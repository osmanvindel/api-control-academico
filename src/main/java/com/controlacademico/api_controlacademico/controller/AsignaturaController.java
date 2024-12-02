package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Asignatura;
import com.controlacademico.api_controlacademico.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class AsignaturaController {
    private final AsignaturaService asignaturaService;

    @Autowired
    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @PostMapping("/asignatura")
    public Asignatura crearAsignatura(@RequestBody Asignatura asignatura) {
        return asignaturaService.crearAsignatura(asignatura);
    }
}
