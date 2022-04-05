package com.mau.spring.service;

import com.mau.spring.projection.GenderEmployee;
import com.mau.spring.projection.PositionEmployee;
import com.mau.spring.projection.SalaryRangesByPosition;

import java.util.List;

public interface ReportService {

    List<PositionEmployee> countEmployeesByPosition();
    List<GenderEmployee> getGenderPercentages();
    List<SalaryRangesByPosition> getSalaryRangesByPosition();
}
