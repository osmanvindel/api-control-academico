package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Calificacion;
import com.controlacademico.api_controlacademico.service.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CalificacionCotroller {
    private final CalificacionService calificacionService;

    @Autowired
    public CalificacionCotroller(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    //Crear
    @PostMapping("/calificacion")
    public ResponseEntity<String> agregarCalificacion(@RequestBody Calificacion calificacion){
        try{
            calificacionService.registrarCalificacion(calificacion);
            return new ResponseEntity<>("Registrada correctamente", HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/calificacion/{id}")
    public ResponseEntity<String> editarCalificacion(@PathVariable int id,@RequestBody Calificacion calificacion){
        try{
            calificacionService.editarCalificacion(id, calificacion);
            return new ResponseEntity<>("Editada correctamente", HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/calificaciones")
    public List<Calificacion> obtenerCalificaciones() {
        return calificacionService.obtenerCalificaciones();
    }
}
