package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Grado;
import com.controlacademico.api_controlacademico.service.GradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class GradoController {
    private final GradoService gradoService;

    @Autowired
    public GradoController(GradoService gradoService) {
        this.gradoService = gradoService;
    }

    //Crear
    @PostMapping("/grado")
    public ResponseEntity<String> crearGrado(@RequestBody Grado grado) {
        try {
            gradoService.crearGrado(grado);
            return new ResponseEntity<>("Grado creado correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Editar
    @PatchMapping("/grado/{id}")
    public ResponseEntity<String> editarGrado(@PathVariable int id, @RequestBody Grado grado) {
        try {
            gradoService.editarGrado(id, grado);
            return new ResponseEntity<>("Editado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar (Todos)
    @GetMapping("/grados")
    public List<Grado> obtenerGrados() {
        return gradoService.obtenerGrados();
    }

    //Buscar (Uno)
    @GetMapping("/grado/{id}")
    public Optional<Grado> obtenerGrado(@PathVariable int id) {
        return gradoService.obtenerGrado(id);
    }

    //Eliminar (eliminacion logica)
    @DeleteMapping("/grado/{id}")
    public ResponseEntity<String> eliminarGrado(@PathVariable int id) {
        try {
            gradoService.eliminarGrado(id);
            return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
