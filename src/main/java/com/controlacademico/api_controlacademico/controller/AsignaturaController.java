package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Asignatura;
import com.controlacademico.api_controlacademico.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class AsignaturaController {
    private final AsignaturaService asignaturaService;

    @Autowired
    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    //Crear
    @PostMapping("/asignatura")
    public ResponseEntity<String> crearAsignatura(@RequestBody Asignatura asignatura) {
        try {
            asignaturaService.crearAsignatura(asignatura);
            return new ResponseEntity<>("Creado correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Editar
    @PatchMapping("/asignatura/{id}")
    public ResponseEntity<String> editarAsignatura(@PathVariable int id, @RequestBody Asignatura asignatura) {
        try {
            asignaturaService.ediarAsignatura(id, asignatura);
            return new ResponseEntity<>("Editada correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar (Todos)
    @GetMapping("/asignaturas")
    public List<Asignatura> obtenerAsignaturas() {
        return asignaturaService.obtenerAsignaturas();
    }

    //Buscar (Uno)
    @GetMapping("/asignatura/{id}")
    public Optional<Asignatura> obtenerAsignatura(@PathVariable int id) {
        return asignaturaService.obtenerAsignatura(id);
    }

    //Eliminar (eliminacion logica)
    @DeleteMapping("/asignatura/{id}")
    public ResponseEntity<String> eliminarAsignatura(@PathVariable int id) {
        try {
            asignaturaService.eliminarAsignatura(id);
            return new ResponseEntity<>("Eliminada correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
