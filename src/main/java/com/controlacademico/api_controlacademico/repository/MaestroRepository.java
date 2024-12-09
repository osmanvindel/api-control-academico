package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaestroRepository extends JpaRepository<Maestro, Integer> {
    Optional<Maestro> findByIdAndActivo(int id, byte activo);

    List<Maestro> findByActivo(byte activo);
}
