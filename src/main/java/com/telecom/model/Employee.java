package com.telecom.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String nombre;
    @NotBlank private String apellido;
    @NotBlank private String documento; // DNI/NIT
    private LocalDate fechaNacimiento;
    private String email;
    private String telefono;

    // getters y setters
}