package com.mau.spring.service.impl;

import com.mau.spring.entity.Employee;
import com.mau.spring.repository.EmployeeRepository;
import com.mau.spring.service.EmployeeService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private static final String STATUS_INACTIVE = "Inactive";


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Employee aux = new Employee();
        aux.setCorpEmail(employee.getCorpEmail());
        Example<Employee> example = Example.of(aux);
        Optional<Employee> optionalEmployee = this.employeeRepository.findOne(example);
        if(!optionalEmployee.isPresent()){
            return false;
        }
        Employee dbEmployee = optionalEmployee.get();
        dbEmployee.setLastName(employee.getLastName());
        dbEmployee.setBirthday(employee.getBirthday());
        dbEmployee.setGender(employee.getGender());
        dbEmployee.setStatus(employee.getStatus());
        dbEmployee.setFirstName(employee.getFirstName());
        this.employeeRepository.save(dbEmployee);
        return true;
    }

    @Override
    public boolean deleteEmployee(String corpEmail){

        Employee aux = new Employee();
        aux.setCorpEmail(corpEmail);
        Example<Employee> example = Example.of(aux);
        Optional<Employee> optionalEmployee = this.employeeRepository.findOne(example);
        if(!optionalEmployee.isPresent()){
            return false;
        }
        Employee dbEmployee = optionalEmployee.get();
        dbEmployee.setStatus(STATUS_INACTIVE);
        this.employeeRepository.save(dbEmployee);
        return true;
    }
}
