package org.example.backendappcarnet.service;

import org.example.backendappcarnet.model.Carnet;

public interface CarnetService {

    Carnet obtenerCarnetPorEstudiante(Long estudianteId);
}