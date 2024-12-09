package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
    boolean existsByCodigo(String codigo);

    List<Asignatura> findByDisponible(byte disponible);

    Optional<Asignatura> findByIdAndDisponible(int id, byte disponible);
}
