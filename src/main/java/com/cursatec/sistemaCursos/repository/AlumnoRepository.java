package com.cursatec.sistemaCursos.repository;

import com.cursatec.sistemaCursos.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    public List<Alumno> findByNombre(String nombre);
}
