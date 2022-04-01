package com.mau.spring.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table
public class Position {
    @Id
    @Column(name = "position_id")
    @GeneratedValue
    private Long positionId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;
    private String positionName;
    private double salary;
    private Date startDate;
    private Date endDate;

}
