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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.crearEstudiante(estudiante));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<Estudiante>> listar() {
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESTUDIANTE')")
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.obtenerPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Long id,
                                                 @RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(estudianteService.actualizarEstudiante(id, estudiante));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.ok("Estudiante eliminado correctamente");
    }

    @PreAuthorize("hasAnyRole('ADMIN','ESTUDIANTE')")
    @GetMapping("/buscar")
    public ResponseEntity<List<Estudiante>> buscar(@RequestParam String nombre) {
        return ResponseEntity.ok(estudianteService.buscarPorNombre(nombre));
    }
    @GetMapping("/buscar/{correo}")
    public ResponseEntity<Estudiante> buscarPorCorreo(@RequestParam String correo) {
        return ResponseEntity.ok(estudianteService.buscarPorCorreo(correo));
    }
}