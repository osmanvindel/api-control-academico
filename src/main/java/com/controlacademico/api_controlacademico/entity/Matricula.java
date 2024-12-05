package com.controlacademico.api_controlacademico.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
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
    private LocalDateTime fecha;
    @Column(name = "matricula_precio")
    private Double precio;
    @Column(name = "matricula_comentarios")
    private String comentarios;
    @Column(name = "matricula_abierta")
    private Byte abierta = 1;
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Alumno> alumnos;
    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pago> pagos;

    //Establecer relacion bidireccional explicitamente
//    public void agregarAlumno(List<Alumno> alumnos) {
//        for (Alumno alumno : alumnos) {
//            alumno.setMatricula(this);
//        }
//    }

    public boolean vacio() {
        return this.fecha == null
                && this.precio == null
                && this.comentarios == null;
    }
}
