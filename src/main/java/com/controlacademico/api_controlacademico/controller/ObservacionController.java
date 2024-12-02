package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Observacion;
import com.controlacademico.api_controlacademico.entity.Periodo;
import com.controlacademico.api_controlacademico.service.ObservacionService;
import com.controlacademico.api_controlacademico.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class ObservacionController {
    private final ObservacionService observacionService;

    @Autowired
    public ObservacionController(ObservacionService observacionService) {
        this.observacionService = observacionService;
    }

    @PostMapping("/observacion")
    public Observacion crearObservacion(@RequestBody Observacion observacion) {
        return observacionService.crearObservacion(observacion);
    }
}
