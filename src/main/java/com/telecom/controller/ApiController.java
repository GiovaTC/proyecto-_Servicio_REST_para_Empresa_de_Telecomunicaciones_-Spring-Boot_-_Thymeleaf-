package com.telecom.controller;

import com.telecom.model.Employee;
import com.telecom.model.CompanyRegistration;
import com.telecom.model.Installation;
import com.telecom.service.TelecomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {

    private final TelecomService telecomService;

    public ApiController(TelecomService telecomService) {
        this.telecomService = telecomService;
    }

    // ================= EMPLOYEES =================

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return telecomService.findAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return telecomService.findEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return telecomService.saveEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return telecomService.findEmployeeById(id)
                .map(existing -> {
                    employee.setId(id);
                    return ResponseEntity.ok(telecomService.saveEmployee(employee));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        telecomService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // ================= COMPANY REGISTRATIONS =================

    @GetMapping("/registrations")
    public List<CompanyRegistration> getAllRegistrations() {
        return telecomService.findAllCompanies();
    }

    @GetMapping("/registrations/{id}")
    public ResponseEntity<CompanyRegistration> getRegistrationById(@PathVariable Long id) {
        return telecomService.findCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/registrations")
    public CompanyRegistration createRegistration(@RequestBody CompanyRegistration registration) {
        return telecomService.saveCompany(registration);
    }

    @PutMapping("/registrations/{id}")
    public ResponseEntity<CompanyRegistration> updateRegistration(@PathVariable Long id, @RequestBody CompanyRegistration registration) {
        return telecomService.findCompanyById(id)
                .map(existing -> {
                    registration.setId(id);
                    return ResponseEntity.ok(telecomService.saveCompany(registration));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/registrations/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        telecomService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    // ================= INSTALLATIONS =================

    @GetMapping("/installations")
    public List<Installation> getAllInstallations() {
        return telecomService.findAllInstallations();
    }

    @GetMapping("/installations/{id}")
    public ResponseEntity<Installation> getInstallationById(@PathVariable Long id) {
        return telecomService.findInstallationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/installations")
    public Installation createInstallation(@RequestBody Installation installation) {
        return telecomService.saveInstallation(installation);
    }

    @PutMapping("/installations/{id}")
    public ResponseEntity<Installation> updateInstallation(@PathVariable Long id, @RequestBody Installation installation) {
        return telecomService.findInstallationById(id)
                .map(existing -> {
                    installation.setId(id);
                    return ResponseEntity.ok(telecomService.saveInstallation(installation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/installations/{id}")
    public ResponseEntity<Void> deleteInstallation(@PathVariable Long id) {
        telecomService.deleteInstallation(id);
        return ResponseEntity.noContent().build();
    }
}
