package org.example.backendappcarnet.dto;

import lombok.Data;
import org.example.backendappcarnet.model.Rol;

@Data
public class RegisterRequest {
    private String correo;
    private String password;
    private Rol rol;
}