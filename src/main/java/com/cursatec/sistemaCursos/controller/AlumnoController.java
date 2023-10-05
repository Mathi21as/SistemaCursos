package com.cursatec.sistemaCursos.controller;

import com.cursatec.sistemaCursos.entity.Alumno;
import com.cursatec.sistemaCursos.entity.Curso;
import com.cursatec.sistemaCursos.repository.AlumnoRepository;
import com.cursatec.sistemaCursos.repository.CursoRepository;
import com.cursatec.sistemaCursos.service.AlumnoService;
import com.cursatec.sistemaCursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
    AlumnoService alumnoService;
    CursoService cursoService;

    public AlumnoController(AlumnoService alumnoService, CursoService cursoService){
        this.alumnoService = alumnoService;
        this.cursoService = cursoService;
    }

    @GetMapping("/todos")
    public String findAll(ModelMap modelMap){
        List<Alumno> alumnos = alumnoService.findAll();
        modelMap.addAttribute("alumnos", alumnos);
        return "mostrarAlumnos.html";
    }

    @GetMapping("/cargar")
    public String formAlumno(){

        return "formCargarAlumnos.html";
    }

    @PostMapping("/cargar")
    public String saveAlumno(@RequestParam String nombre, String apellido, String fecha_nacimiento, Long curso_id, String fecha_inscripcion, ModelMap modelmap) {
        Curso curso = cursoService.findById(curso_id).get();
        Date fechaNacimiento = new Date(fecha_nacimiento.replace("-", "/"));
        Date fechaInscripcion = new Date(fecha_inscripcion.replace("-", "/"));
        Alumno alumno = new Alumno(nombre, apellido, fechaNacimiento, curso, fechaInscripcion);
        String mensajeRegistro;
        Boolean estado;
        if (alumnoService.save(alumno)){
            mensajeRegistro = "Se completo el registro exitosamente.";
            estado = true;
            modelmap.addAttribute("estado", estado);
            modelmap.addAttribute("mensajeRegistro", mensajeRegistro);
        }else{
            mensajeRegistro = "Error: el alumno ya existe.";
            estado = false;
            modelmap.addAttribute("estado", estado);
            modelmap.addAttribute("mensajeRegistro", mensajeRegistro);
        }


        return "formCargarAlumnos.html";
    }

    @GetMapping("/{id}")
    public Optional<Alumno> findById(@PathVariable Long id){
        return alumnoService.findById(id);
    }

}
