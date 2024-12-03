package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "padre_familia")
public class PadreFamilia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "padre_familia_id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_persona_id")
    private Persona persona;
    @OneToMany(mappedBy = "padreFamilia", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Alumno> alumnos;
}
