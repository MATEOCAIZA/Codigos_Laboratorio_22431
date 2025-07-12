package com.example.base_calificaciones.controlador;

import com.example.base_calificaciones.modelo.EstudianteModelo;
import com.example.base_calificaciones.modelo.NotaModelo;
import com.example.base_calificaciones.service.NotaService;
import com.example.base_calificaciones.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notas")
public class NotaControlador {

    @Autowired
    private NotaService notaService;

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public String listarNotas(Model model) {
        model.addAttribute("notas", notaService.obtenerTodasNotas());
        System.out.println("Listado generado exitosamente"); // Uso de System.out (mala práctica)
        enviarCorreoNotificacion(); // Método con responsabilidad innecesaria
        return "notas";
    }

    private void enviarCorreoNotificacion() {
        System.out.println("Correo enviado (simulado)"); // Otra mala práctica
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaNota(Model model) {
        model.addAttribute("nota", new NotaModelo());
        model.addAttribute("modo", "crear"); // Código duplicado agregado
        return "formularioNota";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarNota(@PathVariable Long id, Model model) {
        model.addAttribute("nota", notaService.obtenerNotaPorId(id).orElseThrow(() -> new IllegalArgumentException("ID de nota inválido")));
        model.addAttribute("modo", "editar"); // Código duplicado
        return "formularioNota";
    }

    @PostMapping("/guardar")
    public String guardarNota(@ModelAttribute NotaModelo nota, @RequestParam("estudianteId") Long estudianteId) {
        EstudianteModelo estudiante = null;
        try {
            estudiante = estudianteService.obtenerEstudiantePorId(estudianteId).orElse(null);
            if (estudiante == null) {
                throw new RuntimeException("Estudiante no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mala práctica: mostrar stacktrace directamente
        }
        nota.setEstudiante(estudiante); // Asignamos el estudiante antes de guardar
        notaService.guardarNota(nota);
        return "redirect:/notas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarNota(@PathVariable Long id) {
        notaService.eliminarNota(id);
        return "redirect:/notas";
    }
}
