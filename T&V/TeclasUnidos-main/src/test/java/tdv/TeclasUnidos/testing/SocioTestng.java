package tdv.TeclasUnidos.testing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

import tdv.teclasunidos.entities.NombreMuyLargoException;
import tdv.teclasunidos.entities.Socio;

public class SocioTestng {

    @DataProvider(name = "sociosCsv")
    public Object[][] sociosCsv() {
        return new Object[][] {
            {"Enzo", 30, "Calle 123", "1234567"},
            {"Ana", 25, "Calle 456", "7654321"}
        };
    }
    //para carga desde CSV
    @Test(dataProvider = "sociosCsv")
    public void crearSocio_desdeCsv(String nombre, int edad, String direccion, String dni) throws Exception {
        Socio s = new Socio(nombre, edad, direccion, dni);
        assertEquals(s.getNombre(), nombre);
        assertEquals(s.getEdad(), edad);
        assertEquals(s.getDni(), dni);
    }

    @Test(expectedExceptions = NombreMuyLargoException.class)
    public void nombreMuyLargo_debeLanzarExcepcion() throws Exception {
        new Socio("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", 30, "Calle", "1234567");
    }
}
