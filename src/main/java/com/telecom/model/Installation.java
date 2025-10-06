package com.telecom.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "INSTALLATIONS")
public class Installation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;
    private String tipoServicio; // fibra, cobre, satelite, etc.
    private LocalDateTime fechaInstalacion;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee tecnico; // tecnico que realizó la instalación

    // getters y setters
}