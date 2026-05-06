package org.example.backendappcarnet.repository;

import org.example.backendappcarnet.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findByDocumento(String documento);

    Optional<Estudiante> findByCorreo(String correo);

    List<Estudiante> findByNombreContainingIgnoreCase(String nombre);

    boolean existsByDocumento(String documento);

    boolean existsByCorreo(String correo);
}