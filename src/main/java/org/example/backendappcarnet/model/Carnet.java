package org.example.backendappcarnet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carnets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carnet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String carrera;

    private int semestre;

    @OneToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;
}
