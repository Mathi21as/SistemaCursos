package com.cursatec.sistemaCursos.service;

import com.cursatec.sistemaCursos.entity.Curso;
import com.cursatec.sistemaCursos.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> findAll(){
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(Long id){
        return cursoRepository.findById(id);
    }

}
