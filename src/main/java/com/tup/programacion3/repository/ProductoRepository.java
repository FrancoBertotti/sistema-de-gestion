package com.tup.programacion3.repository;

import com.tup.programacion3.model.entities.Producto;

import java.util.List;


public class ProductoRepository extends BaseRepository<Producto> {
    public ProductoRepository() {
        super(Producto.class);
    }

    // buscar por categoria
    public List<Producto> buscarPorCategoria(Long categoriaId) {

            String jpql = "FROM Producto p WHERE p.categoria.id = :idCategoria AND p.eliminado = false";
            return ejecutarConsulta(em -> em.createQuery(jpql, miClass).setParameter("idCategoria", categoriaId).getResultList());
    }

}
