package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.PadreFamilia;
import com.controlacademico.api_controlacademico.entity.Persona;
import com.controlacademico.api_controlacademico.repository.PadreFamiliaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PadreFamiliaService {
    private final PadreFamiliaRepository padreFamiliaRepository;
    private final PersonaService personaService;

    @Autowired
    public PadreFamiliaService(PadreFamiliaRepository padreFamiliaRepository, PersonaService personaService) {
        this.padreFamiliaRepository = padreFamiliaRepository;
        this.personaService = personaService;
    }

    //Crear
    public void crearPadreFamilia(PadreFamilia padreFamilia) {
        if (padreFamilia == null) //Objeto vacio
            throw new RuntimeException("No se aceptan objetos vacios");
        padreFamilia.setPersona(personaService.crearPersona(padreFamilia.getPersona()));
        padreFamiliaRepository.save(padreFamilia);
    }

    //Editar
    public void editarPadreFamilia(int id, Persona persona) {
        if (persona == null) //Objeto vacio
            throw new RuntimeException("No se aceptan objetos vacios");

        //Validar existencia
        PadreFamilia padreFamiliaEncontrado = padreFamiliaRepository.findByIdAndActivo(id, (byte) 1)
                .orElseThrow(() -> new RuntimeException("El maestro a editar no existe"));

        if (padreFamiliaEncontrado.getActivo() == (byte) 0)
            throw new RuntimeException("Maestro no disponible");

        padreFamiliaEncontrado.setPersona(personaService.editarPersona(padreFamiliaEncontrado.getPersona().getId(), persona));
        padreFamiliaRepository.save(padreFamiliaEncontrado);
    }

    //Buscar (Todos)
    public List<PadreFamilia> obtenerPadresFamilia() {
        return padreFamiliaRepository.findByActivo((byte) 1);
    }

    //Buscar (Uno)
    public Optional<PadreFamilia> obtenerPadreFamilia(int id) {
        return padreFamiliaRepository.findByIdAndActivo(id, (byte) 1);
    }

    //Evaluar la eliminacion
    //Eliminar (eliminacion logica)
    public void eliminarPadreFamilia(int id) {
        PadreFamilia padreFamilia = padreFamiliaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El padre de familia no existe"));
        if (padreFamilia.getActivo() == (byte) 0)
            throw new RuntimeException("Padre de familia no disponible");
        padreFamilia.setActivo((byte) 0);
        padreFamiliaRepository.save(padreFamilia);
    }
}
