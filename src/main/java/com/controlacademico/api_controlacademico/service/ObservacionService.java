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

    //Crear
    public void crearObservacion(Observacion observacion) {
        if (observacion.vacio()) //JSON vacio
            throw new RuntimeException("No se aceptan objetos vacios");
        //Evaluar el formato de la fecha
        observacionRepository.save(observacion);
    }

    //Editar
    public void editarObservacion(int id, Observacion observacion) {
        if (observacion.getComentario() == null) //Comentario vacio
            throw new RuntimeException("No se aceptan objetos vacios");

        Observacion observacionModificada = observacionRepository.findById(id).orElseThrow(() -> new RuntimeException("La observacion a editar no existe"));
        
        observacionModificada.setComentario(observacion.getComentario());
        observacionRepository.save(observacionModificada);
    }

    //...
}
