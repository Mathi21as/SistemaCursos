package com.cursatec.sistemaCursos.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Date fecha_nacimiento;
    @ManyToOne
    @JoinColumn(name="curso_id")
    private Curso curso;
    private Date fecha_inscripcion;
    private Boolean aprobado;

    public Alumno() {
    }

    public Alumno(String nombre, String apellido, Date fecha_nacimiento, Curso curso, Date fecha_inscripcion) {
        this.id = null;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.curso = curso;
        this.fecha_inscripcion = fecha_inscripcion;
        this.aprobado = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getFecha_nacimiento_string() {
        return fecha_nacimiento.toString().replace(" 00:00:00.0","");
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCurso() {
        return curso.getTitulo();
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public String getFecha_inscripcion_string() {
        return fecha_inscripcion.toString().replace(" 00:00:00.0","");
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }
}
