package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pago_id")
    private int id;
    @Column(name = "pago_fecha")
    private LocalDateTime fecha;
    @Column(name = "pago_monto")
    private double monto;
    @Column(name = "pago_json", columnDefinition = "json")
    private String json;
    @ManyToOne
    @JoinColumn(name = "fk_tipo_pago_id", referencedColumnName = "tipo_pago_id")
    private TipoPago tipoPago;
    @ManyToOne
    @JoinColumn(name = "fk_matricula_id", referencedColumnName = "matricula_id")
    private Matricula matricula;
}
