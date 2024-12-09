package com.controlacademico.api_controlacademico.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles_operaciones")
public class RolesOperaciones {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "roles_operaciones_id")
//    private int id;
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_operacion_id", referencedColumnName = "operacion_id")
    private Operacion operacion;
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_rol_id", referencedColumnName = "rol_id")
    private Rol rol;
}
