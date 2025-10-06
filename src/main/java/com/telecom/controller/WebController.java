package com.telecom.controller;

import com.telecom.service.TelecomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final TelecomService telecomService;

    public WebController(TelecomService telecomService) {
        this.telecomService = telecomService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("employeeCount", telecomService.findAllEmployees().size());
        model.addAttribute("installationCount", telecomService.findAllInstallations().size());
        model.addAttribute("companyCount", telecomService.findAllCompanies().size());
        return "dashboard"; // dashboard.html (Thymeleaf)
    }

    @GetMapping("/employees")
    public String employeesPage(Model model) {
        model.addAttribute("employees", telecomService.findAllEmployees());
        return "employees"; // employees.html
    }

    @GetMapping("/installations")
    public String installationsPage(Model model) {
        model.addAttribute("installations", telecomService.findAllInstallations());
        return "installations"; // installations.html
    }
}
