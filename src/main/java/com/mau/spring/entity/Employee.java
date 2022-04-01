package com.mau.spring.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String corpEmail;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthday;
    private String status;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ContactInformation contactInformation;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Position position;

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
        this.contactInformation.setEmployee(this);
    }

    public void setPosition(Position position) {
        this.position = position;
        this.position.setEmployee(this);
    }


}
