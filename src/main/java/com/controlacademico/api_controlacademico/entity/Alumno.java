package com.controlacademico.api_controlacademico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alumno_id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_persona_id")
    private Persona persona;
    @ManyToOne
    @JoinColumn(name = "fk_grado_id", referencedColumnName = "grado_id")
    private Grado grado;
    @ManyToOne
    @JoinColumn(name = "fk_padre_familia_id", referencedColumnName = "padre_familia_id")
    private PadreFamilia padreFamilia;
    @ManyToOne
    @JoinColumn(name = "fk_matricula_id", referencedColumnName = "matricula_id")
    private Matricula matricula;
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Observacion> observaciones;
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Asistencia> asistencias;
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Calificacion> calificaciones;
    @Column(name = "alumno_activo")
    private Byte activo = 1;
}
