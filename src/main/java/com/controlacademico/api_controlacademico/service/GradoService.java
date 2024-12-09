package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Grado;
import com.controlacademico.api_controlacademico.repository.GradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradoService {
    private final GradoRepository gradoRepository;

    @Autowired //Inyeccion de dependencia automatica
    public GradoService(GradoRepository gradoRepository) {
        this.gradoRepository = gradoRepository;
    }

    //Crear
    public void crearGrado(Grado grado) {
        //Objeto vacio
        if (grado.vacio())
            throw new RuntimeException("No se aceptan objetos vacios");
        //Codigo de grado repetido
        if (gradoRepository.existsByCodigo(grado.getCodigo()))
            throw new RuntimeException("Ya existe un grado con este codigo, prueba otro");
        //Nombre de grado repetido
        if (gradoRepository.existsByNombre(grado.getNombre()))
            throw new RuntimeException("Ya existe un grado con este nombre, prueba otro");
        evaluarCapacidad(grado.getCapacidad());

        gradoRepository.save(grado);
    }

    //Editar
    public void editarGrado(int id, Grado grado) {
        //Verificar existencia del grado a editar
        Grado gradoModificado = gradoRepository.findById(id).orElseThrow(() -> new RuntimeException("El grado no existe"));

        //JSON vacio
        if (grado.vacio())
            throw new RuntimeException("No se aceptan objetos vacios");

        if (gradoModificado.getBorrado().equals((byte) 1)) //El grado esta "eliminado"
            throw new RuntimeException("Grado no disponible");

        if (grado.getCodigo() != null) {
            //Codigo de grado repetido
            if (gradoRepository.existsByCodigo(grado.getCodigo()))
                throw new RuntimeException("Ya existe un grado con este codigo, prueba otro");
            gradoModificado.setCodigo(grado.getCodigo());
        }
        if (grado.getNombre() != null) {
            //Nombre de grado repetido
            if (gradoRepository.existsByNombre(grado.getNombre()))
                throw new RuntimeException("Ya existe un grado con este nombre, prueba otro");
            gradoModificado.setNombre(grado.getNombre());
        }
        if (grado.getCapacidad() != null) {
            //Evaluar capacidad
            evaluarCapacidad(grado.getCapacidad());
            gradoModificado.setCapacidad(grado.getCapacidad());
        }
        if (grado.getDescripcion() != null) {
            gradoModificado.setDescripcion(grado.getDescripcion());
        }

        gradoRepository.save(gradoModificado);
    }

    //Buscar (Todos)
    public List<Grado> obtenerGrados() {
        return gradoRepository.findByBorrado((byte) 0);
    }

    //Buscar (Uno)
    public Optional<Grado> obtenerGrado(int id) {
        return gradoRepository.findByIdAndBorrado(id, (byte) 0);
    }

    //Eliminar (eliminacion logica)
    public void eliminarGrado(int id) {
        Grado grado = gradoRepository.findById(id).orElseThrow(() -> new RuntimeException("El grado no existe"));
        if (grado.getBorrado().equals((byte) 1))
            throw new RuntimeException("Grado no disponible");
        grado.setBorrado((byte) 1);
        gradoRepository.save(grado);
    }

    private void evaluarCapacidad(int capacidad) {
        //Si la capacidad del grado es 0
        if (capacidad <= 0)
            throw new RuntimeException("El número debe ser entero positivo");
    }
}
