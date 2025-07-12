package com.example.base_calificaciones.controlador;

import com.example.base_calificaciones.modelo.EstudianteModelo;
import com.example.base_calificaciones.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteControlador {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.obtenerTodosEstudiantes());
        return "estudiantes"; // Asegúrate de que "estudiantes" coincida con el nombre del archivo HTML
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoEstudiante(Model model) {
        model.addAttribute("estudiante", new EstudianteModelo());
        return "formularioEstudiante"; // Asegúrate de que "formularioEstudiante" coincida con el nombre del archivo HTML
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute EstudianteModelo estudiante) {
        estudianteService.guardarEstudiante(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarEstudiante(@PathVariable Long id, Model model) {
        model.addAttribute("estudiante", estudianteService.obtenerEstudiantePorId(id).orElseThrow(() -> new IllegalArgumentException("ID de estudiante inválido")));
        return "formularioEstudiante";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return "redirect:/estudiantes";
    }
}