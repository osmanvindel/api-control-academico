package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Asignatura;
import com.controlacademico.api_controlacademico.repository.AsignaturaRepository;
import com.controlacademico.api_controlacademico.repository.GradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaService {
    private final AsignaturaRepository asignaturaRepository;
    private final GradoRepository gradoRepository;

    @Autowired
    public AsignaturaService(AsignaturaRepository asignaturaRepository, GradoRepository gradoRepository) {
        this.asignaturaRepository = asignaturaRepository;
        this.gradoRepository = gradoRepository;
    }

    //Crear
    public void crearAsignatura(Asignatura asignatura) {
        if (asignatura.vacio()) //JSON vacio
            throw new RuntimeException("No se aceptan objetos vacios");
        if (asignaturaRepository.existsByCodigo(asignatura.getCodigo())) //Codigo repetido
            throw new RuntimeException("Ya existe una asignatura con este codigo, prueba con otro");
        if (asignatura.getGrado() == null) //No se especifica el grado
            throw new RuntimeException("Debes de asignar la asignatura a un grado");
        asignaturaRepository.save(asignatura);
    }

    //Editar
    public void ediarAsignatura(int id, Asignatura asignatura) {
        if (asignatura.vacio() && asignatura.getGrado() == null) //JSON vacio
            throw new RuntimeException("No se aceptan objetos vacios");

        Asignatura asignaturaMdoficada = asignaturaRepository.findById(id).orElseThrow(() -> new RuntimeException("La asignatura no existe"));

        if (asignaturaMdoficada.getDisponible() == (byte) 0) //Asignatura "eliminada"
            throw new RuntimeException("Asignatura no dispobile");

        if (asignatura.getCodigo() != null) {
            if (asignaturaMdoficada.getCodigo().equals(asignatura.getCodigo())) //Codigo repetido
                throw new RuntimeException("Ya existe una asignatura con este codigo, prueba con otro");
            asignaturaMdoficada.setCodigo(asignatura.getCodigo());
        }
        if (asignatura.getNombre() != null)
            asignaturaMdoficada.setNombre(asignatura.getNombre());

        if (asignatura.getDescripcion() != null)
            asignaturaMdoficada.setDescripcion(asignatura.getDescripcion());

        if (asignatura.getGrado() != null) {
            if (!gradoRepository.existsById(asignatura.getGrado().getId()))
                throw new RuntimeException("El grado a asignar no existe"); //Grado no existe
            asignaturaMdoficada.setGrado(asignatura.getGrado());
        }

        asignaturaRepository.save(asignaturaMdoficada);
    }

    //Buscar (Todos)
    public List<Asignatura> obtenerAsignaturas() {
        return asignaturaRepository.findByDisponible((byte) 1); //Solo asignaturas disponibles
    }

    //Buscar (Uno)
    public Optional<Asignatura> obtenerAsignatura(int id) {
        return asignaturaRepository.findByIdAndDisponible(id, (byte) 1); //Asignatura especifica disponible
    }

    //Eliminar (eliminacion logica)
    public void eliminarAsignatura(int id) {
        Asignatura asignatura = asignaturaRepository.findById(id).orElseThrow(() -> new RuntimeException("La asignatura no existe"));
        if (asignatura.getDisponible().equals((byte) 0)) //La asignatura no esta disponible
            throw new RuntimeException("Asignatura no disponible");
        asignatura.setDisponible((byte) 0);
        asignaturaRepository.save(asignatura);
    }
}
