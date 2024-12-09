package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data //Gets y Sets declarados implicitamente
@Entity //Definimos esta clase como una entidad
@Table(name = "direcciones")
public class Direccion {
    @Id //Inicamos que es un campo clave
    @GeneratedValue(strategy = GenerationType.IDENTITY) //El campo clave es autoincremental
    @Column(name = "direccion_id")
    private int id;
    @Column(name = "direccion_departamento")
    private String departamento;
    @Column(name = "direccion_municipio")
    private String municipio;
    @Column(name = "direccion_barrio_colonia")
    private String barrioColonia;
    @Column(name = "direccion_referencia")
    private String referencia;
    //@OneToOne(mappedBy = "fk_direccion_id")
    //private Persona presona;
}
