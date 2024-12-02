package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tipo_pago")
public class TipoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_pago_id")
    private int id;
    @Column(name = "tipo_pago_nombre")
    private String nombre;
    @OneToMany(mappedBy = "tipoPago", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pago> pagos;
}
