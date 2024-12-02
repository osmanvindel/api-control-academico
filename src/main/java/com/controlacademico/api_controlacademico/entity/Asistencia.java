package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.type.descriptor.java.spi.MapEntryJavaType;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "asistencias")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asistencia_id")
    private int id;
    @Column(name = "asistencia_fecha")
    private LocalDateTime fecha;
    @Column(name = "asistencia_asignatura")
    private String asignatura;
    @ManyToOne
    @JoinColumn(name = "fk_alumno_id", referencedColumnName = "alumno_id")
    private Alumno alumno;
    @ManyToOne
    @JoinColumn(name = "fk_maestro_id", referencedColumnName = "maestro_id")
    private Maestro maestro;
}
