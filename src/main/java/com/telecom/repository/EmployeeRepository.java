package com.telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.telecom.model.Employee;
import com.telecom.model.CompanyRegistration;
import com.telecom.model.Installation;

/**
 * Repositorios Spring Data JPA para cada entidad del sistema.
 * Permiten operaciones CRUD automáticas y consultas personalizadas si es necesario.
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Puedes agregar métodos personalizados aquí, por ejemplo:
    // List<Employee> findByApellido(String apellido);
}
