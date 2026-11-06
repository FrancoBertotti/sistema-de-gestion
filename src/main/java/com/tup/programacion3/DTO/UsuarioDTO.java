package com.tup.programacion3.DTO;

import com.tup.programacion3.model.entities.Pedido;

import java.util.Set;

import lombok.Builder;

@Builder

public record UsuarioDTO(
        Long id,
        String nombre,
        String apellido,
        String mail,
        String celular,
        Set<Pedido> pedidos
) {
}
