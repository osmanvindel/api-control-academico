package com.controlacademico.api_controlacademico.validations;

import com.controlacademico.api_controlacademico.entity.Telefono;

import java.util.List;

public class Validaciones {

    public static void validarCedula(String cedula) {
        if (!cedula.matches("^\\d{13}$"))
            throw new RuntimeException("Cedula no valida (13 digitos y numeros  enteros positivos solamente)");
    }

    public static void validarNombre(String nombre) {
        if (!nombre.matches("^[a-zA-Z]+$"))
            throw new RuntimeException("Los nombres solo pueden contener letras");
    }

    public static void validarApellido(String apellido) {
        if (!apellido.matches("^[a-zA-Z]+$"))
            throw new RuntimeException("Los apellidos solo pueden contener letras");
    }

    public static void validarCorreo(String correo) {
        if (!correo.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}$"))
            throw new RuntimeException("No es un correo valido");
    }

    public static void validarTelefono(String numero) {
        if (!numero.matches("^\\d{8}$"))
            throw new RuntimeException("Formato de telefono incorrecto (8 digitos y numeros enteros positivos solamente");
    }

    public static void validarTelefonos(List<Telefono> telefonos) {
        for (Telefono telefono : telefonos) {
            if (!telefono.getNumero().matches("^\\d{8}$"))
                throw new RuntimeException("Formato de telefono incorrecto (8 digitos y numeros enteros positivos solamente");
        }
    }

    public static void ValidarFecha(String fecha) {
        if (!fecha.matches("^(\\d{4})-(\\d{2})-(\\d{2})$"))
            throw new RuntimeException("La fecha tiene que tener un formato de: año-mes-dia");
    }

    public static void ValidarFechaHora(String fecha) {
        if (!fecha.matches("\\\\d{4}-\\\\d{2}-\\\\d{2}T\\\\d{2}:\\\\d{2}:\\\\d{2}$"))
            throw new RuntimeException("La fecha tiene que tener un formato de: año-mes-dia-horas:min:seg");
    }
}
