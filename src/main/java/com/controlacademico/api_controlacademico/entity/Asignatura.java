package com.controlacademico.api_controlacademico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "asignaturas")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asignatura_id")
    private int id;
    @Column(name = "asignatura_codigo")
    private String codigo;
    @Column(name = "asignatura_nombre")
    private String nombre;
    @Column(name = "asignatura_descripcion")
    private String descripcion;
    //@JsonIgnore
    @ManyToOne
    //@JsonManagedReference
    @JsonIgnoreProperties("asignaturas")
    @JoinColumn(name = "fk_grado_id", referencedColumnName = "grado_id")
    private Grado grado;
    @OneToMany(mappedBy = "asignatura", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Calificacion> calificaciones;
    @Column(name = "asignatura_disponible")
    private Byte disponible = 1;

    public boolean vacio() {
        return this.codigo == null
                && this.nombre == null
                && this.descripcion == null;
    }
}
