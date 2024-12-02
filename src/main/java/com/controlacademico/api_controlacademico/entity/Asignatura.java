package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "asignaturas")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asignatura_id")
    private int id;
    @Column(name = "asignatura_codigo")
    private String codigo;
    @Column(name = "asignatura_nombre")
    private String nombre;
    @Column(name = "asignatura_descripcion")
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "fk_grado_id", referencedColumnName = "grado_id")
    private Grado grado;
    @OneToMany(mappedBy = "asignatura", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Calificacion> calificaciones;
}
