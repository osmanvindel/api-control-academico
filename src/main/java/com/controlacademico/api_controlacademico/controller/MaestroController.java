package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Maestro;
import com.controlacademico.api_controlacademico.service.MaestroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class MaestroController {
    private final MaestroService maestroService;

    @Autowired
    public MaestroController(MaestroService maestroService) {
        this.maestroService = maestroService;
    }

    @PostMapping("/maestro")
    public Maestro crearMaestro(@RequestBody Maestro maestro) {
        return maestroService.agregarMaestro(maestro);
    }
}
