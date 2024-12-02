package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Maestro;
import com.controlacademico.api_controlacademico.repository.MaestroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaestroService {
    private final MaestroRepository maestroRepository;

    @Autowired
    public MaestroService(MaestroRepository maestroRepository) {
        this.maestroRepository = maestroRepository;
    }

    public Maestro agregarMaestro(Maestro maestro) {
        //Evitamos referencias nulas
        maestro.getPersona().agregarTelefono(maestro.getPersona().getTelefonos());
        maestro.setPersona(maestro.getPersona());
        return maestroRepository.save(maestro);
    }
}
