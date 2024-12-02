//package com.controlacademico.api_controlacademico.service;
//
//import com.controlacademico.api_controlacademico.entity.Alumno;
//import com.controlacademico.api_controlacademico.entity.Persona;
//import com.controlacademico.api_controlacademico.repository.PersonaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service //Indicamos que esta clase es un servicio
//public class PersonaService {
//    //Aqui manejamos la logica de negocio para la entidad (Persona en este caso)
//    private final PersonaRepository personaRepository;
//
//    @Autowired //Spring boot hara una inyeccion de dependencia autoatica cuando sea necesario
//    public PersonaService(PersonaRepository personaRepository) {
//        this.personaRepository = personaRepository;
//    }
//
//    //Funciona
//    @Transactional
//    public Persona crearPersona(Persona persona) {
//        if (persona.getTelefonos() != null) {
//            persona.agregarTelefono(persona.getTelefonos());
//        }
//        //Guarda la entidad persona junto con sus relaciones
//        return personaRepository.save(persona);
//    }
//
//    public List<Persona> obtenerPersonas() {
//        return personaRepository.findAll();
//    }
//
//    public Optional<Persona> obtenerPersonaByCedula(String cedula) {
//        return personaRepository.findByCedula(cedula);
//    }
//}