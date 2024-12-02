package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "alumnos_asignaturas")
public class AlumnosAsignaturas {
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_alumno_id", referencedColumnName = "alumno_id")
    private Alumno alumno;
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_asignatura_id", referencedColumnName = "asignatura_id")
    private Asignatura asignatura;
}
