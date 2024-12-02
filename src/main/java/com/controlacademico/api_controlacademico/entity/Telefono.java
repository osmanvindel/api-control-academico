package com.controlacademico.api_controlacademico.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "telefonos")
public class Telefono {
    @Id //Anotacion para indicar que es el campo clave
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Anotacion para indicar que el campo es autoincremental
    @Column(name = "telefono_id")
    private int id;
    @Column(name = "telefono_numero")
    private String numero;
    @ManyToOne //Anotacion para indicar la cardinalidad de la llave foranea
    @JoinColumn(name = "fk_persona_id", referencedColumnName = "persona_id")
    @JsonBackReference
    private Persona persona;
}
