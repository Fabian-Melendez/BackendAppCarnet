package org.example.backendappcarnet.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendappcarnet.model.Estudiante;
import org.example.backendappcarnet.service.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.crearEstudiante(estudiante));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','ESTUDIANTE')")
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.obtenerPorId(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Long id,
                                                 @RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.actualizarEstudiante(id, estudiante));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.ok("Estudiante eliminado correctamente");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','ESTUDIANTE')")
    @GetMapping("/buscar")
    public ResponseEntity<List<Estudiante>> buscar(@RequestParam String nombre) {
        return ResponseEntity.ok(estudianteService.buscarPorNombre(nombre));
    }
}