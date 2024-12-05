package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Observacion;
import com.controlacademico.api_controlacademico.entity.Periodo;
import com.controlacademico.api_controlacademico.service.ObservacionService;
import com.controlacademico.api_controlacademico.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class ObservacionController {
    private final ObservacionService observacionService;

    @Autowired
    public ObservacionController(ObservacionService observacionService) {
        this.observacionService = observacionService;
    }

    @PostMapping("/observacion")
    public ResponseEntity<String> crearObservacion(@RequestBody Observacion observacion) {
        try {
            observacionService.crearObservacion(observacion);
            return new ResponseEntity<>("Creada correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/observacion/{id}")
    public ResponseEntity<String> crearObservacion(@PathVariable int id, @RequestBody Observacion observacion) {
        try {
            observacionService.editarObservacion(id, observacion);
            return new ResponseEntity<>("Creada correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
