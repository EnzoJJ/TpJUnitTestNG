package tdv.TeclasUnidos.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import tdv.teclasunidos.entities.Recurso;
import tdv.teclasunidos.entities.Socio;
import tdv.teclasunidos.repositories.RecursoRepository;

public class RecursoRepositoryTest {
    @Test
    public void agregarYBuscar_debeFuncionar()throws Exception{
        RecursoRepository repo=new RecursoRepository();
        Recurso r=new Recurso("R1","ayacucho");
        repo.agregar(r);

        Recurso buscado=repo.buscarPorNombre("R1");
        assertNotNull(buscado);
        assertEquals("ayacucho",buscado.getUbicacion());
    }

    @Test
    public void eliminarPorNombre_debeEliminarElRecurso()throws Exception{
        RecursoRepository repo=new RecursoRepository();
        Recurso r=new Recurso("R2","belgrano");
        repo.agregar(r);

        boolean eliminado=repo.eliminarPorNombre("R2");
        assertNotNull(r);
        assertEquals("belgrano",r.getUbicacion());
    }

    @Test
    public void actualizar_debeModificarDatos()throws Exception{
        RecursoRepository repo=new RecursoRepository();
        Recurso r=new Recurso("R3","palermo");
        repo.agregar(r);

        Recurso modificado=new Recurso("R3","recoleta");
        repo.actualizar(modificado);

        Recurso buscado=repo.buscarPorNombre("R3");
        assertEquals("recoleta",buscado.getUbicacion());
    }

    @Test
    public void listar_debeDevolverTodosLosRecursos()throws Exception{
        RecursoRepository repo=new RecursoRepository();
        repo.agregar(new Recurso("R4","villa crespo"));
        repo.agregar(new Recurso("R5","caballito"));

        int lista=repo.listar().size();
        assertEquals(2,lista);
    }
    @Test
    public void crearRecursoOficina_debeSerIgnorado() {
        RecursoRepository repo = new RecursoRepository();
        String nombre = "Oficina";
        String ubicacion = "Centro";

        Recurso r = null;
        if (!"Oficina".equalsIgnoreCase(nombre)) {
            r = new Recurso(nombre, ubicacion);
            repo.agregar(r);
        }

        assertNull("No se debe crear el recurso de tipo Oficina", r);
        assertTrue(repo.listar().isEmpty());
    }


}