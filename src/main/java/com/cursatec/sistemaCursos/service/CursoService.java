package com.cursatec.sistemaCursos.service;

import com.cursatec.sistemaCursos.entity.Curso;
import com.cursatec.sistemaCursos.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
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

    public Curso guardarParametros(String titulo, String descripcion, String fecha_creacion){
        Date fechaCreacion = new Date(fecha_creacion.replace("-", "/")
                .replace(" 00:00:00.0",""));
        Curso curso = new Curso(titulo, descripcion, fechaCreacion);
        return curso;
    }

    public Optional<Curso> findById(Long id){
        return cursoRepository.findById(id);
    }

}
