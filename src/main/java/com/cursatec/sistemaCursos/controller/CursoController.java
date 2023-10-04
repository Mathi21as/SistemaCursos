package com.cursatec.sistemaCursos.controller;

import com.cursatec.sistemaCursos.entity.Curso;
import com.cursatec.sistemaCursos.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/curso")
public class CursoController {
    CursoService cursoService;

    public CursoController(CursoService cursoService){
        this.cursoService = cursoService;
    }

    @GetMapping("/cargar")
    public List<Curso> findAll(){
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Curso> findById(@PathVariable Long id){
        return cursoService.findById(id);
    }
}
