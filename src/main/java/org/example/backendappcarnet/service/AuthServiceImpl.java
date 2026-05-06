package org.example.backendappcarnet.service;

import lombok.RequiredArgsConstructor;
import org.example.backendappcarnet.dto.AuthResponse;
import org.example.backendappcarnet.dto.RegisterRequest;
import org.example.backendappcarnet.model.Usuario;
import org.example.backendappcarnet.repository.UsuarioRepository;
import org.example.backendappcarnet.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(String correo, String password) {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generarToken(
                usuario.getCorreo(),
                usuario.getRol().name()
        );

        return new AuthResponse(token);
    }
    @Override
    public String register(RegisterRequest request) {

        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("El correo ya existe");
        }

        Usuario usuario = Usuario.builder()
                .correo(request.getCorreo())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(request.getRol())
                .build();

        usuarioRepository.save(usuario);

        return "Usuario creado correctamente";
    }
}