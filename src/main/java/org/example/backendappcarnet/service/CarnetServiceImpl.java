package org.example.backendappcarnet.service;

import lombok.RequiredArgsConstructor;
import org.example.backendappcarnet.model.Carnet;
import org.example.backendappcarnet.model.Estudiante;
import org.example.backendappcarnet.repository.CarnetRepository;
import org.example.backendappcarnet.repository.EstudianteRepository;
import org.example.backendappcarnet.service.CarnetService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarnetServiceImpl implements CarnetService {

    private final CarnetRepository carnetRepository;
    private final EstudianteRepository estudianteRepository;

    @Override
    public Carnet obtenerCarnetPorEstudiante(Long estudianteId) {

        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return carnetRepository.findByEstudiante(estudiante)
                .orElseThrow(() -> new RuntimeException("Carnet no encontrado"));
    }
}