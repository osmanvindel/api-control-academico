package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.PadreFamilia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PadreFamiliaRepository extends JpaRepository<PadreFamilia, Integer> {
    Optional<PadreFamilia> findByIdAndActivo(int id, byte activo);

    List<PadreFamilia> findByActivo(byte activo);
}
