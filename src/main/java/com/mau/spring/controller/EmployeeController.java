package com.mau.spring.controller;

import com.mau.spring.dto.EmployeeAdminDTO;
import com.mau.spring.dto.EmployeeDTO;
import com.mau.spring.entity.Employee;
import com.mau.spring.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/employee/search")
    public ResponseEntity addPosition(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String position) throws ParseException {
        List<String> status = List.of(Employee.STATUS_ACTIVE);
        List<Employee> employees = employeeService.getEmployeeByNameAndPosition(firstName, lastName, position, status);
        List<EmployeeDTO> result = convertEmployeesToDto(employees);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
