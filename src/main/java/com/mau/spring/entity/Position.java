package com.mau.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
public class Position {
    @Id
    @Column(name = "position_id")
    @GeneratedValue
    private Long positionId;
    @ManyToOne
//    @MapsId
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @JsonIgnore
    @ToString.Exclude
    private Employee employee;
    private String positionName;
    private double salary;
//    private Date startDate;
//    private Date endDate;

}
