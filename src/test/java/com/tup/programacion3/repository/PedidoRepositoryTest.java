package com.tup.programacion3.repository;

import com.tup.programacion3.model.entities.Pedido;
import com.tup.programacion3.model.enums.Estado;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PedidoRepositoryTest {

    private final PedidoRepository pedidoRepo = new PedidoRepository();

    @Test
    void buscarPorEstado_devuelveLosPedidosConEseEstado() {
        // PREPARAR
        Pedido pedido = new Pedido();
        pedido.setEstado(Estado.PENDIENTE);
        pedido.setFecha(LocalDate.now());
        pedido.setEliminado(false);
        Pedido guardado = pedidoRepo.guardar(pedido);

        // EJECUTAR: buscá los pedidos que están en estado PENDIENTE
        List<Pedido> resultado = pedidoRepo.buscarPorEstado(Estado.PENDIENTE);

        // VERIFICAR: entre los resultados está el pedido que guardé
        assertTrue(resultado.stream().anyMatch(p -> p.getId().equals(guardado.getId())));
    }
}