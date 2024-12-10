package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Matricula;
import com.controlacademico.api_controlacademico.repository.MatriculaRepository;
import com.controlacademico.api_controlacademico.validations.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {
    private final MatriculaRepository matriculaRepository;

    @Autowired
    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    //Crear
    public void crearMatricula(Matricula matricula) {
        if (matricula.vacio()) //JSON vacio
            throw new RuntimeException("No se aceptan objetos vacios");
        Validaciones.validarFecha(matricula.getFecha().toString());
        evaluarPrecio(matricula.getPrecio());
        matriculaRepository.save(matricula);
    }

    //Editar
    public void editarMatricula(int id, Matricula matricula) {
        if (matricula.vacio()) //JSON vacio
            throw new RuntimeException("No se aceptan objetos vacios");

        //Existencia
        Matricula matriculaModificada = matriculaRepository.findById(id).orElseThrow(() -> new RuntimeException("La matricula no existe"));

        //Matricula cerrada
        if (matriculaModificada.getAbierta() == (byte) 0)
            throw new RuntimeException("Matricula no disponible");

        if (matricula.getPrecio() != null) {
            evaluarPrecio(matricula.getPrecio()); //Evaluar nuevo precio
            matriculaModificada.setPrecio(matricula.getPrecio());
        }
        if (matricula.getComentarios() != null)
            matriculaModificada.setComentarios(matricula.getComentarios());

        matriculaRepository.save(matriculaModificada);
    }

    //Buscar (Todos)
    public List<Matricula> buscarMatriculas() {
        return matriculaRepository.findByAbierta((byte) 1);
    }

    //Buscar por fecha
    public Optional<Matricula> buscarMatriculaPorFecha(LocalDate fecha) {
        return matriculaRepository.findByFecha(fecha);
    }

    //Buscar antes de (todas las coincidencias)
    public List<Matricula> buscarMatriculaAntesDe(LocalDate fecha) {
        return matriculaRepository.findByFechaBefore(fecha);
    }

    //Buscar despues de (todas las coincidencias)
    public List<Matricula> buscarMatriculaDespuesDe(LocalDate fecha) {
        return matriculaRepository.findByFechaAfter(fecha);
    }

    //Buscar entre un rango (todas las coincidencias)
    public List<Matricula> buscarMatriculasEntre(LocalDate fechaInicio, LocalDate fechaFin) {
        return matriculaRepository.findByFechaBetween(fechaInicio, fechaFin);
    }

    //Eliminar (eliminacion logica)
    public void cerrarMatricula(int id) {
        Matricula matricula = matriculaRepository.findById(id).orElseThrow(() -> new RuntimeException("La matricula no existe")); //Existencia
        if (matricula.getAbierta().equals((byte) 0)) //Ya esta cerrada
            throw new RuntimeException("Matricula no disponible");
        matricula.setAbierta((byte) 0);
        matriculaRepository.save(matricula);
    }

    private void evaluarPrecio(double precio) {
        if (precio < 0) //Precios negativos
            throw new RuntimeException("El precio debe de ser positivo");
    }
}
