package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Asistencia;
import com.controlacademico.api_controlacademico.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class AsistenciaController {
    public final AsistenciaService asistenciaService;

    @Autowired
    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    //Crear
    @PostMapping("/asistencia")
    public ResponseEntity<String> crearAsistencia(@RequestBody Asistencia asistencia) {
        try {
            asistenciaService.crearAsistencia(asistencia);
            return new ResponseEntity<>("Registrada correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Editar
    @PatchMapping("/asistencia/{id}")
    public ResponseEntity<String> editarAsistencia(@PathVariable int id, @RequestBody Asistencia asistencia) {
        try {
            asistenciaService.editarAsistencia(id, asistencia);
            return new ResponseEntity<>("Editada correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar (Todos)
    @GetMapping("/asistencias")
    public List<Asistencia> buscarAsistencias() {
        return asistenciaService.buscarAsistencias();
    }

    //Buscar (Uno)
    @GetMapping("/asistencia/{fecha}")
    public Optional<Asistencia> buscarAsistencia(@PathVariable LocalDate fecha) {
        return asistenciaService.buscarAsistenciaPorFecha(fecha);
    }

    //Buscar (antes de)
    @GetMapping("/asistencia/antes/{fecha}")
    public List<Asistencia> buscarAsistenciasAntesDe(@PathVariable LocalDate fecha) {
        return asistenciaService.buscarAsistenciasAntesDe(fecha);
    }

    //Buscar (despues de)
    @GetMapping("/asistencia/despues/{fecha}")
    public List<Asistencia> buscarAsistenciasDespuesDe(@PathVariable LocalDate fecha) {
        return asistenciaService.buscarAsistenciasDespuesDe(fecha);
    }

    //Buscar (entre un rango)
    @GetMapping("/asistencia/entre/{fechaInicio}/{fechaFin}")
    public List<Asistencia> buscarAsistenciasEntre(@PathVariable LocalDate fechaInicio, LocalDate fechaFin) {
        return asistenciaService.buscarAsistenciasEntre(fechaInicio, fechaFin);
    }
}
