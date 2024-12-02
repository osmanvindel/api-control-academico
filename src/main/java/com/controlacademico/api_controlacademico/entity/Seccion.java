package com.controlacademico.api_controlacademico.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "secciones")
public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seccion_id")
    private int id;
    @Column(name = "seccion_numero")
    private int numero;
    @ManyToOne
    @JoinColumn(name = "fk_grado_id", referencedColumnName = "grado_id")
    //@JsonBackReference
    private Grado grado;
}
