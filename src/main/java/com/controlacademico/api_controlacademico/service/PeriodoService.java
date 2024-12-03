package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Periodo;
import com.controlacademico.api_controlacademico.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodoService {
    private final PeriodoRepository periodoRepository;

    @Autowired
    public PeriodoService(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    //Crear
    public void crearPeriodo(Periodo periodo) {
        if (periodo.vacio()) //JSON vacio
            throw new RuntimeException("No se aceptan periodos vacios");
        if (periodoRepository.existsByCodigo(periodo.getCodigo())) //Codigo repetido
            throw new RuntimeException("Ya existe un periodo con ese codigo, prueba con otro");
        periodoRepository.save(periodo);
    }

    //Editar
    public void editarPeriodo(int id, Periodo periodo) {
        if (periodo.vacio()) //JSON vacio
            throw new RuntimeException("No se aceptan objetos vacios");

        //Existencia
        Periodo periodoModificado = periodoRepository.findById(id).orElseThrow(() -> new RuntimeException("El periodo no existe"));

        if (periodoModificado.getAbierto().equals((byte) 0)) //Periodo cerrado
            throw new RuntimeException("Periodo no disponible");

        if (periodo.getCodigo() != null) {
            if (periodoRepository.existsByCodigo(periodo.getCodigo())) //Codigo repetido
                throw new RuntimeException("El codigo ya esta en uso, prueba uno diferente");
            periodoModificado.setCodigo(periodo.getCodigo());
        }

        if (periodo.getFechaInicio() != null)
            periodoModificado.setFechaInicio(periodo.getFechaInicio());

        if (periodo.getFechaFin() != null)
            periodoModificado.setFechaFin(periodo.getFechaFin());

        periodoRepository.save(periodoModificado);
    }

    //Buscar (Todos)
    public List<Periodo> obtenerPeriodos() {
        return periodoRepository.findByAbierto((byte) 1); //Solo periodos abierto
    }

    //Buscar (Uno)
    public Optional<Periodo> obtenerPeriodo(int id) {
        return periodoRepository.findByIdAndAbierto(id, (byte) 1); //Periodo especifico abierto
    }

    //Eliminar (eliminacion logica)
    public void cerrarPeriodo(int id) {
        Periodo periodo = periodoRepository.findById(id).orElseThrow(() -> new RuntimeException("El periodo no existe"));
        if (periodo.getAbierto().equals((byte) 0)) //El periodo ya esta cerrado
            throw new RuntimeException("Periodo no disponible");
        periodo.setAbierto((byte) 0);
        periodoRepository.save(periodo);
    }
}
