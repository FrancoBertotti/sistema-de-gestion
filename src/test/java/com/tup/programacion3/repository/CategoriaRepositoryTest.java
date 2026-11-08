package com.tup.programacion3.repository;

import com.tup.programacion3.model.entities.Categoria;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoriaRepositoryTest {

    private final CategoriaRepository repo = new CategoriaRepository();

    @Test
    void guardar_asignaIdYLaListaComoActiva() {
        Categoria c = new Categoria();
        c.setNombre("Postres");
        c.setDescripcion("Dulces");
        c.setEliminado(false);

        Categoria guardada = repo.guardar(c);

        assertNotNull(guardada.getId());
        assertTrue(repo.listarActivos().stream()
                .anyMatch(x -> x.getId().equals(guardada.getId())));
    }

    @Test
    void eliminarLogico_laSacaDelListadoDeActivos() {
        // PREPARAR: creo y guardo una categoría
        Categoria c = new Categoria();
        c.setNombre("Para borrar");
        c.setDescripcion("Se va a dar de baja");
        c.setEliminado(false);
        Categoria guardada = repo.guardar(c);

        // EJECUTAR: la doy de baja lógica
        repo.eliminarLogico(guardada.getId());

        // VERIFICAR: ya no aparece entre las activas
        assertFalse(repo.listarActivos().stream()
                .anyMatch(x -> x.getId().equals(guardada.getId())));
    }
}