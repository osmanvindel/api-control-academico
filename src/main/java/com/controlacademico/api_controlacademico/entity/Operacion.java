package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "operaciones")
public class Operacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operacion_id")
    private int id;
    @Column(name = "operacion_nombre")
    private String nombre;
    @ManyToOne()
    @JoinColumn(name = "fk_modulo_id")
    private Modulo modulo;
//    @ManyToMany
//    @JoinTable(
//            name = "roles_operaciones", // Nombre de la tabla de unión existente (roles_operaciones)
//            joinColumns = @JoinColumn(name = "operacion_id"), // Columna de esta entidad en la tabla de unión (Operacion)
//            inverseJoinColumns = @JoinColumn(name = "rol_id")// Columna de la otra entidad en la tabla de unión (Rol en este caso)
//    )
//    private List<Rol> roles;
    @OneToMany(mappedBy = "operacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RolesOperaciones> rol;

}
