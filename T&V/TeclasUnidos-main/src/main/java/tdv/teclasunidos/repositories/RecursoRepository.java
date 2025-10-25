package tdv.teclasunidos.repositories;

import java.util.*;

import tdv.teclasunidos.entities.Recurso;

public class RecursoRepository {

    private Map<String, Recurso> recursos = new HashMap<>();

    // Agregar recurso
    public void agregar(Recurso recurso) {
        recursos.put(recurso.getNombre(), recurso);
    }

    // Buscar recurso por nombre
    public Recurso buscarPorNombre(String nombreRecurso) {
        return recursos.get(nombreRecurso);
    }

    // Eliminar recurso por nombre
    public boolean eliminarPorNombre(String nombreRecurso) {
        return recursos.remove(nombreRecurso) != null;
    }

    // Actualizar un recurso existente
    public void actualizar(Recurso recursoModificado) {
        if (recursos.containsKey(recursoModificado.getNombre())) {
            recursos.put(recursoModificado.getNombre(), recursoModificado);
        }
    }

    // Listar todos los recursos
    public Collection<Recurso> listar() {
        return recursos.values();
    }
}
