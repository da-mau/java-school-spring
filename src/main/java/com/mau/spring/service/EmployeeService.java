package com.mau.spring.service;

import com.mau.spring.entity.Employee;

public interface EmployeeService {

    Employee getEmployee(String corpEmail, boolean isAdmin);
    Employee saveEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(String corpEmail);
}
