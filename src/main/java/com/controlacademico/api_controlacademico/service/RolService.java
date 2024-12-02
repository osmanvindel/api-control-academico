package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Rol;
import com.controlacademico.api_controlacademico.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class RolService {
    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    //Crear
    public void crearRol(Rol rol) {
        //JSON vacio
        if (rol == null) throw new RuntimeException("No se aceptan objetos vacios");
        //El rol tiene un nombre que ya le pertenece a otro rol
        if (rolRepository.existsByNombre(rol.getNombre())) throw new RuntimeException("El rol ya existe");

        rolRepository.save(rol);
    }

    //Editar
    public void editarRol(int id, Rol rol) {
        //Verificar existencia del rol a editar
        Rol rolModificado = rolRepository.findById(id).orElseThrow(() -> new RuntimeException("El rol a editar no existe"));

        //No se especifica el nuevo nombre/objeto vacio
        if (rol.getNombre() == null) throw new RuntimeException("No se aceptan objetos vacios");

        //El nuevo nombre ya le pertenece a otro rol (o esta tratando de guardar el mismo nombre)
        if (rolRepository.existsByNombre(rol.getNombre()))
            throw new RuntimeException("El rol ya existe, prueba otro nombre");

        rolModificado.setNombre(rol.getNombre());
        rolRepository.save(rolModificado);
    }

    //Buscar (Todos)
    public List<Rol> obtenerRoles() {
        return rolRepository.findAll();
    }

    //Buscar (Uno)
    public Optional<Rol> obtenerRol(int id) {
        return rolRepository.findById(id);
    }
}
