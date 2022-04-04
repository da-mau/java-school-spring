package com.mau.spring.controller;

import com.mau.spring.dto.EmployeeAdminDTO;
import com.mau.spring.dto.EmployeeDTO;
import com.mau.spring.dto.PositionDTO;
import com.mau.spring.entity.Employee;
import com.mau.spring.entity.Position;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.Calendar;

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