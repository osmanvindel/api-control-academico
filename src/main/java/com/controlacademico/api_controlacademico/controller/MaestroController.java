package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Maestro;
import com.controlacademico.api_controlacademico.entity.Persona;
import com.controlacademico.api_controlacademico.service.MaestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class MaestroController {
    private final MaestroService maestroService;

    @Autowired
    public MaestroController(MaestroService maestroService) {
        this.maestroService = maestroService;
    }

    //Crear
    @PostMapping("/maestro")
    public ResponseEntity<String> crearMaestro(@RequestBody Maestro maestro) {
        try {
            maestroService.crearMaestro(maestro);
            return new ResponseEntity<>("Maestro creado", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Editar
    @PatchMapping("/maestro/{id}")
    public ResponseEntity<String> editarMaestro(@PathVariable int id, @RequestBody Persona persona) {
        try {
            maestroService.editarMaestro(id, persona);
            return new ResponseEntity<>("Editado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar (Todos)
    @GetMapping("/maestros")
    public List<Maestro> obtenerMaestros() {
        return maestroService.obtenerMaestros();
    }

    //Buscar (Uno)
    @GetMapping("/maestro/{id}")
    public Optional<Maestro> obtenerMaestro(@PathVariable int id) {
        return maestroService.obtenerMaestro(id);
    }

    //Eliminar (eliminacion logica)
    @DeleteMapping("/maestro/{id}")
    public ResponseEntity<String> eliminarMaestro(@PathVariable int id) {
        try {
            maestroService.eliminarMestro(id);
            return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
