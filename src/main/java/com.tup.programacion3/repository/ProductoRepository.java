package com.tup.programacion3.repository;

import com.tup.programacion3.model.entities.Producto;
import com.tup.programacion3.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ProductoRepository extends BaseRepository<Producto>{
    public ProductoRepository() {
        super(Producto.class);
    }

    //buscar por categoria
    public List<Producto> buscarPorCategoria(Long categoriaId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "FROM Producto p WHERE p.categoria.id = :idCategoria AND p.eliminado = false";

            return em.createQuery(jpql, Producto.class)
                    .setParameter("idCategoria", categoriaId)
                    .getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
