package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.PadreFamilia;
import com.controlacademico.api_controlacademico.entity.Persona;
import com.controlacademico.api_controlacademico.service.PadreFamiliaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class PadreFamiliaController {
    private final PadreFamiliaService padreFamiliaService;

    @Autowired
    public PadreFamiliaController(PadreFamiliaService padreFamiliaService) {
        this.padreFamiliaService = padreFamiliaService;
    }

    //Crear
    @PostMapping("/padre-familia")
    public ResponseEntity<String> crearAlumno(@RequestBody PadreFamilia padreFamilia) {
        try {
            padreFamiliaService.crearPadreFamilia(padreFamilia);
            return new ResponseEntity<>("Padre de familia creado", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Editar
    @PatchMapping("/padre-familia/{id}")
    public ResponseEntity<String> editarPadreFamilia(@PathVariable int id, @RequestBody Persona persona) {
        try {
            padreFamiliaService.editarPadreFamilia(id, persona);
            return new ResponseEntity<>("Editado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar (Todos)
    @GetMapping("/padres-familia")
    public List<PadreFamilia> obtenerPadresFamilia() {
        return padreFamiliaService.obtenerPadresFamilia();
    }

    //Buscar (Uno)
    @GetMapping("/padre-familia/{id}")
    public Optional<PadreFamilia> obtenerPadreFamilia(@PathVariable int id) {
        return padreFamiliaService.obtenerPadreFamilia(id);

    }

    //Eliminar (eliminacion logica)
    @DeleteMapping("/padre-familia/{id}")
    public ResponseEntity<String> eliminarAlumno(@PathVariable int id) {
        try {
            padreFamiliaService.eliminarPadreFamilia(id);
            return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
