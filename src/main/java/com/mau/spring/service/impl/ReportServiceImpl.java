package com.mau.spring.service.impl;

import com.mau.spring.projection.GenderEmployee;
import com.mau.spring.projection.PositionEmployee;
import com.mau.spring.projection.SalaryRangesByPosition;
import com.mau.spring.repository.EmployeeRepository;
import com.mau.spring.repository.PositionRepository;
import com.mau.spring.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private static final String STATUS_INACTIVE = "Inactive";
    private static final String STATUS_ACTIVE = "Active";


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
}
