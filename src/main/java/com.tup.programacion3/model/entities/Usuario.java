package com.tup.programacion3.model.entities;

import com.tup.programacion3.model.enums.Rol;

import java.util.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@Entity
public class Usuario extends com.tup.programacion3.model.entities.Base {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<com.tup.programacion3.model.entities.Pedido> pedidos = new ArrayList<>();

}
