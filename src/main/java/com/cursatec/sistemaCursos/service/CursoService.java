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

    public Curso findByTitulo(String tituloCurso){
        return cursoRepository.findByTitulo(tituloCurso).get(0);
    }

    public boolean save(Curso curso){
        if (cursoRepository.findByTitulo(curso.getTitulo()).size() == 0){
            cursoRepository.save(curso);
            return true;
        }else{
            return false;
        }

    }

    public Optional<Curso> findById(Long id){
        return cursoRepository.findById(id);
    }

}
