package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Observacion;
import com.controlacademico.api_controlacademico.repository.ObservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservacionService {
    private final ObservacionRepository observacionRepository;

    @Autowired
    public ObservacionService(ObservacionRepository observacionRepository) {
        this.observacionRepository = observacionRepository;
    }

    public Observacion crearObservacion(Observacion observacion) {
        return observacionRepository.save(observacion);
    }
}
