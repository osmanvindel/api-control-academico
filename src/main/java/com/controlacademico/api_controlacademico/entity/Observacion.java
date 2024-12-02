package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "observaciones")
public class Observacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "observacion_id")
    private int id;
    @Column(name = "observacion_comentario")
    private String comentario;
    @ManyToOne
    @JoinColumn(name = "fk_alumno_id", referencedColumnName = "alumno_id")
    private Alumno alumno;
}
