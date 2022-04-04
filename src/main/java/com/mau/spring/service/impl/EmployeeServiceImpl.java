package com.mau.spring.service.impl;

import com.mau.spring.entity.Employee;
import com.mau.spring.entity.Position;
import com.mau.spring.repository.EmployeeRepository;
import com.mau.spring.service.EmployeeService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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
    public Employee getEmployee(Long id, boolean includeDeleted) {
        Optional<Employee> optionalEmployee;
        Employee aux;
        if (includeDeleted) {
            optionalEmployee = this.employeeRepository.findById(id);
        } else {
            aux = new Employee();
            aux.setEmployeeId(id);
            aux.setStatus(STATUS_ACTIVE);
            Example<Employee> example = Example.of(aux);
            optionalEmployee = this.employeeRepository.findOne(example);
        }
        if (optionalEmployee.isPresent()) {
            aux = optionalEmployee.get();
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
        Employee dbEmployee = getEmployee(employee.getEmployeeId(), true);
        if (dbEmployee == null) {
            return false;
        }
        dbEmployee.setCorpEmail(employee.getCorpEmail());
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
    public boolean deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            return false;
        }
        Employee dbEmployee = optionalEmployee.get();
        dbEmployee.setStatus(STATUS_INACTIVE);
        this.employeeRepository.save(dbEmployee);
        return true;
    }

    @Override
    public boolean addPosition(Position position, Long id) {
        boolean result = true;
        Employee employee = getEmployee(id, false);
        if (employee != null) {
            employee.addPosition(position);
            employeeRepository.save(employee);
        } else {
            result = false;
        }
        return result;
    }
}
