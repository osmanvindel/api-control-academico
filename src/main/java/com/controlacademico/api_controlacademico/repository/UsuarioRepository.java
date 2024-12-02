package com.controlacademico.api_controlacademico.repository;

import com.controlacademico.api_controlacademico.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByNombre(String nombre);
    boolean existsByCorreo(String nombre);
    List<Usuario> findByActivo(byte activo);
    Optional<Usuario> findByIdAndActivo(int id, byte activo);
}
