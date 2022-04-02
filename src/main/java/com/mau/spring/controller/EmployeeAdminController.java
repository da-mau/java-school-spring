package com.mau.spring.controller;

import com.mau.spring.entity.Employee;
import com.mau.spring.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/admin")
public class EmployeeAdminController {
    private final EmployeeService employeeService;

    public EmployeeAdminController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity createEmployee(@Valid @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("/employee")
    public ResponseEntity deleteEmployee(@RequestParam String corporateEmail) {
        boolean deleted = employeeService.deleteEmployee(corporateEmail);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employee")
    public ResponseEntity updateEmployee(@Valid @RequestBody Employee employee, @RequestParam String corporateEmail) {
        boolean updated = employeeService.updateEmployee(employee);
        if(updated){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/employee")
    public ResponseEntity getEmployee(@RequestParam String corporateEmail) {
        Employee employee = employeeService.getEmployee(corporateEmail, true);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
