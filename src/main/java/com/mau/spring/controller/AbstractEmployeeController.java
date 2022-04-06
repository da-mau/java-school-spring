package com.mau.spring.controller;

import com.mau.spring.dto.EmployeeAdminDTO;
import com.mau.spring.dto.EmployeeDTO;
import com.mau.spring.dto.PositionDTO;
import com.mau.spring.entity.Employee;
import com.mau.spring.entity.Position;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class AbstractEmployeeController {
    protected static final String PATH = "/employee";

    private final ModelMapper modelMapper;
    Calendar now = Calendar.getInstance();

    public AbstractEmployeeController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO convertEmployeeToDto(Employee employee) {
        employee.setPositions(null);
        EmployeeDTO employeeDto = modelMapper.map(employee, EmployeeDTO.class);
        employeeDto.setBirthdayDate(employee.getBirthday());
        return employeeDto;
    }

    public EmployeeAdminDTO convertEmployeeToAdminDto(Employee employee) {
        EmployeeAdminDTO employeeDto = modelMapper.map(employee, EmployeeAdminDTO.class);
        employeeDto.setBirthdayDate(employee.getBirthday());
        return employeeDto;
    }

    public List<EmployeeAdminDTO> convertEmployeesToAdminDto(List<Employee> employees) {
        List<EmployeeAdminDTO> result = new ArrayList<>(employees.size());
        for(Employee aux : employees){
            EmployeeAdminDTO dto = convertEmployeeToAdminDto(aux);
            result.add(dto);
        }
        return result;
    }

    public List<EmployeeDTO> convertEmployeesToDto(List<Employee> employees) {
        List<EmployeeDTO> result = new ArrayList<>(employees.size());
        for(Employee aux : employees){
            EmployeeDTO dto = convertEmployeeToDto(aux);
            result.add(dto);
        }
        return result;
    }

    public Employee convertEmployeeDTOToEntity(EmployeeDTO employeeDto) throws ParseException {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        employee.setBirthday(employeeDto.getBirthdayDateConverted());
        return employee;
    }

    public PositionDTO convertPositionToDto(Position position) {
        PositionDTO positionDto = modelMapper.map(position, PositionDTO.class);
        return positionDto;
    }

    public Position convertPositionDTOToEntity(PositionDTO positionDto) throws ParseException {
        Position position = modelMapper.map(positionDto, Position.class);
        return position;
    }
}