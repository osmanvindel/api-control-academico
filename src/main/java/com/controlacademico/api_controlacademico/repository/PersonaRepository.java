package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //Indicar que es un repositorio de alguna entidad
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    //Esta interfaz abstrae las formas en como se puede manipular (CRUD por ejemplo) la entidad (Persona en este caso)
    //Al heredar de la interfaz JpaRepository, heredamos metodos como:
    // save() - guardar, actualizar
    // findById() - buscar por un campo
    // findAll() - buscar todos los elementos
    // deleteById() - eliminar por un campo
    //Tambien podemos crear nuestros metodos personalizados.
    Optional<Persona> findByCedula(String cedula);

    boolean existsByCedula(String cedula);

    boolean existsByCorreo(String correo);
}
