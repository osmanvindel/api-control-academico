package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Maestro;
import com.controlacademico.api_controlacademico.entity.Persona;
import com.controlacademico.api_controlacademico.repository.MaestroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaestroService {
    private final MaestroRepository maestroRepository;
    private final PersonaService personaService;

    @Autowired //Inyeccion de dependencias
    public MaestroService(MaestroRepository maestroRepository, PersonaService personaService) {
        this.maestroRepository = maestroRepository;
        this.personaService = personaService;
    }

    //Crear
    public void crearMaestro(Maestro maestro) {
        if (maestro == null) //Objeto vacio
            throw new RuntimeException("No se aceptan objetos vacios");
        maestro.setPersona(personaService.crearPersona(maestro.getPersona()));
        maestroRepository.save(maestro);
    }

    //Editar
    public void editarMaestro(int id, Persona persona) {
        if (persona == null) //Objeto vacio
            throw new RuntimeException("No se aceptan objetos vacios");

        //Validar existencia
        Maestro maestroEncontrado = maestroRepository.findByIdAndActivo(id, (byte) 1)
                .orElseThrow(() -> new RuntimeException("El maestro a editar no existe"));

        if (maestroEncontrado.getActivo() == (byte) 0)
            throw new RuntimeException("Maestro no disponible");

        maestroEncontrado.setPersona(personaService.editarPersona(maestroEncontrado.getPersona().getId(), persona));
        maestroRepository.save(maestroEncontrado);
    }

    //Buscar (Todos)
    public List<Maestro> obtenerMaestros() {
        return maestroRepository.findByActivo((byte) 1);
    }

    //Buscar (Uno)
    public Optional<Maestro> obtenerMaestro(int id) {
        return maestroRepository.findByIdAndActivo(id, (byte) 1);
    }

    //Eliminar (eliminacion logica)
    public void eliminarMestro(int id) {
        Maestro maestro = maestroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El maestro no existe"));
        if (maestro.getActivo() == (byte) 0)
            throw new RuntimeException("Maestro no disponible");
        maestro.setActivo((byte) 0);
        maestroRepository.save(maestro);
    }
}
