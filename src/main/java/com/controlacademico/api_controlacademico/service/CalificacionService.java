package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Calificacion;
import com.controlacademico.api_controlacademico.repository.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService {
    private final CalificacionRepository calificacionRepository;

    @Autowired
    public CalificacionService(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    //Crear
    public void registrarCalificacion(Calificacion calificacion) {
        if (calificacion == null)
            throw new RuntimeException("No se permiten objetos vacios");
        if (calificacion.vacio())
            throw new RuntimeException("No se aceptan campos vacios");
        if (calificacion.getPeriodo() == null)
            throw new RuntimeException("Objeto periodo vacio");
        if (calificacion.getAlumno() == null)
            throw new RuntimeException("Objeto alumno vacio");
        if (calificacion.getAsignatura() == null)
            throw new RuntimeException("Objeto asignatura vacio");
        if (calificacion.getNota() < 0) //Nota negativa
            throw new RuntimeException("El nota no puede ser negativa");
        calificacionRepository.save(calificacion);
    }

    //Editar
    public void editarCalificacion(int id, Calificacion calificacion) {
        if (calificacion == null)
            throw new RuntimeException("No se permiten objetos vacios");

        Calificacion calificacionModificada = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificacion no encontrada"));

        if (calificacion.getPeriodo() != null)
            calificacionModificada.setPeriodo(calificacion.getPeriodo());

        if (calificacion.getAlumno() != null)
            calificacionModificada.setAlumno(calificacion.getAlumno());

        if (calificacion.getAsignatura() != null)
            calificacionModificada.setAsignatura(calificacion.getAsignatura());

        if (calificacion.getNota() != null) {
            if (calificacion.getNota() < 0) //Nota negativa
                throw new RuntimeException("El nota no puede ser negativa");
            calificacionModificada.setNota(calificacion.getNota());
        }

        calificacionRepository.save(calificacionModificada);
    }

    //Buscar por alumnos?

    //Buscar (Todas)
    public List<Calificacion> obtenerCalificaciones() {
        return calificacionRepository.findAll();
    }
}
