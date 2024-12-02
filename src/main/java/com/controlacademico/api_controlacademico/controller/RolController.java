package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Rol;
import com.controlacademico.api_controlacademico.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class RolController {
    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    //Crear rol
    @PostMapping("/rol")
    public ResponseEntity<String> crearRol(@RequestBody Rol rol) {
        try {
            rolService.crearRol(rol);
            return new ResponseEntity<>("Creado correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Editar rol
    @PatchMapping("/rol/{id}")
    public ResponseEntity<String> editarRol(@PathVariable int id, @RequestBody Rol rol) {
        try {
            rolService.editarRol(id, rol);
            return new ResponseEntity<>("Editado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar (Todos)
    @GetMapping("/roles")
    public List<Rol> obtenerRoles() {
        return rolService.obtenerRoles();
    }

    //Buscar (Uno)
    @GetMapping("/rol/{id}")
    public Optional<Rol> obtenerRol(@PathVariable int id) {
            return rolService.obtenerRol(id);
    }
}
