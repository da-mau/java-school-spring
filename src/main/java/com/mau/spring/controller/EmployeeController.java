package com.mau.spring.controller;

import com.mau.spring.entity.Employee;
import com.mau.spring.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("admin/employee")
    public ResponseEntity createEmployee(@Valid @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("admin/employee")
    public ResponseEntity createEmployee(@Valid @RequestParam String corporateEmail) {
        boolean deleted = employeeService.deleteEmployee(corporateEmail);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
