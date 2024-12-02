package com.controlacademico.api_controlacademico.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private int id;
    @Column(name = "usuario_nombre")
    private String nombre;
    @Column(name = "usuario_correo")
    private String correo;
    @Column(name = "usuario_password")
    private String password;
    @Column(name = "usuario_fecha_creacion")
    private LocalDateTime fechaCreacion;
    @ManyToOne()
    @JoinColumn(name = "fk_rol_id", referencedColumnName = "rol_id")
    private Rol rol;
    @Column(name = "usuario_activo")
    private Byte activo;

    public boolean vacio() {
        return this.nombre == null & this.correo == null & this.password == null & this.fechaCreacion == null;
    }
}
