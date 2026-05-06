package org.example.backendappcarnet.repository;

import org.example.backendappcarnet.model.Carnet;
import org.example.backendappcarnet.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarnetRepository extends JpaRepository<Carnet, Long> {

    Optional<Carnet> findByEstudiante(Estudiante estudiante);
}