package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Persona;
import com.controlacademico.api_controlacademico.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/persona")
    public ResponseEntity<String> crearPersona(@RequestBody Persona persona) {
        try {
            personaService.crearPersona(persona);
            return new ResponseEntity<>("Creado correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/personas")
    public List<Persona> obtenerPersonas() {
        return personaService.obtenerPersonas();
    }

    @GetMapping("/persona/{cedula}")
    public Optional<Persona> obtenerPersonaByCedula(@PathVariable String cedula) {
        return personaService.obtenerPersonaByCedula(cedula);
    }
}
