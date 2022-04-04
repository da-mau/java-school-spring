package com.mau.spring.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ContactInformationDTO {
    private Long contactInformationId;
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
