package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Usuario;
import com.controlacademico.api_controlacademico.repository.RolRepository;
import com.controlacademico.api_controlacademico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    //Crear
    public void crearUsuario(Usuario usuario) {
        //Verificar si el JSON no esta vacio
        if (usuario.vacio()) throw new RuntimeException("No se aceptan objetos vacios");

        //El nombre le pertenece a otro usuario
        if (usuarioRepository.existsByNombre(usuario.getNombre()))
            throw new RuntimeException("El usuario ya existe, prueba con otro");

        //El nuevo correo le pertenece a otro usuario
        if (usuarioRepository.existsByCorreo(usuario.getCorreo()))
            throw new RuntimeException("Correo en uso, prueba con otro");

        //Correo no valido
        if (!usuario.getCorreo().contains("@")) throw new RuntimeException("Correo no valido");

        if(usuario.getRol() == null)
            throw new RuntimeException("Debes de asignar un rol");

        //Encriptar password

        usuarioRepository.save(usuario);
    }

    //Editar
    public void editarUsuario(int id, Usuario usuario) {
        if (usuario.vacio()) //Verificar si el JSON no esta vacio
            throw new RuntimeException("No se aceptan objetos vacios");

        //Verificar existencia del usuario a editar
        Usuario usuarioModificado = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("El usuario no existe"));

        if (usuarioModificado.getActivo().equals((byte) 0)) //El usuario esta "elimiando"
            throw new RuntimeException("Usuario no disponible");

        if (usuario.getNombre() != null) {
            //El nuevo nombre ya le pertenece a otro usuario
            if (usuarioRepository.existsByNombre(usuario.getNombre()))
                throw new RuntimeException("Nombre no disponible, prueba con otro");
            usuarioModificado.setNombre(usuario.getNombre());
        }
        if (usuario.getCorreo() != null) {
            validarCorreo(usuario.getCorreo());
            usuarioModificado.setCorreo(usuario.getCorreo());
        }

        //Editar la password

        if (usuario.getRol() != null) {
            //El rol a asignar no existe
            if (rolRepository.existsById(usuario.getRol().getId()))
                throw new RuntimeException("El rol a asignar no existe");
            usuarioModificado.setRol(usuario.getRol());
        }
        usuarioRepository.save(usuarioModificado);
    }

    //Buscar (Todos)
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findByActivo((byte) 1); //Solo usuario no "eliminados"
    }

    //Buscar (Uno)
    public Optional<Usuario> obtenerUsuario(int id) {
        return usuarioRepository.findByIdAndActivo(id, (byte) 1); //Usuario especifico no eliminado
    }

    //Eliminar (eliminacion logica)
    public void elimnarUsuario(int id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("El usuario no existe"));
        if (usuario.getActivo().equals((byte) 0)) //El usuario ya esta "eliminado"
            throw new RuntimeException("Usuario no disponible");
        usuario.setActivo((byte) 0);
        usuarioRepository.save(usuario);
    }

    private void validarCorreo(String correo) {
        //El nuevo correo ya le pertenece a otro usuario
        if (usuarioRepository.existsByCorreo(correo))
            throw new RuntimeException("Correo en uso, prueba con otro");
        //Correo no valido
        if (correo.contains("@"))
            throw new RuntimeException("El correo no es valido");
    }
}
