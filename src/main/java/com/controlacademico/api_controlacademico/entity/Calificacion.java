package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "calificaciones")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calificacion_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "fk_periodo_id", referencedColumnName = "periodo_id")
    private Periodo periodo;
    @ManyToOne
    @JoinColumn(name = "fk_alumno_id", referencedColumnName = "alumno_id")
    private Alumno alumno;
    @ManyToOne
    @JoinColumn(name = "fk_asignatura_id", referencedColumnName = "asignatura_id")
    private Asignatura asignatura;
    @Column(name = "calificacion_nota")
    private Double nota;
    @Column(name = "calificacion_fecha")
    private LocalDateTime fecha;

    public boolean vacio() {
        return this.asignatura ==  null
                && this.nota == null;
    }
}
