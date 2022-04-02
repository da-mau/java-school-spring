package com.mau.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table
public class ContactInformation {
    @Id
    @Column(name = "contact_information_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactInformationId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @ToString.Exclude
    @JsonIgnore
    private Employee employee;
    @NotEmpty(message = "Address is mandatory")
    private String streetName;
    @Min(value = 0, message = "Address is mandatory")
    private int streetNumber;
    @Min(value = 0, message = "Address is mandatory")
    private int zipCode;
    @NotEmpty(message = "Address is mandatory")
    private String state;
    @NotEmpty(message = "Address is mandatory")
    private String country;
    @Column(name = "personal_email")
    private String email;
    private String phoneNumber;
}
