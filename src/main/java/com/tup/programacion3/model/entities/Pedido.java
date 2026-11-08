package com.tup.programacion3.model.entities;

import com.tup.programacion3.model.enums.Estado;
import com.tup.programacion3.model.enums.FormaPago;

import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@Entity
public class Pedido extends Base implements Calculable {
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Double total;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<DetallePedido> detalles = new HashSet<>();


    @Override
    public void calcularTotal() {
        this.total = detalles.stream()
                .map(DetallePedido::getSubtotal)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public void addDetallePedido(int cantidad, Producto producto) {
        if (this.detalles == null) {
            this.detalles = new java.util.HashSet<>();
        }
        DetallePedido nuevoDetalle = new DetallePedido();
        nuevoDetalle.setCantidad(cantidad);
        nuevoDetalle.setProducto(producto);

        if (producto != null && producto.getPrecio() != null) {
            nuevoDetalle.setSubtotal(cantidad * producto.getPrecio());
        }

        nuevoDetalle.setPedido(this);
        this.detalles.add(nuevoDetalle);
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto() != null && detalle.getProducto().equals(producto)) {
                return detalle;
            }
        }
        return null;
    }

    public int getCantidadTotalItems() {
        return detalles.stream()
                // Filtramos por seguridad si algún detalle es nulo
                .filter(Objects::nonNull)
                // Transformamos el flujo de DetallePedido a un flujo numérico con sus cantidades
                .mapToInt(DetallePedido::getCantidad)
                // Sumamos todas las cantidades
                .sum();
    }
    public void getDetalle() {
        detalles.stream()
                .filter(Objects::nonNull)
                .forEach(detalle -> {
                    System.out.println("- " + detalle.getCantidad() + "x " + detalle.getProducto().getNombre());
                });

        System.out.println("--------------------------\n");

    }

}
