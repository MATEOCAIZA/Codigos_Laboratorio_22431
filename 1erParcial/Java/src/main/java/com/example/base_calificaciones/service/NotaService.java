package com.example.base_calificaciones.service;

import com.example.base_calificaciones.modelo.NotaModelo;
import com.example.base_calificaciones.repositorio.NotaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepositorio notaRepositorio;

    public List<NotaModelo> obtenerTodasNotas() {
        return notaRepositorio.findAll();
    }

    public Optional<NotaModelo> obtenerNotaPorId(Long id) {
        return notaRepositorio.findById(id);
    }

    public NotaModelo guardarNota(NotaModelo nota) {
        return notaRepositorio.save(nota);
    }

    public void eliminarNota(Long id) {
        notaRepositorio.deleteById(id);
    }
}