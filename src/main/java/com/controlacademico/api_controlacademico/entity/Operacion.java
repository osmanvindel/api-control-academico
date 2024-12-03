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
    @OneToMany(mappedBy = "operacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RolesOperaciones> rol;

}
