package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Alumno;
import com.controlacademico.api_controlacademico.entity.Persona;
import com.controlacademico.api_controlacademico.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class AlumnoController {
    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    //Crear
    @PostMapping("/alumno")
    public ResponseEntity<String> crearAlumno(@RequestBody Alumno alumno) {
        try {
            alumnoService.crearAlumno(alumno);
            return new ResponseEntity<>("Alumno creado", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Editar
    @PatchMapping("/alumno/{id}")
    public ResponseEntity<String> editarAlumno(@PathVariable int id, @RequestBody Persona persona) {
        try {
            alumnoService.editarAlumno(id, persona);
            return new ResponseEntity<>("Editado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar (Todos)
    @GetMapping("/alumnos")
    public List<Alumno> obtenerAlumnos() {
        return alumnoService.obtenerAlumnos();
    }

    //Buscar (Uno)
    @GetMapping("/alumno/{id}")
    public Optional<Alumno> obtenerAlumno(@PathVariable int id) {
        return alumnoService.obtenerAlumno(id);

    }

    //Eliminar (eliminacion logica)
    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<String> eliminarAlumno(@PathVariable int id) {
        try {
            alumnoService.eliminarMestro(id);
            return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
