package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Persona;
import com.controlacademico.api_controlacademico.repository.PersonaRepository;
import com.controlacademico.api_controlacademico.validations.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //Indicamos que esta clase es un servicio
public class PersonaService {
    //Aqui manejamos la logica de negocio para la entidad (Persona en este caso)
    private final PersonaRepository personaRepository;
    private final TelefonoService telefonoService;
    private final DireccionService direccionService;

    @Autowired //Spring boot hara una inyeccion de dependencia autoatica cuando sea necesario
    public PersonaService(PersonaRepository personaRepository, TelefonoService telefonoService, DireccionService direccionService) {
        this.personaRepository = personaRepository;
        this.telefonoService = telefonoService;
        this.direccionService = direccionService;
    }

    //Crear
    public Persona crearPersona(Persona persona) {
        if (persona.vacio() && persona.getTelefonos() == null && persona.getDireccion() == null)
            throw new RuntimeException("No se permiten objetos vacios");
        if (persona.getTelefonos() == null)
            throw new RuntimeException("Telefono vacio");
        if (persona.getDireccion() == null)
            throw new RuntimeException("Direccion vacia");
        validarCampos(persona);
        return personaRepository.save(persona);
    }

    //Editar
    public Persona editarPersona(int id, Persona persona) {
        if (persona == null)
            throw new RuntimeException("Objeto persona vacio");

        Persona personaModificada = personaRepository.findById(id).orElseThrow(() -> new RuntimeException("La persona no existe"));

        if (persona.getCedula() != null) { //Cedula repetida
            if (personaRepository.existsByCedula(persona.getCedula())) //Cedula repetida
                throw new RuntimeException("Esta cedula ya esta registrada");
            Validaciones.validarCedula(persona.getCedula());
            personaModificada.setCedula(persona.getCedula());
        }

        if (persona.getNombre() != null) {
            Validaciones.validarNombre(persona.getNombre());
            personaModificada.setNombre(persona.getNombre());
        }

        if (persona.getApellido() != null) {
            Validaciones.validarApellido(persona.getApellido());
            personaModificada.setApellido(persona.getApellido());
        }

        if (persona.getCorreo() != null) {
            if (personaRepository.existsByCorreo(persona.getCorreo())) //Correo repetido
                throw new RuntimeException("Este correo ya esta en uso");
            Validaciones.validarCorreo(persona.getCorreo());
            personaModificada.setCorreo(persona.getCorreo());
        }

        if (persona.getDireccion() != null) {
            direccionService.editarDireccion(personaModificada.getDireccion().getId(), persona.getDireccion());
        }
        return personaRepository.save(personaModificada);
    }

    //Buscar (Todos)
    public List<Persona> obtenerPersonas() {
        return personaRepository.findAll();
    }

    //Buscar (Uno)
    public Optional<Persona> obtenerPersonaByCedula(String cedula) {
        return personaRepository.findByCedula(cedula);
    }

    private void validarCampos(Persona persona) {
        //Validar cedula
        Validaciones.validarCedula(persona.getCedula());
        //Validar Nombre
        Validaciones.validarNombre(persona.getNombre());
        //Validar Apellido
        Validaciones.validarApellido(persona.getApellido());
        //Validar correo
        Validaciones.validarCorreo(persona.getCorreo());

        //Campos duplicados
        if (personaRepository.existsByCedula(persona.getCedula()))
            throw new RuntimeException("Esta cedula ya esta registrada");
        if (personaRepository.existsByCorreo(persona.getCorreo()))
            throw new RuntimeException("Este correo ya esta en uso");
        //Validar telefonos
        telefonoService.existeTelefono(persona.getTelefonos());
        Validaciones.validarTelefonos(persona.getTelefonos());
    }
}