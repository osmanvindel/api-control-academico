package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Telefono;
import com.controlacademico.api_controlacademico.service.TelefonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TelefonoController {
    private final TelefonoService telefonoService;

    @Autowired
    public TelefonoController(TelefonoService telefonoService) {
        this.telefonoService = telefonoService;
    }

    @PatchMapping("/telefono/nuevo/{id}")
    public ResponseEntity<String> agregarTelefono(@PathVariable int id, @RequestBody List<Telefono> telefonos) {
        try {
            telefonoService.agregarTelefono(id, telefonos);
            return new ResponseEntity<>("Agregado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/telefono/{id}")
    public ResponseEntity<String> editarTelefono(@PathVariable int id, @RequestBody Telefono telefono) {
        try {
            telefonoService.editarTelefono(id, telefono);
            return new ResponseEntity<>("Editado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
