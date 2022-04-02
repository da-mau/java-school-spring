package com.mau.spring.repository;

import com.mau.spring.AbstractTest;
import com.mau.spring.entity.ContactInformation;
import com.mau.spring.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Example;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryIntegrationTest extends AbstractTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    EmployeeRepository employeeRepository;

    //search user
    @Test
    public void searchUserTest() {
        Employee employee = getEmployee();
        employee.setContactInformation(getContactInformation());
        employee = entityManager.persistAndFlush(employee);
        Example<Employee> example = Example.of(getEmployee());
        Optional<Employee> optionalEmployee = employeeRepository.findOne(example);
        assertThat(optionalEmployee.get(), is(equalTo(employee)));
    }

    //search user negative
    @Test
    public void searchUserNegativeTest() {
        Employee employee = getEmployee();
        employee.setContactInformation(getContactInformation());
        entityManager.persistAndFlush(employee);
        Employee employee2 = new Employee();
        employee2.setCorpEmail("Random String");
        Example<Employee> example = Example.of(employee2);
        Optional<Employee> optionalEmployee = employeeRepository.findOne(example);
        assertThrows(NoSuchElementException.class, () -> optionalEmployee.get());
    }


    // Save user with contact information
    @Test
    public void saveUserWithCITest() {
        Employee employee = getEmployee();
        ContactInformation ci = getContactInformation();
        employee.setContactInformation(ci);
        employeeRepository.save(employee);
        assertThat(employee.getEmployeeId(), notNullValue());
        assertThat(employee.getContactInformation().getEmployee().getEmployeeId(), is(equalTo(employee.getEmployeeId())));
    }

    // Update
    @Test
    public void modifyUserWithCITest() {
        Employee employee = getEmployee();
        ContactInformation ci = getContactInformation();
        employee.setContactInformation(ci);
        employeeRepository.save(employee);
        assertThat(employee.getContactInformation().getEmployee().getEmployeeId(), is(equalTo(employee.getEmployeeId())));
        ci.setStreetName(STREET_NAME_2);
        employeeRepository.save(employee);
        assertThat(employee.getContactInformation().getStreetName(), is(equalTo(STREET_NAME_2)));
    }

    //Save user with position
    @Test
    public void saveUserWithPositionTest() {
        Employee employee = getEmployee();
        employee.setContactInformation(getContactInformation());
        employee.setPositions(getPositionList());
        employee = employeeRepository.save(employee);
        assertThat(employee.getEmployeeId(), notNullValue());
        assertThat(employee.getPositions().get(0).getEmployee().getEmployeeId(), is(equalTo(employee.getEmployeeId())));
    }

    //update
    @Test
    public void modifyUserWithPositionTest() {
        Employee employee = getEmployee();
        employee.setContactInformation(getContactInformation());
        employee.setPositions(getPositionList());
        employeeRepository.save(employee);
        assertThat(employee.getPositions().get(0).getEmployee().getEmployeeId(), is(equalTo(employee.getEmployeeId())));
        employee.getPositions().get(0).setPositionName(POSITION_NAME_2);
        employeeRepository.save(employee);
        assertThat(employee.getPositions().get(0).getPositionName(), is(equalTo(POSITION_NAME_2)));
    }

}