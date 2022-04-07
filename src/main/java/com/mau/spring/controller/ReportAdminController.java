package com.mau.spring.controller;

import com.mau.spring.projection.EmployeeByLocation;
import com.mau.spring.projection.GenderEmployee;
import com.mau.spring.projection.PositionEmployee;
import com.mau.spring.projection.SalaryRangesByPosition;
import com.mau.spring.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/admin")
public class ReportAdminController {

    private final ReportService reportService;

    public ReportAdminController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report/countByPosition")
    public ResponseEntity getCountByPosition() {
        List<PositionEmployee> result = reportService.countEmployeesByPosition();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/report/percentageByGender")
    public ResponseEntity getPercentageByGender() {
        List<GenderEmployee> result = reportService.getGenderPercentages();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/report/rangesByPosition")
    public ResponseEntity getSalaryRangesByPosition() {
        List<SalaryRangesByPosition> result = reportService.getSalaryRangesByPosition();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/report/employeesByLocation")
    public ResponseEntity getEmployeesByLocation() {
        List<EmployeeByLocation> result = reportService.getEmployeesByLocation();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


