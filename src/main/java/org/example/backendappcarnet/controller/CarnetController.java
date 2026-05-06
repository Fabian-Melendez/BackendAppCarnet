package org.example.backendappcarnet.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendappcarnet.model.Carnet;
import org.example.backendappcarnet.service.CarnetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carnet")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CarnetController {

    private final CarnetService carnetService;

    @PreAuthorize("hasAnyRole('ADMIN','ESTUDIANTE')")
    @GetMapping("/{estudianteId}")
    public ResponseEntity<Carnet> obtenerCarnet(@PathVariable Long estudianteId) {
        return ResponseEntity.ok(carnetService.obtenerCarnetPorEstudiante(estudianteId));
    }
}