package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Maestro;
import org.hibernate.id.IncrementGenerator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaestroRepository extends JpaRepository<Maestro, Integer> {

}
