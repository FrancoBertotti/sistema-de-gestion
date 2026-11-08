package com.tup.programacion3.service;

import java.util.List;
import com.tup.programacion3.model.entities.Usuario;
import com.tup.programacion3.model.enums.FormaPago;

public record SolicitudPedido(Usuario usuario, FormaPago formaPago, List<ItemPedido> items) {}