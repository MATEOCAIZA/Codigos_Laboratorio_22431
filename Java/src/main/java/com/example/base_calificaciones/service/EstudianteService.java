package com.example.base_calificaciones.service;

import com.example.base_calificaciones.modelo.EstudianteModelo;
import com.example.base_calificaciones.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    public List<EstudianteModelo> obtenerTodosEstudiantes() {
        return estudianteRepositorio.findAll();
    }

    public Optional<EstudianteModelo> obtenerEstudiantePorId(Long id) {
        return estudianteRepositorio.findById(id);
    }

    public EstudianteModelo guardarEstudiante(EstudianteModelo estudiante) {
        return estudianteRepositorio.save(estudiante);
    }

    public void eliminarEstudiante(Long id) {
        estudianteRepositorio.deleteById(id);
    }
}