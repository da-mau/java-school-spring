package com.mau.spring.controller;

import com.mau.spring.dto.EmployeeAdminDTO;
import com.mau.spring.dto.EmployeeDTO;
import com.mau.spring.dto.PositionDTO;
import com.mau.spring.entity.Employee;
import com.mau.spring.entity.Position;
import com.mau.spring.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

@RestController()
@RequestMapping("/admin")
public class EmployeeAdminController extends AbstractEmployeeController {
    private final EmployeeService employeeService;

    public EmployeeAdminController(EmployeeService employeeService, ModelMapper modelMapper) {
        super(modelMapper);
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity createEmployee(@Valid @RequestBody EmployeeDTO employeeDto) throws ParseException {
        Employee employee = convertEmployeeDTOToEntity(employeeDto);
        Employee createdEmployee = employeeService.saveEmployee(employee);
        ResponseEntity result = ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/employee/" + createdEmployee.getEmployeeId())
                .build();
        return result;
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity updateEmployee(@Valid @RequestBody EmployeeDTO employeeDto, @PathVariable Long id) throws ParseException {
        Employee employee = convertEmployeeDTOToEntity(employeeDto);
        employee.setEmployeeId(id);
        boolean updated = employeeService.updateEmployee(employee);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity getEmployee(@PathVariable Long id) {
        Employee employee = employeeService.getEmployee(id, true);
        if (employee != null) {
            EmployeeAdminDTO dto = convertEmployeeToAdminDto(employee);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employee/{id}/position")
    public ResponseEntity addPosition(@RequestBody PositionDTO positionDto, @PathVariable Long id) throws ParseException {
        Position position = convertPositionDTOToEntity(positionDto);
        boolean added = employeeService.addPosition(position, id);
        if (added) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/employee/search")
    public ResponseEntity addPosition(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String position, @RequestParam(defaultValue = "false") boolean includeInactive) throws ParseException {
        List<String> status;
        if(includeInactive){
            status = List.of(Employee.STATUS_INACTIVE, Employee.STATUS_ACTIVE);
        }else{
            status = List.of(Employee.STATUS_ACTIVE);
        }
        List<Employee> employees = employeeService.getEmployeeByNameAndPosition(firstName, lastName, position, status);
        List<EmployeeAdminDTO> result = convertEmployeesToAdminDto(employees);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
