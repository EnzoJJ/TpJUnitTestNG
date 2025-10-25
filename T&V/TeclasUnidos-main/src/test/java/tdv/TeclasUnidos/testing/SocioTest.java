package tdv.TeclasUnidos.testing;

import org.junit.Test;
import static org.junit.Assert.*;

import tdv.teclasunidos.entities.Socio;
import tdv.teclasunidos.repositories.RecursoRepository;
import tdv.teclasunidos.entities.EdadInvalidaException;
import tdv.teclasunidos.entities.NombreMuyLargoException;

import java.util.HashMap;
import java.util.Map;

public class SocioTest {

    // --- Tests que ya tenías ---
    @Test
    public void constructorValido_noDebeLanzarExcepcion_yGettersDevuelvenValores() throws Exception {
        Socio s = new Socio("Enzo", 30, "Calle Falsa 123", "12345678A");
        assertEquals("Enzo", s.getNombre());
        assertEquals(30, s.getEdad());
        assertEquals("12345678A", s.getDni());
    }

    @Test
    public void nombreMuyLargo_debeLanzarNombreMuyLargoException() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 52; i++) sb.append('x');
        String nombreMuyLargo = sb.toString();

        try {
            new Socio(nombreMuyLargo, 25, "Calle", "00000000X");
            fail("Se esperaba NombreMuyLargoException");
        } catch (NombreMuyLargoException e) {
            // OK
        } catch (Exception e) {
            fail("Se esperaba NombreMuyLargoException, pero se lanzó: " + e);
        }
    }

    @Test
    public void edadNegativa_debeLanzarEdadInvalidaException() {
        try {
            new Socio("Persona", -1, "Addr", "22222222B");
            fail("Se esperaba EdadInvalidaException");
        } catch (EdadInvalidaException e) {
            // OK
        } catch (Exception e) {
            fail("Se esperaba EdadInvalidaException, pero se lanzó: " + e);
        }
    }

    // --- Mini ABM dentro del test usando solo Socio ---
    @Test
    public void abmBasico_debePermitirAltaBajaModificacionYBusqueda() throws Exception {
        Map<String, Socio> repositorio = new HashMap<>();

        // --- ALTA ---
        Socio s1 = new Socio("Enzo", 30, "Calle Falsa 123", "12345678A");
        Socio s2 = new Socio("Ana", 25, "Calle Real 456", "87654321B");
        repositorio.put(s1.getDni(), s1);
        repositorio.put(s2.getDni(), s2);

        assertEquals(2, repositorio.size());
        assertTrue(repositorio.containsKey("12345678A"));
        assertTrue(repositorio.containsKey("87654321B"));

        // --- MODIFICACION ---
        Socio s1Modificado = new Socio("Enzo J.", 31, "Calle Falsa 123", "12345678A");
        repositorio.put(s1Modificado.getDni(), s1Modificado);

        Socio buscado = repositorio.get("12345678A");
        assertEquals("Enzo J.", buscado.getNombre());
        assertEquals(31, buscado.getEdad());

        // --- BAJA ---
        repositorio.remove("87654321B");
        assertFalse(repositorio.containsKey("87654321B"));

        // --- BUSQUEDA ---
        Socio encontrado = repositorio.get("12345678A");
        assertNotNull(encontrado);
        assertEquals("Enzo J.", encontrado.getNombre());
    }
}
