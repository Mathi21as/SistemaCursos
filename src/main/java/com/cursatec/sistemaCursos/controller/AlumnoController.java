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
    public String findAll(ModelMap model){
        //ModelMap model = new ModelMap();
        List<Alumno> alumnos = alumnoService.findAll();
        model.put("alumnos", alumnos);
        return "base.html";
        //return alumnoService.findAll();
    }

    @GetMapping("/cargar")
    public String formAlumno(){

        return "formCargarAlumnos.html";
    }

    @PostMapping("/cargar")
    public String saveAlumno(@RequestParam String nombre, String apellido, String fecha_nacimiento, Long curso_id, String fecha_inscripcion, ModelMap modelmap) throws ParseException {
        Curso curso = cursoService.findById(curso_id).get();
        Date fechaNacimiento = new Date(fecha_nacimiento.replace("-", "/"));
        Date fechaInscripcion = new Date(fecha_inscripcion.replace("-", "/"));
        Alumno alumno = new Alumno(nombre, apellido, fechaNacimiento, curso, fechaInscripcion);
        alumnoService.save(alumno);
        modelmap.addAttribute("alumno", alumno);
        return "registroAlumnoExitoso.html";
    }

    @GetMapping("/{id}")
    public Optional<Alumno> findById(@PathVariable Long id){
        return alumnoService.findById(id);
    }

}
