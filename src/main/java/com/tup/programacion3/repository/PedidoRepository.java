package com.tup.programacion3.repository;

import com.tup.programacion3.model.entities.Pedido;
import com.tup.programacion3.model.enums.Estado;

import java.util.List;


public class PedidoRepository extends BaseRepository<Pedido> {

    public PedidoRepository() {
        super(Pedido.class);
    }

    // Buscar pedidos activos de un usuario específico
    public List<Pedido> buscarPorUsuario(Long usuarioId) {
        String jpql ="SELECT p FROM Pedido p WHERE p.usuario.id = :usuarioId AND p.eliminado = false";
        return ejecutarConsulta(em -> em.createQuery(jpql, miClass).setParameter("usuarioId", usuarioId).getResultList());
    }

    // Buscar pedidos activos por estado
    public List<Pedido> buscarPorEstado(Estado estado) {
        String jpql = "SELECT p FROM Pedido p WHERE p.estado = :estado AND p.eliminado = false";
        return ejecutarConsulta(em -> em.createQuery(jpql, miClass).setParameter("estado", estado).getResultList());
    }

}
