package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Direccion;
import com.controlacademico.api_controlacademico.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {
    private final DireccionRepository direccionRepository;

    @Autowired
    public DireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    //Editar
    public void editarDireccion(int id, Direccion direccion) {
        if (direccion == null)
            throw new RuntimeException("No se aceptan objetos vacios");

        Direccion direccionModificado = direccionRepository.findById(id).
                orElseThrow(() -> new RuntimeException("La direccion no existe"));

        if (direccion.getDepartamento() != null)
            direccionModificado.setDepartamento(direccion.getDepartamento());
        if (direccion.getMunicipio() != null)
            direccionModificado.setMunicipio(direccion.getMunicipio());
        if (direccion.getBarrioColonia() != null)
            direccionModificado.setBarrioColonia(direccion.getBarrioColonia());
        if (direccion.getReferencia() != null)
            direccionModificado.setReferencia(direccion.getReferencia());

        direccionRepository.save(direccionModificado);
    }
}
