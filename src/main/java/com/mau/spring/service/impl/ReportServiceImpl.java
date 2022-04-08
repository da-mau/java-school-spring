package com.mau.spring.service.impl;

import com.mau.spring.dto.EmployeeDTO;
import com.mau.spring.entity.Employee;
import com.mau.spring.projection.EmployeeByLocation;
import com.mau.spring.projection.GenderEmployee;
import com.mau.spring.projection.PositionEmployee;
import com.mau.spring.projection.SalaryRangesByPosition;
import com.mau.spring.repository.EmployeeRepository;
import com.mau.spring.repository.PositionRepository;
import com.mau.spring.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    public ReportServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public List<PositionEmployee> countEmployeesByPosition() {
        return employeeRepository.countEmployeesByPosition();
    }

    @Override
    public List<GenderEmployee> getGenderPercentages() {
        return employeeRepository.getPercentagesByGender();
    }

    @Override
    public List<SalaryRangesByPosition> getSalaryRangesByPosition() {
        List<SalaryRangesByPosition> result = positionRepository.getSalaryRangesByPosition();
        return  result;
    }

    @Override
    public List<EmployeeByLocation> getEmployeesByLocation() {
        return employeeRepository.getEmployeesByCountryAndState();
    }

    @Override
    public List<Employee> getCurrentBirthdays(){
        return employeeRepository.getCurrentBirthdays();
    }

    @Override
    public List<Employee> getNextWeekBirthdays(){
        LocalDateTime startDate = LocalDateTime.now().plusDays(1).with(LocalTime.of(0, 0));
        LocalDateTime endDate = startDate.plusDays(7).with(LocalTime.of(23,59,59));
        return employeeRepository.getNextWeekBirthdays(startDate.toLocalDate(), endDate.toLocalDate());
    }
}
