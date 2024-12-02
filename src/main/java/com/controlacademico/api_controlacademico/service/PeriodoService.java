package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Periodo;
import com.controlacademico.api_controlacademico.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodoService {
    private final PeriodoRepository periodoRepository;

    @Autowired
    public PeriodoService(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    public Periodo crearPeriodo(Periodo periodo) {
        return periodoRepository.save(periodo);
    }
}
