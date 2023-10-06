package com.cursatec.sistemaCursos.controller;

import com.cursatec.sistemaCursos.entity.Curso;
import com.cursatec.sistemaCursos.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/curso")
public class CursoController {
    CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping("/todos")
    public String findAll(ModelMap modelMap){
        List<Curso> cursos = cursoService.findAll();
        modelMap.addAttribute("cursos", cursos);

        return "mostrarCursos.html";
    }

    @GetMapping("/cargar")
    public String cargar(){
        return "formCargarCursos.html";
    }

    @PostMapping("/cargar")
    public String cargar(@RequestParam String titulo, String descripcion, String fecha_creacion, ModelMap modelmap){
        Curso curso = cursoService.guardarParametros(titulo, descripcion, fecha_creacion);
        String mensajeRegistro;
        Boolean estado;
        if (cursoService.save(curso)){
            mensajeRegistro = "Se completo el registro exitosamente.";
            estado = true;
            modelmap.addAttribute("estado", estado);
            modelmap.addAttribute("mensajeRegistro", mensajeRegistro);
        }else{
            mensajeRegistro = "Error: el curso ya existe.";
            estado = false;
            modelmap.addAttribute("estado", estado);
            modelmap.addAttribute("mensajeRegistro", mensajeRegistro);
        }
        return "formCargarCursos.html";
    }

    @GetMapping("/{id}/alumnos")
    public String mostrarAlumnos(@PathVariable Long id, ModelMap modelMap){
        Curso curso = cursoService.findById(id).get();
        modelMap.addAttribute("curso", curso);
        return "mostrarAlumnosPorCurso.html";
    }
}
