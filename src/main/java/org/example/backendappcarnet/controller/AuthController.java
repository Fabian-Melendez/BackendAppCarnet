package org.example.backendappcarnet.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendappcarnet.dto.AuthRequest;
import org.example.backendappcarnet.dto.AuthResponse;
import org.example.backendappcarnet.dto.RegisterRequest;
import org.example.backendappcarnet.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(
                authService.login(request.getCorreo(), request.getPassword())
        );
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String response = authService.register(request);
        return ResponseEntity.ok(response);
    }
}