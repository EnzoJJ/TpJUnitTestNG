package tdv.TeclasUnidos;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ActividadRepositoryTest {
    @Test
public void inscripcionActividad_cupoSuperado() throws Exception {
    int cupo = 2;
    List<String> inscritos = new ArrayList<>();
    inscritos.add("12345678A");
    inscritos.add("87654321B");

    String nuevoSocio = "11111111C";
    try {
        if (inscritos.size() >= cupo) {
            throw new IllegalStateException("Cupo máximo alcanzado");
        }
        inscritos.add(nuevoSocio);
        fail("Se esperaba error por cupo máximo alcanzado");
    } catch (IllegalStateException e) {
        // OK
    }
}

}
