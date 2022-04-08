package com.mau.spring.controller;

import com.mau.spring.dto.EmployeeDTO;
import com.mau.spring.entity.Employee;
import com.mau.spring.projection.EmployeeByLocation;
import com.mau.spring.projection.GenderEmployee;
import com.mau.spring.projection.PositionEmployee;
import com.mau.spring.projection.SalaryRangesByPosition;
import com.mau.spring.service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping
public class ReportController extends AbstractEmployeeController{

    private final ReportService reportService;

    public ReportController(ReportService reportService, ModelMapper modelMapper) {
        super(modelMapper);
        this.reportService = reportService;
    }

    @GetMapping("/report/todayBirthdays")
    public ResponseEntity getCurrentBirthdays() {
        List<Employee> result = reportService.getCurrentBirthdays();
        List<EmployeeDTO> resultDto = convertEmployeesToDto(result);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping("/report/nextWeekBirthdays")
    public ResponseEntity getNextWeekBirthdays() {
        List<Employee> result = reportService.getNextWeekBirthdays();
        List<EmployeeDTO> resultDto = convertEmployeesToDto(result);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}


