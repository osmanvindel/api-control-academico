package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_alumno_id", referencedColumnName = "alumno_id")
    private Alumno alumno;
    @Column(name = "observacion_fecha")
    private LocalDateTime fecha;

    public boolean vacio() {
        return this.comentario == null
                && this.fecha == null;
    }
}
