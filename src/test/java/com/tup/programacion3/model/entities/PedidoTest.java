package com.tup.programacion3.model.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void calcularTotal_sumaLosSubtotalesDeLosDetalles() {
        Producto p1 = new Producto();
        p1.setPrecio(100.0);
        Producto p2 = new Producto();
        p2.setPrecio(50.0);
        Pedido pedido = new Pedido();

        pedido.addDetallePedido(2, p1);   // subtotal 200
        pedido.addDetallePedido(3, p2);   // subtotal 150
        pedido.calcularTotal();

        assertEquals(350.0, pedido.getTotal());
    }
    @Test
    void agregarDosDetallesConMismoSubtotal_hoyPierdeUnaLinea() {
        Producto p1 = new Producto();
        p1.setPrecio(100.0);
        Producto p2 = new Producto();
        p2.setPrecio(100.0);
        Pedido pedido = new Pedido();

        pedido.addDetallePedido(2, p1);
        pedido.addDetallePedido(2, p2);
        pedido.calcularTotal();

        assertEquals(1, pedido.getDetalles().size());
        assertEquals(200.0, pedido.getTotal());
    }
}