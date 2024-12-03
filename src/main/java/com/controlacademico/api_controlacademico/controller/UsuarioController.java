package com.controlacademico.api_controlacademico.controller;

import com.controlacademico.api_controlacademico.entity.Usuario;
import com.controlacademico.api_controlacademico.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //Crear
    @PostMapping("/usuario")
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioService.crearUsuario(usuario);
            return new ResponseEntity<>("Creado correctamente", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Editar
    @PatchMapping("/usuario/{id}")
    public ResponseEntity<String> editarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        try {
            usuarioService.editarUsuario(id, usuario);
            return new ResponseEntity<>("Editado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //Buscar (Todos)
    @GetMapping("/usuarios")
    public List<Usuario> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    //Buscar (Uno)
    @GetMapping("/usuario/{id}")
    public Optional<Usuario> obtenerUsuario(@PathVariable int id){
        return usuarioService.obtenerUsuario(id);
    }

    //Eliminar (eliminacion logica)
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {
        try {
            usuarioService.elimnarUsuario(id);
            return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
