package com.cursatec.sistemaCursos.service;

import com.cursatec.sistemaCursos.entity.Alumno;
import com.cursatec.sistemaCursos.entity.Curso;
import com.cursatec.sistemaCursos.repository.AlumnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public Alumno guardarParametros(String nombre, String apellido, String fecha_nacimiento, String tituloCurso,
                                    String fecha_inscripcion, CursoService cursoService){
        Curso curso = cursoService.findByTitulo(tituloCurso);
        Date fechaNacimiento = new Date(fecha_nacimiento.replace("-", "/"));
        Date fechaInscripcion = new Date(fecha_inscripcion.replace("-", "/"));
        Alumno alumno = new Alumno(nombre, apellido, fechaNacimiento, curso, fechaInscripcion);
        return alumno;
    }

    public void modificar(Long id, String nombre, String apellido, String fecha_nacimiento, String curso,
                          String fecha_inscripcion, String estado, CursoService cursoService) {
        Alumno alumno = alumnoRepository.findById(id).get();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date fecha_nacimiento_date = null;
        Date fecha_inscripcion_date = null;
        try{
            if (!fecha_nacimiento.equals(""))
                fecha_nacimiento_date = (Date) format.parse(fecha_nacimiento.replace("-", "/"));

            if (!fecha_inscripcion.equals(""))
                fecha_inscripcion_date = (Date) format.parse(fecha_inscripcion.replace("-", "/"));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        boolean estadoBool = estado.equals("Aprobado") ? true : false;
        if (!nombre.equals(""))
            alumno.setNombre(nombre);
        if (!apellido.equals(""))
            alumno.setApellido(apellido);
        if (fecha_nacimiento_date != null)
            alumno.setFecha_nacimiento(fecha_nacimiento_date);
        if (!curso.equals("")){
            Curso cursoEntity = cursoService.findByTitulo(curso);
            alumno.setCurso(cursoEntity);
        }
        if (fecha_inscripcion_date != null)
            alumno.setFecha_inscripcion(fecha_inscripcion_date);
        if (!estado.equals(""))
            alumno.setAprobado(estadoBool);
        alumnoRepository.save(alumno);
    }

    public Alumno save(Alumno alumno){
        Alumno alumnoCreado = null;
        if (alumnoRepository.findByNombreAndApellido(alumno.getNombre(), alumno.getApellido()).size() == 0)
            alumnoCreado = alumnoRepository.save(alumno);
        return alumnoCreado;
    }

    public List<Alumno> findByApellido(String apellido){
        return alumnoRepository.findByApellido(apellido);
    }

    public void delete(Long id){
        alumnoRepository.deleteById(id);
    }

}
