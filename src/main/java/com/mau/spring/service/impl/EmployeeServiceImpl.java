package com.mau.spring.service.impl;

import com.mau.spring.entity.Employee;
import com.mau.spring.entity.Position;
import com.mau.spring.repository.EmployeeRepository;
import com.mau.spring.service.EmployeeService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private static final String STATUS_INACTIVE = "Inactive";
    private static final String STATUS_ACTIVE = "Active";


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployee(String corpEmail, boolean isAdmin) {
        Employee aux = new Employee();
        aux.setCorpEmail(corpEmail);
        aux.setStatus(STATUS_ACTIVE);
        Example<Employee> example = Example.of(aux);
        Optional<Employee> optionalEmployee = this.employeeRepository.findOne(example);
        if (optionalEmployee.isPresent()) {
            aux = optionalEmployee.get();
            if (!isAdmin) {
                aux.setPositions(null);
            }
        } else {
            aux = null;
        }
        return aux;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        employee.setStatus(STATUS_ACTIVE);
        this.employeeRepository.save(employee);
        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Employee aux = new Employee();
        aux.setCorpEmail(employee.getCorpEmail());
        aux.setStatus(STATUS_ACTIVE);
        Example<Employee> example = Example.of(aux);
        Optional<Employee> optionalEmployee = this.employeeRepository.findOne(example);
        if (!optionalEmployee.isPresent()) {
            return false;
        }
        Employee dbEmployee = optionalEmployee.get();
        dbEmployee.setLastName(employee.getLastName());
        dbEmployee.setBirthday(employee.getBirthday());
        dbEmployee.setGender(employee.getGender());
        dbEmployee.setStatus(STATUS_ACTIVE);
        dbEmployee.setFirstName(employee.getFirstName());
        long ciId = dbEmployee.getContactInformation().getContactInformationId();
        employee.getContactInformation().setContactInformationId(ciId);
        dbEmployee.setContactInformation(employee.getContactInformation());
        this.employeeRepository.save(dbEmployee);
        return true;
    }

    @Override
    public boolean deleteEmployee(String corpEmail) {

        Employee aux = new Employee();
        aux.setCorpEmail(corpEmail);
        aux.setStatus(STATUS_ACTIVE);
        Example<Employee> example = Example.of(aux);
        Optional<Employee> optionalEmployee = this.employeeRepository.findOne(example);
        if (!optionalEmployee.isPresent()) {
            return false;
        }
        Employee dbEmployee = optionalEmployee.get();
        dbEmployee.setStatus(STATUS_INACTIVE);
        this.employeeRepository.save(dbEmployee);
        return true;
    }

    @Override
    public boolean addPosition(Position position, String corpEmail) {
        boolean result = true;
        Employee employee = getEmployee(corpEmail, true);
        if(employee != null){
           employee.addPosition(position);
           employeeRepository.save(employee);
        }else{
            result = false;
        }
        return result;
    }
}
