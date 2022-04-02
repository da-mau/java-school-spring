package com.mau.spring;

import com.mau.spring.entity.ContactInformation;
import com.mau.spring.entity.Employee;
import com.mau.spring.entity.Position;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbstractTest {
    protected static final String EMAIL_1 = "a@a.com";
    protected static final String EMAIL_2 = "b@b.com";
    protected static final String GENDER = "M";
    protected static final String FIRST_NAME = "Mau";
    protected static final String FIRST_NAME_2 = "Mau 2";
    protected static final String LAST_NAME = "Cerna";
    protected static final String STATUS = "Active";
    protected static final String STATUS_INACTIVE = "Inactive";
    protected static final Date BIRTHDAY = new Date();
    protected static final String STREET_NAME = "Random Street";
    protected static final String STREET_NAME_2 = "Random Street 2";
    protected static final int STREET_NUMBER = 10;
    protected static final int ZIP_CODE = 94600;
    protected static final String STATE = "TX";
    protected static final String COUNTRY = "USA";
    protected static final String EMAIL_3 = " c@c.com";
    protected static final String PHONE_NUMBER = "1234567890";
    protected static final String POSITION_NAME = "Position Name";
    protected static final String POSITION_NAME_2 = "Position Name 2";
    protected static double SALARY = 100.00;

    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.setBirthday(BIRTHDAY);
        employee.setCorpEmail(EMAIL_1);
        employee.setGender(GENDER);
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(LAST_NAME);
        employee.setStatus(STATUS);
        return employee;
    }

    public ContactInformation getContactInformation() {
        ContactInformation ci = new ContactInformation();
        ci.setStreetName(STREET_NAME);
        ci.setStreetNumber(STREET_NUMBER);
        ci.setZipCode(ZIP_CODE);
        ci.setState(STATE);
        ci.setCountry(COUNTRY);
        ci.setEmail(EMAIL_3);
        ci.setPhoneNumber(PHONE_NUMBER);
        return ci;
    }

    public Position getPosition(int i) {
        Position position = new Position();
        position.setPositionName(POSITION_NAME + i);
        position.setSalary(SALARY * i);
        return position;
    }

    public List<Position> getPositionList() {
        List<Position> result = new ArrayList<>();
        result.add(getPosition(1));
        result.add(getPosition(2));
        result.add(getPosition(3));
        return result;
    }
}
