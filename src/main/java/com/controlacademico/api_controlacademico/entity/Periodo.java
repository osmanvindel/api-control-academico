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
    private LocalDate fecha_inicio;
    @Column(name = "periodo_fecha_fin")
    private LocalDate fecha_fin;
    @Column(name = "periodo_abierto")
    private byte abierto;
    @OneToMany(mappedBy = "periodo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Calificacion> calificaciones;
}
