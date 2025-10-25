package tdv.TeclasUnidos.testing;

import org.junit.Test;
import static org.junit.Assert.*;

import tdv.teclasunidos.entities.Socio;
import tdv.teclasunidos.entities.EdadInvalidaException;
import tdv.teclasunidos.entities.NombreMuyLargoException;
import tdv.teclasunidos.repositories.SocioRepository;

import java.util.List;

public class SocioRepositoryTest {

    @Test
    public void agregarYBuscar_debeFuncionar() throws Exception {
        SocioRepository repo = new SocioRepository();
        Socio s = new Socio("Enzo", 30, "Calle Falsa 123", "12345678A");
        repo.agregar(s);

        Socio buscado = repo.buscarPorDni("12345678A");
        assertNotNull(buscado);
        assertEquals("Enzo", buscado.getNombre());
    }

    @Test
    public void eliminarPorDni_debeEliminarSocio() throws Exception {
        SocioRepository repo = new SocioRepository();
        Socio s = new Socio("Ana", 25, "Calle Real 456", "87654321B");
        repo.agregar(s);

        assertTrue(repo.eliminarPorDni("87654321B"));
        assertNull(repo.buscarPorDni("87654321B"));
    }


    @Test
    public void actualizar_debeModificarDatos() throws Exception {
        SocioRepository repo = new SocioRepository();
        Socio s = new Socio("Enzo", 30, "Calle Falsa 123", "12345678A");
        repo.agregar(s);

        Socio modificado = new Socio("Enzo J.", 31, "Calle Falsa 123", "12345678A");
        repo.actualizar(modificado);

        Socio buscado = repo.buscarPorDni("12345678A");
        assertEquals("Enzo J.", buscado.getNombre());
        assertEquals(31, buscado.getEdad());
    }

    @Test
    public void listar_debeDevolverTodosLosSocios() throws Exception {
        SocioRepository repo = new SocioRepository();
        repo.agregar(new Socio("Enzo", 30, "Calle Falsa 123", "12345678A"));
        repo.agregar(new Socio("Ana", 25, "Calle Real 456", "87654321B"));

        List<Socio> lista = repo.listar();
        assertEquals(2, lista.size());
    }

    @Test
    public void nombreMasCortoQue50_debeLanzarError()throws Exception{
        try{
            new Socio("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 25, "Calle", "12345678A");
            fail("Se esperaba IllegalArgumentException");
        }
        catch(IllegalArgumentException  e){
            
        }
    }   

    @Test
    public void dniCon6o7NumerosYSinPuntos_debeCrearSocio() throws Exception{
    
    }
}
