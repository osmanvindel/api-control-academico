package com.controlacademico.api_controlacademico.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data //Anotacion de la dependencia Lombok la cual define implicitamente los get y set de una clase
@Entity //Definimos una entidad
@Table(name = "personas")
//Indicamos cual es el nombre de la tabla en la DB porque en este caso la entiadad Persona no coincide exactamente
public class Persona {
    @Id //Anotacion para indicar el campo clave de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private int id;
    @Column(name = "persona_cedula")
    private String cedula;
    @Column(name = "persona_nombre")
    private String nombre;
    @Column(name = "persona_apellido")
    private String apellido;
    @Column(name = "persona_correo")
    private String correo;
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Telefono> telefonos;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_direccion_id")
    private Direccion direccion;

    public boolean vacio() {
        return this.cedula == null
                && this.nombre == null
                && this.apellido == null
                && this.correo == null;
    }
}

