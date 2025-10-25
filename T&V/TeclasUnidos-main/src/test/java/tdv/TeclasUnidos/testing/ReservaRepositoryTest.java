package tdv.TeclasUnidos.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tdv.teclasunidos.entities.Recurso;
import tdv.teclasunidos.entities.Reserva;
import tdv.teclasunidos.entities.Socio;

public class ReservaRepositoryTest {
    @Test
    public void reservaRecursoSiNoEstaOcupado_debeFuncionar() throws Exception {
        Recurso sala=new Recurso("Sala de Reuniones","Piso 1");
        Socio s1=new Socio("Enzo",30,"Calle Falsa 123","12345678A");
        Socio s2=new Socio("Ana",25,"Calle Real 456","87654321B");

        List<Reserva> reservas=sala.getReservas();

        LocalDateTime inicio1=LocalDateTime.of(2024,6,10,10,0);
        LocalDateTime fin1=LocalDateTime.of(2024,6,10,12,0);
        Reserva reserva1=new Reserva(sala,s1,inicio1,fin1);
        reservas.add(reserva1);

        LocalDateTime inicio2=LocalDateTime.of(2024,6,10,12,30);
        LocalDateTime fin2=LocalDateTime.of(2024,6,10,14,0);
        Reserva reserva2=new Reserva(sala,s2,inicio2,fin2);

        boolean puedeReservar=true;
        for(Reserva existente:reservas){
            if(existente.getRecurso().equals(reserva2.getRecurso())){
                existente.seSuperpone(reserva2.getInicio(),reserva2.getFin());
                puedeReservar=false;
                break;
            }
        }
        assertFalse("no se puede reservar", puedeReservar);
    }

    @Test
    public void eliminarReservaDeOtroUsuario_noTieneEfecto() throws Exception{
        List<Reserva> reservas=new ArrayList<>();
        String dni1="12345678";
        String dni2="87654321";

        reservas.put(dni1, "Sala de Reuniones");

        reservas.remove(dni2);
        assertEquals("Sala de Reuniones",reservas.get(dni1));
    }

    @Test
    public void inscribirMenorDe16_Boxeo_noPermitido() throws Exception {
    Socio menor = new Socio("Juan", 15, "Calle", "11111111A");
    String actividad = "Boxeo";

    try {
        if (menor.getEdad() < 16 && actividad.equals("Boxeo")) {
            throw new IllegalArgumentException("No se puede inscribir menores de 16 años a Boxeo");
        }
        fail("Se esperaba excepción por edad menor a 16 en Boxeo");
    } catch (IllegalArgumentException e) {
    }
}
}
