package org.example.backendappcarnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String documento;

    private int edad;

    private String correo;

    private String password;

    private String carrera;

    private int semestre;

    private LocalDateTime fechaCreacion;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
