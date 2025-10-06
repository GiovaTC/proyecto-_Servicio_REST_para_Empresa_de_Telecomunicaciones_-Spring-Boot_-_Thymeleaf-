package com.telecom.service;

import com.telecom.model.Employee;
import com.telecom.model.CompanyRegistration;
import com.telecom.model.Installation;
import com.telecom.repository.EmployeeRepository;
import com.telecom.repository.CompanyRegistrationRepository;
import com.telecom.repository.InstallationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelecomService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRegistrationRepository companyRepository;
    private final InstallationRepository installationRepository;

    public TelecomService(EmployeeRepository employeeRepository,
                          CompanyRegistrationRepository companyRepository,
                          InstallationRepository installationRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.installationRepository = installationRepository;
    }

    // ================= EMPLOYEES =================
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // ================= COMPANY REGISTRATIONS =================
    public List<CompanyRegistration> findAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<CompanyRegistration> findCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public CompanyRegistration saveCompany(CompanyRegistration company) {
        return companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    // ================= INSTALLATIONS =================
    public List<Installation> findAllInstallations() {
        return installationRepository.findAll();
    }

    public Optional<Installation> findInstallationById(Long id) {
        return installationRepository.findById(id);
    }

    public Installation saveInstallation(Installation installation) {
        return installationRepository.save(installation);
    }

    public void deleteInstallation(Long id) {
        installationRepository.deleteById(id);
    }

    // ================= CUSTOM QUERIES =================
    public List<Installation> findInstallationsByTecnico(Long tecnicoId) {
        return installationRepository.findAll()
                .stream()
                .filter(inst -> inst.getTecnico() != null && inst.getTecnico().getId().equals(tecnicoId))
                .toList();
    }
}
