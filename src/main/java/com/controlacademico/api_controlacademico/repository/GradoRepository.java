package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Grado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradoRepository extends JpaRepository<Grado, Integer> {
    boolean existsByCodigo(String codigo);

    boolean existsByNombre(String nombre);

    List<Grado> findByBorrado(byte borrado);

    Optional<Grado> findByIdAndBorrado(int id, byte borrado);
}
