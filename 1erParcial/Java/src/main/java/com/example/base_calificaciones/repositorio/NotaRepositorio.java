package com.example.base_calificaciones.repositorio;

import com.example.base_calificaciones.modelo.NotaModelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepositorio extends JpaRepository<NotaModelo, Long> {
}