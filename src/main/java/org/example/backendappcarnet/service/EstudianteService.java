package org.example.backendappcarnet.service;

import org.example.backendappcarnet.model.Estudiante;

import java.util.List;

public interface EstudianteService {

    Estudiante crearEstudiante(Estudiante estudiante);

    List<Estudiante> listarEstudiantes();

    Estudiante obtenerPorId(Long id);

    Estudiante actualizarEstudiante(Long id, Estudiante estudiante);

    void eliminarEstudiante(Long id);

    List<Estudiante> buscarPorNombre(String nombre);
}