package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Matricula;
import com.controlacademico.api_controlacademico.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class MatriculaController {
    private final MatriculaService matriculaService;

    @Autowired
    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    //Crear
    @PostMapping("/matricula")
    public ResponseEntity<String> crearMatricula(@RequestBody Matricula matricula) {
        try {
            matriculaService.crearMatricula(matricula);
            System.out.println("ANTES DE ENVIAR JSON A SERVICE" + matricula);
            return new ResponseEntity<>("Creada correctamente", HttpStatus.CREATED);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Editar
    @PatchMapping("/matricula/{id}")
    public ResponseEntity<String> editarMatricula(@PathVariable int id, @RequestBody Matricula matricula) {
        try {
            matriculaService.editarMatricula(id, matricula);
            return new ResponseEntity<>("Editada correctamente", HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar ..?

    //Eliminar (eliminacion logica)
    @DeleteMapping("/matricula/{id}")
    public ResponseEntity<String> cerrarMatricula(@PathVariable int id) {
        try {
            matriculaService.cerrarMatricula(id);
            return new ResponseEntity<>("Cerrada correctamente", HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
