package com.mau.spring.service;

import com.mau.spring.AbstractTest;
import com.mau.spring.entity.ContactInformation;
import com.mau.spring.entity.Employee;
import com.mau.spring.repository.EmployeeRepository;
import com.mau.spring.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceUnitTest extends AbstractTest {
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Captor
    private ArgumentCaptor<Employee> captor;

    @Test
    void employeeCreationTest(){
        Employee employee  = getEmployee();
        when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.saveEmployee(employee);
    }

    //Update Employee
    @Test
    void employeeUpdateTest(){
        Employee employee  = getEmployee();
        employee.setEmployeeId(1L);
        ContactInformation ci = getContactInformation();
        ci.setContactInformationId(2L);
        employee.setContactInformation(ci);
        Example<Employee> example = Example.of(employee);
        Optional<Employee> optional = Optional.of(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeRepository.findOne(ArgumentMatchers.any())).thenReturn(optional);
        employee = employeeService.saveEmployee(employee);
        employee.setFirstName(FIRST_NAME_2);
        boolean updated = employeeService.updateEmployee(employee);
        assertTrue(updated);

    }
    //Delete employee
    @Test
    void employeeDeleteTest(){
        Employee employee  = getEmployee();
        employee.setEmployeeId(1L);
        Example<Employee> example = Example.of(employee);
        Optional<Employee> optional = Optional.of(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(employeeRepository.findById(ArgumentMatchers.any())).thenReturn(optional);
        employeeService.deleteEmployee(1L);
        verify(this.employeeRepository).save(captor.capture());
        assertEquals(STATUS_INACTIVE, captor.getValue().getStatus());
    }


}
