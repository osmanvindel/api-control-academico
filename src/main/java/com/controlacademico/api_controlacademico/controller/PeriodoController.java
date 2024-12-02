package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Periodo;
import com.controlacademico.api_controlacademico.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class PeriodoController {
    private final PeriodoService periodoService;

    @Autowired
    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @PostMapping("/periodo")
    public Periodo crearPeriodo(@RequestBody Periodo periodo) {
        return periodoService.crearPeriodo(periodo);
    }
}
