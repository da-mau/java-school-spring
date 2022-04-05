package com.mau.spring.repository;

import com.mau.spring.entity.Position;
import com.mau.spring.projection.SalaryRangesByPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query(value = "Select ep.positionName as positionName , count(*) as employees, max (ep.salary) as max, min (ep.salary) as min\n" +
            "from Position as ep \n" +
            "where ep.status = 'Active'\n" +
            "group by(ep.positionName)")
    List<SalaryRangesByPosition> getSalaryRangesByPosition();



}
