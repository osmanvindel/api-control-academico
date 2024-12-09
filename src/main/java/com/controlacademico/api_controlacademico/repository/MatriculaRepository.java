package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    Optional<Matricula> findByFecha(LocalDate fecha);

    List<Matricula> findByFechaBefore(LocalDate fecha);

    List<Matricula> findByFechaAfter(LocalDate fecha);

    List<Matricula> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
