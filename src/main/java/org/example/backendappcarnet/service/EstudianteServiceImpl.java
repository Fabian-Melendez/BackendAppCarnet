package org.example.backendappcarnet.service;

import lombok.RequiredArgsConstructor;
import org.example.backendappcarnet.model.*;
import org.example.backendappcarnet.repository.*;
import org.example.backendappcarnet.service.EstudianteService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final UsuarioRepository usuarioRepository;
    private final CarnetRepository carnetRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Estudiante crearEstudiante(Estudiante estudiante) {

        if (estudianteRepository.existsByCorreo(estudiante.getCorreo())) {
            throw new RuntimeException("El correo ya está en uso");
        }

        if (estudianteRepository.existsByDocumento(estudiante.getDocumento())) {
            throw new RuntimeException("El documento ya está registrado");
        }

        String passwordEncriptada = passwordEncoder.encode(estudiante.getPassword());

        Usuario usuario = Usuario.builder()
                .correo(estudiante.getCorreo())
                .password(passwordEncriptada)
                .rol(Rol.ESTUDIANTE)
                .build();

        usuarioRepository.save(usuario);

        estudiante.setPassword(passwordEncriptada);
        estudiante.setFechaCreacion(LocalDateTime.now());
        estudiante.setUsuario(usuario);

        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);

        Carnet carnet = Carnet.builder()
                .nombre(estudianteGuardado.getNombre())
                .carrera(estudianteGuardado.getCarrera())
                .semestre(estudianteGuardado.getSemestre())
                .estudiante(estudianteGuardado)
                .build();

        carnetRepository.save(carnet);

        return estudianteGuardado;
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante obtenerPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    @Override
    public Estudiante actualizarEstudiante(Long id, Estudiante estudiante) {

        Estudiante existente = obtenerPorId(id);

        existente.setNombre(estudiante.getNombre());
        existente.setEdad(estudiante.getEdad());
        existente.setCarrera(estudiante.getCarrera());
        existente.setSemestre(estudiante.getSemestre());

        return estudianteRepository.save(existente);
    }

    @Override
    public Estudiante buscarPorCorreo(String correo) {
        return estudianteRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    @Override
    public void eliminarEstudiante(Long id) {

        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado");
        }

        estudianteRepository.deleteById(id);
    }

    @Override
    public List<Estudiante> buscarPorNombre(String nombre) {
        return estudianteRepository.findByNombreContainingIgnoreCase(nombre);
    }
}