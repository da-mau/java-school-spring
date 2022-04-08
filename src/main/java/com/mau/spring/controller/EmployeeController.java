package com.mau.spring.controller;

import com.mau.spring.dto.EmployeeDTO;
import com.mau.spring.entity.Employee;
import com.mau.spring.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController()
public class EmployeeController extends AbstractEmployeeController {
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
    public ResponseEntity addPosition(@RequestParam String firstName, @RequestParam String lastName,
                                      @RequestParam String position,
                                      @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize) throws ParseException {
        List<String> status = List.of(Employee.STATUS_ACTIVE);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Employee> employees = employeeService.getEmployeeByNameAndPosition(firstName, lastName, position, status, pageable);
        Page dtoPage = employees.map(this::convertEmployeeToDto);
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }


}
