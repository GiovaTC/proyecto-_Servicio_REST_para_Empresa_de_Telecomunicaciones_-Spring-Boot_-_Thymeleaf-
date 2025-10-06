package com.telecom.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "COMPANY_REGISTRATIONS")
public class CompanyRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresaNombre;
    private String nit;
    private LocalDate fechaRegistro;
    private String direccion;

    // getters y setters
}