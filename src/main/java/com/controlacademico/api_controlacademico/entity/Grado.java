package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "grados")
public class Grado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grado_id")
    private Integer id;
    @Column(name = "grado_codigo")
    private String codigo;
    @Column(name = "grado_nombre")
    private String nombre;
    @Column(name = "grado_capacidad_alumnos")
    private Integer capacidad;
    @Column(name = "grado_cantidad_alumnos")
    private int cantidad;
    @Column(name = "grado_descripcion")
    private String descripcion;
    @Column(name = "grado_borrado")
    private Byte borrado = 0;
    @OneToMany(mappedBy = "grado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Seccion> secciones;
    @OneToMany(mappedBy = "grado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Alumno> alumnos;
    @OneToMany(mappedBy = "grado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonBackReference
    //@JsonIgnoreProperties
    private List<Asignatura> asignaturas;

    public boolean vacio() {
        return this.codigo == null
                && this.nombre == null
                && this.capacidad == null
                && this.descripcion == null
                && this.borrado == null;
    }
}
