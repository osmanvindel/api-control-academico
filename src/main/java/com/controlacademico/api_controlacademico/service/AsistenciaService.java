package com.controlacademico.api_controlacademico.service;

import com.controlacademico.api_controlacademico.entity.Alumno;
import com.controlacademico.api_controlacademico.entity.Asistencia;
import com.controlacademico.api_controlacademico.entity.Maestro;
import com.controlacademico.api_controlacademico.repository.AlumnoRepository;
import com.controlacademico.api_controlacademico.repository.AsistenciaRepository;
import com.controlacademico.api_controlacademico.repository.MaestroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {
    public final AsistenciaRepository asistenciaRepository;
    public final AlumnoRepository alumnoRepository;
    public final MaestroRepository maestroRepository;

    @Autowired
    public AsistenciaService(AsistenciaRepository asistenciaRepository, AlumnoRepository alumnoRepository, MaestroRepository maestroRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.alumnoRepository = alumnoRepository;
        this.maestroRepository = maestroRepository;
    }

    //Crear
    public void crearAsistencia(Asistencia asistencia) {
        if (asistencia.vacio() && asistencia.getAlumno() == null && asistencia.getMaestro() == null) //JSON vacio
            throw new RuntimeException("No se aceptan objetos vacios");
        if (asistencia.getAlumno() == null)
            throw new RuntimeException("Debes de especificar el Alumno");
        if (asistencia.getMaestro() == null)
            throw new RuntimeException("Debes de especificar el Maestro");
        asistenciaRepository.save(asistencia);
    }

    //Editar
    public void editarAsistencia(int id, Asistencia asistencia) {
        if (asistencia.vacio()) //JSON vacio
            throw new RuntimeException("No se aceptan objetos vacios");

        Asistencia asistenciaModificada = asistenciaRepository.findById(id).orElseThrow(() -> new RuntimeException("La asistencia no existe"));

        if (asistencia.getAsignatura() != null) {
            asistenciaModificada.setAsignatura(asistencia.getAsignatura());
        }
        if (asistencia.getAlumno() != null) {
            Alumno alumno = alumnoRepository.findById(id).orElseThrow(() -> new RuntimeException("El alumno no existe"));
            if (alumno.getActivo().equals((byte) 0))
                throw new RuntimeException("Alumno no disponible");
            asistenciaModificada.setAlumno(alumno);
        }
        if (asistencia.getMaestro() != null) {
            Maestro maestro = maestroRepository.findById(id).orElseThrow(() -> new RuntimeException("El maestro no existe"));
            if (maestro.getActivo().equals((byte) 0))
                throw new RuntimeException("Maestro no disponible");
            asistenciaModificada.setMaestro(maestro);
        }

        asistenciaRepository.save(asistenciaModificada);
    }

    //Buscar (Todos)
    public List<Asistencia> buscarAsistencias() {
        return asistenciaRepository.findAll();
    }

    //Buscar por fecha
    public Optional<Asistencia> buscarAsistenciaPorFecha(LocalDate fecha) {
        return asistenciaRepository.findByFecha(fecha);
    }

    ///Buscar antes de (todas las coincidencias)
    public List<Asistencia> buscarAsistenciasAntesDe(LocalDate fecha) {
        return asistenciaRepository.findByFechaBefore(fecha);
    }

    //Buscar despues de (todas las coincidencias)
    public List<Asistencia> buscarAsistenciasDespuesDe(LocalDate fecha) {
        return asistenciaRepository.findByFechaAfter(fecha);
    }

    //Buscar entre un rango (todas las coincidencias)
    public List<Asistencia> buscarAsistenciasEntre(LocalDate fechaInicio, LocalDate fechaFin) {
        return asistenciaRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
}
