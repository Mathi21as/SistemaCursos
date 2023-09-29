CREATE DATABASE IF NOT EXISTS backendjava;

use backendjava;

CREATE TABLE IF NOT EXISTS curso(
	id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    fecha_creacion DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS alumnos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    curso_id INT NOT NULL,
    fecha_inscripcion DATE NOT NULL,
    aprobado boolean NOT NULL,
	FOREIGN KEY (curso_id) REFERENCES Curso(id) ON DELETE CASCADE
);