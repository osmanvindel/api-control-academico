package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Alumno;
import com.controlacademico.api_controlacademico.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class AlumnoController {
    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping("/alumno")
    public Alumno crearAlumno(@RequestBody Alumno alumno) {
        return alumnoService.crearAlumno(alumno);
    }

    @GetMapping("/alumnos")
    public List<Alumno> obtenerAlumnos() {
        return alumnoService.obtenerAlumnos();
    }
}
