package com.mau.spring.controller;

import com.mau.spring.dto.EmployeeDTO;
import com.mau.spring.entity.Employee;
import com.mau.spring.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class EmployeeController extends AbstractEmployeeController{
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployee(id, false);
        if (employee != null) {
            EmployeeDTO dto = convertEmployeeToDto(employee);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
