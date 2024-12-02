package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "modulos")
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modulo_id")
    private int id;
    @Column(name = "modulo_nombre")
    private String nombre;
    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Operacion> operacion;

}
