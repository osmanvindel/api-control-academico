package com.controlacademico.api_controlacademico.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "matriculas")
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula_id")
    private int id;
    @Column(name = "matricula_fecha")
    private LocalDate fecha;
    @Column(name = "matricula_precio")
    private Double precio;
    @Column(name = "matricula_comentarios")
    private String comentarios;
    @Column(name = "matricula_abierta")
    private Byte abierta = 1;
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Alumno> alumnos;
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pago> pagos;

    public boolean vacio() {
        return this.fecha == null
                && this.precio == null
                && this.comentarios == null;
    }
}
