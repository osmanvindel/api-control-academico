package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Alumno;
import com.controlacademico.api_controlacademico.entity.Persona;
import com.controlacademico.api_controlacademico.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoService {
    private final AlumnoRepository alumnoRepository;

    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Transactional
    public Alumno crearAlumno(Alumno alumno) {
        alumno.getPersona().agregarTelefono(alumno.getPersona().getTelefonos());
        alumno.setPersona(alumno.getPersona());
        //Prueba, fk_matricula_id sale null
        //alumno.getMatricula().agregarAlumno(alumno.getMatricula().getAlumnos());
        //alumno.setMatricula(alumno.getMatricula());
       return alumnoRepository.save(alumno);
    }

    public List<Alumno> obtenerAlumnos() {
        return alumnoRepository.findAll();
    }
}
