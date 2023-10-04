package com.cursatec.sistemaCursos.service;

import com.cursatec.sistemaCursos.entity.Alumno;
import com.cursatec.sistemaCursos.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {
    AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository){
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> findAll(){
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> findById(Long id){
        return alumnoRepository.findById(id);
    }

    public void save(Alumno alumno){
        if (alumnoRepository.findByNombre(alumno.getNombre()).size() == 0){
            alumnoRepository.save(alumno);
        }
    }

}
