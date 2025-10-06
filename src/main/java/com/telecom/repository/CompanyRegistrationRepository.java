package com.telecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.telecom.model.Employee;
import com.telecom.model.CompanyRegistration;
import com.telecom.model.Installation;

@Repository
public interface CompanyRegistrationRepository extends JpaRepository<CompanyRegistration, Long> {
// Métodos personalizados, por ejemplo:
// Optional<CompanyRegistration> findByNit(String nit);
}