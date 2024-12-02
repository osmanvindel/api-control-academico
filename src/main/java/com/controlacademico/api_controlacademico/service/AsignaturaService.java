package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Asignatura;
import com.controlacademico.api_controlacademico.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaService {
    private final AsignaturaRepository asignaturaRepository;

    @Autowired
    public AsignaturaService(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    public Asignatura crearAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }
}
