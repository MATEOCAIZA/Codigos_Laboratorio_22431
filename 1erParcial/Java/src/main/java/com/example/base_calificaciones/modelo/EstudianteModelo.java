package com.example.base_calificaciones.modelo;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.util.Date;

@Entity
@Table(name = "estudiantes")
public class EstudianteModelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;
    private String email;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    //Relaciones

    //Estudiante con varias notas
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<NotaModelo> notas;

    public EstudianteModelo() {
    }

    public EstudianteModelo(long id, String nombre, String apellido, String email, Date fechaNacimiento, List<NotaModelo> notas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.notas = notas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<NotaModelo> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaModelo> notas) {
        this.notas = notas;
    }
}
