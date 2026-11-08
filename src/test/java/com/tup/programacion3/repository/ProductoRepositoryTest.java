package com.tup.programacion3.repository;

import com.tup.programacion3.model.entities.Categoria;
import com.tup.programacion3.model.entities.Producto;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProductoRepositoryTest {

    private final CategoriaRepository categoriaRepo = new CategoriaRepository();
    private final ProductoRepository productoRepo = new ProductoRepository();

    @Test
    void buscarPorCategoria_devuelveSoloLosProductosDeEsaCategoria() {
        // PREPARAR: creo los datos que necesita la prueba
        Categoria categoria = new Categoria();
        categoria.setNombre("Categoria de prueba");
        categoria.setDescripcion("Solo para el test");
        categoria.setEliminado(false);
        Categoria categoriaGuardada = categoriaRepo.guardar(categoria);

        Producto producto = new Producto();
        producto.setNombre("Producto de prueba");
        producto.setPrecio(100.0);
        producto.setStock(5);
        producto.setEliminado(false);
        producto.setCategoria(categoriaGuardada);
        productoRepo.guardar(producto);

        // EJECUTAR: llamo al método que quiero probar
        List<Producto> resultado = productoRepo.buscarPorCategoria(categoriaGuardada.getId());

        // VERIFICAR: comparo con lo que esperaba
        assertEquals(1, resultado.size());
        assertEquals("Producto de prueba", resultado.get(0).getNombre());
    }
}