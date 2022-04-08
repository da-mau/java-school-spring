package com.mau.spring.service;

import com.mau.spring.entity.Employee;
import com.mau.spring.entity.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Employee getEmployee(Long id, boolean includeDeleted);
    Employee saveEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(Long id);
    boolean addPosition(Position position, Long id);
    Page<Employee> getEmployeeByNameAndPosition(String firstName, String lastName, String position, List<String> status
    , Pageable pageable);
}
