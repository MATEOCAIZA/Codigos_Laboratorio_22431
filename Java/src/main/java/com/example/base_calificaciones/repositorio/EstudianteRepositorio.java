package com.example.base_calificaciones.repositorio;

import com.example.base_calificaciones.modelo.EstudianteModelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositorio extends JpaRepository<EstudianteModelo, Long> {
}