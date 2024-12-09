package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    Optional<Asistencia> findByFecha(LocalDate fecha);

    List<Asistencia> findByFechaBefore(LocalDate fecha);

    List<Asistencia> findByFechaAfter(LocalDate fecha);

    List<Asistencia> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
