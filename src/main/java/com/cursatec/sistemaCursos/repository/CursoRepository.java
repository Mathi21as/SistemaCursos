package com.cursatec.sistemaCursos.repository;

import com.cursatec.sistemaCursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    public List<Curso> findByTitulo(String titulo);
}
