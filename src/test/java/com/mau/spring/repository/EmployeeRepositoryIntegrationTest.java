package com.mau.spring.repository;

import com.mau.spring.AbstractTest;
import com.mau.spring.entity.ContactInformation;
import com.mau.spring.entity.Employee;
import com.mau.spring.entity.Position;
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

    @Test
    public void saveUserTest() {
        Employee employee = getEmployee();
        employee = employeeRepository.save(employee);
        assertThat(employee.getEmployeeId(), notNullValue());
    }

    @Test
    public void modifyUserTest() {
        Employee employee = getEmployee();
        employee = employeeRepository.save(employee);
        employee.setCorpEmail(EMAIL_2);
        employee = employeeRepository.save(employee);
        Employee employee2 = employeeRepository.getById(employee.getEmployeeId());
        assertThat(employeeRepository.findById(employee2.getEmployeeId()).get().getCorpEmail(), is(equalTo(EMAIL_2)));
    }

    //search user
    @Test
    public void searchUserTest() {
        Employee employee = getEmployee();
        employee = entityManager.persistAndFlush(employee);
        Example<Employee> example = Example.of(employee);
        Optional<Employee> optionalEmployee = employeeRepository.findOne(example);
        assertThat(optionalEmployee.get(), is(equalTo(employee)));
    }

    //search user negative
    @Test
    public void searchUserNegativeTest() {
        Employee employee = getEmployee();
        employee = entityManager.persistAndFlush(employee);
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
        //employee = employeeRepository.save(employee);
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
        //employee = employeeRepository.save(employee);
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
        //employee = employeeRepository.save(employee);
        Position position = getPosition();
        employee.setPosition(position);
        employeeRepository.save(employee);
        assertThat(employee.getEmployeeId(), notNullValue());
        assertThat(employee.getPosition().getEmployee().getEmployeeId(), is(equalTo(employee.getEmployeeId())));
    }

    //update
    @Test
    public void modifyUserWithPositionTest() {
        Employee employee = getEmployee();
        //employee = employeeRepository.save(employee);
        Position position = getPosition();
        employee.setPosition(position);
        employeeRepository.save(employee);
        assertThat(employee.getPosition().getEmployee().getEmployeeId(), is(equalTo(employee.getEmployeeId())));
        position.setPositionName(POSITION_NAME_2);
        employeeRepository.save(employee);
        assertThat(employee.getPosition().getPositionName(), is(equalTo(POSITION_NAME_2)));
    }

}