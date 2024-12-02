package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "maestros_asignaturas")
public class MaestrosAsignaturas {
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_asignatura_id", referencedColumnName = "asignatura_id")
    private Asignatura asignatura;
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_maestro_id", referencedColumnName = "maestro_id")
    private Maestro maestro;
}
