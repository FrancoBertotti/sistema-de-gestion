package com.tup.programacion3.repository;

import com.tup.programacion3.model.entities.Pedido;
import com.tup.programacion3.model.enums.Estado;
import com.tup.programacion3.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PedidoRepository extends BaseRepository<Pedido> {

    public PedidoRepository() {
        super(Pedido.class);
    }

    // Buscar pedidos activos de un usuario específico
    public List<Pedido> buscarPorUsuario(Long usuarioId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.usuario.id = :usuarioId AND p.eliminado = false",
                    Pedido.class)
                    .setParameter("usuarioId", usuarioId)
                    .getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    // Buscar pedidos activos por estado
    public List<Pedido> buscarPorEstado(Estado estado) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM Pedido p WHERE p.estado = :estado AND p.eliminado = false",
                    Pedido.class)
                    .setParameter("estado", estado)
                    .getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
