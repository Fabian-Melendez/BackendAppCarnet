package org.example.backendappcarnet.service;

import org.example.backendappcarnet.dto.AuthResponse;
import org.example.backendappcarnet.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);
    AuthResponse login(String correo, String password);
}