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
import org.springframework.web.servlet.ModelAndView;

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
    public String formAlumno(ModelMap modelMap){
        List<Curso> cursos = cursoService.findAll();
        modelMap.addAttribute("cursos", cursos);
        return "formCargarAlumnos.html";
    }

    @PostMapping("/cargar")
    public String saveAlumno(@RequestParam String nombre, String apellido, String fecha_nacimiento,
                             String tituloCurso, String fecha_inscripcion, ModelMap modelmap) {
        Alumno alumno = alumnoService.guardarParametros(
                nombre, apellido, fecha_nacimiento, tituloCurso,
                fecha_inscripcion, cursoService); //guarda los parametros del metodo saveAlumno en un objeto alumno
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

    @GetMapping("/buscar")
    public String findById(){
        return "buscarAlumno.html";
    }

    @PostMapping("/buscar")
    public String findById(@RequestParam String apellido, ModelMap modelMap){
        List<Alumno> alumnos;
        if (!apellido.equals("")){
            alumnos = alumnoService.findByApellido(apellido);
            modelMap.addAttribute("alumnos", alumnos);
        }
        return "buscarAlumno.html";
    }

    @GetMapping("/editar/{id}")
    public String update(@PathVariable Long id, ModelMap modelMap){
        Alumno alumno = alumnoService.findById(id).get();
        List<Curso> cursos = cursoService.findAll();
        Curso curso = new Curso(); //crea un curso vacio para que luego, en la etiqueta select de la pagina registrar o editar alumno, aparezca vacio si no se quiere modificar.
        cursos.add(0, curso);
        modelMap.addAttribute("cursos", cursos);
        modelMap.addAttribute("alumno", alumno);
        return "formEditarAlumno.html";
    }

    @PostMapping("/editar/{id}")
    public ModelAndView update(@PathVariable Long id, @RequestParam String nombre, String apellido,
                               String fecha_nacimiento, String curso, String fecha_inscripcion, String estado){
        alumnoService.modificar(id, nombre, apellido, fecha_nacimiento, curso, fecha_inscripcion, estado, cursoService);
        return new ModelAndView("redirect:/alumno/todos");
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id, ModelMap modelMap){
        Alumno alumno = alumnoService.findById(id).get();
        modelMap.addAttribute("alumno", alumno);
        return "eliminarAlumno.html";
    }

    @PostMapping("/eliminar/{id}")
    public ModelAndView eliminarAlumno(@PathVariable Long id){
        alumnoService.delete(id);
        return new ModelAndView("redirect:/alumno/todos");
    }

}
