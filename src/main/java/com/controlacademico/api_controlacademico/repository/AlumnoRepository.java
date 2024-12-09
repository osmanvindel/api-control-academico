package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    Optional<Alumno> findByIdAndActivo(int id, byte activo);

    List<Alumno> findByActivo(byte id);
}
