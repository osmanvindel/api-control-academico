package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Persona;
import com.controlacademico.api_controlacademico.entity.Telefono;
import com.controlacademico.api_controlacademico.repository.PersonaRepository;
import com.controlacademico.api_controlacademico.repository.TelefonoRepository;
import com.controlacademico.api_controlacademico.validations.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelefonoService {
    private final TelefonoRepository telefonoRepository;
    private final PersonaRepository personaRepository;

    @Autowired
    public TelefonoService(TelefonoRepository telefonoRepository, PersonaRepository personaRepository) {
        this.telefonoRepository = telefonoRepository;
        this.personaRepository = personaRepository;
    }

    //Agregar telefonos
    public void agregarTelefono(int id, List<Telefono> telefonos) {
        if (telefonos.isEmpty())
            throw new RuntimeException("No se aceptan listas vacias");

        Validaciones.validarTelefonos(telefonos);

        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La persona no existe"));

        //Verificar si alguno de los telefonos a agregar ya existe
        for (Telefono telefonoNuevo : telefonos) {
            for (Telefono telefonoExistente : persona.getTelefonos()) {
                if (telefonoExistente.getNumero().equals(telefonoNuevo.getNumero()))
                    throw new RuntimeException("La persona ya tiene este telefono: " + telefonoNuevo.getNumero());
            }
        }

        //Guardar nuevos telefonos
        for (Telefono telefono : telefonos) {
            if (telefonoRepository.existsByNumero(telefono.getNumero()))
                throw new RuntimeException("El telefono ya existe");
            telefono.setPersona(persona);
            persona.getTelefonos().add(telefono);
        }
        personaRepository.save(persona);
    }

    //Editar
    public void editarTelefono(int id, Telefono telefono) {
        if (telefono == null)
            throw new RuntimeException("No se aceptan objetos vacios");

        Telefono telefonoModificado = telefonoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El telefono no existe"));

        if (telefonoRepository.existsByNumero(telefono.getNumero()))
            throw new RuntimeException("El telefono ya esta en uso");
        Validaciones.validarTelefono(telefono.getNumero());

        telefonoModificado.setNumero(telefono.getNumero());
        telefonoRepository.save(telefonoModificado);
    }

    public void existeTelefono(List<Telefono> telefonos) {
        for (Telefono telefono : telefonos) {
            if (telefonoRepository.existsByNumero(telefono.getNumero()))
                throw new RuntimeException("El numero de telefono ya existe");
        }
    }
}
