package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Periodo;
import com.controlacademico.api_controlacademico.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class PeriodoController {
    private final PeriodoService periodoService;

    @Autowired
    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    //Crear
    @PostMapping("/periodo")
    public ResponseEntity<String> crearPeriodo(@RequestBody Periodo periodo) {
        try {
            periodoService.crearPeriodo(periodo);
            return new ResponseEntity<>("Creado correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Editar
    @PatchMapping("/periodo/{id}")
    public ResponseEntity<String> crearPeriodo(@PathVariable int id, @RequestBody Periodo periodo) {
        try {
            periodoService.editarPeriodo(id, periodo);
            return new ResponseEntity<>("Editado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar (Todos)
    @GetMapping("/periodos")
    public List<Periodo> obtenerPeriodos() {
        return periodoService.obtenerPeriodos();
    }

    //Buscar (Uno)
    @GetMapping("/periodo/{id}")
    public Optional<Periodo> obtenerPeriodo(@PathVariable int id) {
        return periodoService.obtenerPeriodo(id);
    }

    //Eliminar (eliminacion logica)
    @DeleteMapping("/periodo/{id}")
    public ResponseEntity<String> cerrarPeriodo(@PathVariable int id) {
        try {
            periodoService.cerrarPeriodo(id);
            return new ResponseEntity<>("Cerrado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}