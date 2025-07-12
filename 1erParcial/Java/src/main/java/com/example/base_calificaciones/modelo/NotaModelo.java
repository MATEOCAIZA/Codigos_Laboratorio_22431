package com.example.base_calificaciones.modelo;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table (name = "notas")
public class NotaModelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String asignatura;
    private double nota;
    private String calificacion;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_registro  = new Date();

    //Relaciones entre tablas
    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false) // Esta relación es obligatoria
    private EstudianteModelo estudiante;
    //Clave Foranea

    //Constructores


    public NotaModelo() {
    }

    public NotaModelo(long id, String asignatura, double nota, String calificacion, Date fecha_registro, EstudianteModelo estudiante) {
        this.id = id;
        this.asignatura = asignatura;
        this.nota = nota;
        this.fecha_registro = fecha_registro;
        this.estudiante = estudiante;
        this.calificacion = calificacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
        this.calificacion = calcularCalificacion(nota);
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public EstudianteModelo getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteModelo estudiante) {
        this.estudiante = estudiante;
    }

    private String calcularCalificacion(double nota) {
        if (nota >= 9 && nota <= 10) return "Sobresaliente";
        if (nota >= 7 && nota < 9) return "Notable";
        if (nota >= 5 && nota < 7) return "Bien";
        return "Suspendido";
    }
}
