package com.tup.programacion3.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.time.LocalDate;
import com.tup.programacion3.util.JPAUtil;
import com.tup.programacion3.model.entities.DetallePedido;
import com.tup.programacion3.model.entities.Pedido;
import com.tup.programacion3.model.entities.Producto;
import com.tup.programacion3.model.entities.Usuario;
import com.tup.programacion3.model.enums.Estado;
import com.tup.programacion3.model.enums.FormaPago;

public class PedidoService {

    public Pedido crearPedido(SolicitudPedido solicitud) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setUsuario(em.merge(solicitud.usuario()));
            nuevoPedido.setFecha(LocalDate.now());
            nuevoPedido.setEstado(Estado.PENDIENTE);
            nuevoPedido.setFormaPago(solicitud.formaPago());
            nuevoPedido.setEliminado(false);

            // Procesar cada producto
            for (ItemPedido item : solicitud.items()) {
                Producto prodManaged = em.find(Producto.class, item.idProducto());

                if (prodManaged.getStock() < item.cantidad()) {
                    throw new RuntimeException("Stock insuficiente para " + prodManaged.getNombre());
                }

                DetallePedido detalle = new DetallePedido();
                detalle.setProducto(prodManaged);
                detalle.setCantidad(item.cantidad());
                detalle.setSubtotal(prodManaged.getPrecio() * item.cantidad());
                detalle.setPedido(nuevoPedido);
                detalle.setEliminado(false);

                nuevoPedido.getDetalles().add(detalle);

                // Reducir stock
                prodManaged.setStock(prodManaged.getStock() - item.cantidad());
            }

            // Calcular total
            nuevoPedido.calcularTotal();

            // Persistir (cascade persiste detalles)
            em.persist(nuevoPedido);
            tx.commit();
            return nuevoPedido;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}