package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    boolean existsByNombre(String nombre);
}
