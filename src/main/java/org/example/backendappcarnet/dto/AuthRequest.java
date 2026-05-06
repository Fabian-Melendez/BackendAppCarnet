package org.example.backendappcarnet.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String correo;
    private String password;
}