package com.cursatec.sistemaCursos.controller;

import com.cursatec.sistemaCursos.entity.Alumno;
import com.cursatec.sistemaCursos.entity.Curso;
import com.cursatec.sistemaCursos.repository.AlumnoRepository;
import com.cursatec.sistemaCursos.repository.CursoRepository;
import com.cursatec.sistemaCursos.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService){
        this.alumnoService = alumnoService;
    }

    @GetMapping("/todos")
    public List<Alumno> findAll(){
        return alumnoService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Alumno> findById(@PathVariable Long id){
        return alumnoService.findById(id);
    }

}
