package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {
    boolean existsByCodigo(String codigo);
    List<Periodo> findByAbierto(byte abierto);
    Optional<Periodo> findByIdAndAbierto(int id, byte abierto);
}
