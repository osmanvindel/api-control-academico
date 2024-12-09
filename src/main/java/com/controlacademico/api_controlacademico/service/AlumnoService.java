package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Alumno;
import com.controlacademico.api_controlacademico.entity.Persona;
import com.controlacademico.api_controlacademico.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
    private final AlumnoRepository alumnoRepository;
    private final PersonaService personaService;

    @Autowired
    public AlumnoService(AlumnoRepository alumnoRepository, PersonaService personaService) {
        this.alumnoRepository = alumnoRepository;
        this.personaService = personaService;
    }

    //Crear
    public void crearAlumno(Alumno alumno) {
        if (alumno == null) //Objeto vacio
            throw new RuntimeException("No se aceptan objetos vacios");
        alumno.setPersona(personaService.crearPersona(alumno.getPersona()));
        alumnoRepository.save(alumno);
    }

    //Editar
    public void editarAlumno(int id, Persona persona) {
        if (persona == null) //Objeto vacio
            throw new RuntimeException("No se aceptan objetos vacios");

        //Validar existencia
        Alumno alumnoEncontrado = alumnoRepository.findByIdAndActivo(id, (byte) 1)
                .orElseThrow(() -> new RuntimeException("El alumno a editar no existe"));

        if (alumnoEncontrado.getActivo() == (byte) 0)
            throw new RuntimeException("Maestro no disponible");

        alumnoEncontrado.setPersona(personaService.editarPersona(alumnoEncontrado.getPersona().getId(), persona));
        alumnoRepository.save(alumnoEncontrado);
    }

    //Buscar (Todos)
    public List<Alumno> obtenerAlumnos() {
        return alumnoRepository.findByActivo((byte) 1);
    }

    //Buscar (Uno)
    public Optional<Alumno> obtenerAlumno(int id) {
        return alumnoRepository.findByIdAndActivo(id, (byte) 1);
    }

    //Eliminar (eliminacion logica)
    public void eliminarMestro(int id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El alumno no existe"));
        if (alumno.getActivo() == (byte) 0)
            throw new RuntimeException("Maestro no disponible");
        alumno.setActivo((byte) 0);
        alumnoRepository.save(alumno);
    }
}
