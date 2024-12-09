package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "periodos")
public class Periodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodo_id")
    private int id;
    @Column(name = "periodo_codigo")
    private String codigo;
    @Column(name = "periodo_fecha_inicio")
    private LocalDate fechaInicio;
    @Column(name = "periodo_fecha_fin")
    private LocalDate fechaFin;
    @Column(name = "periodo_abierto")
    private Byte abierto = 1;
    @OneToMany(mappedBy = "periodo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Calificacion> calificaciones;

    public boolean vacio() {
        return this.codigo == null
                && this.fechaInicio == null
                && this.fechaFin == null;
    }
}
