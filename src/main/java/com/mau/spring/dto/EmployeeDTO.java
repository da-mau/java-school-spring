package com.mau.spring.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.beans.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class EmployeeDTO {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");
    private Long employeeId;
    @NotEmpty(message = "Email is mandatory")
    private String corpEmail;
    @NotEmpty(message = "First name is mandatory")
    private String firstName;
    @NotEmpty(message = "Last name is mandatory")
    private String lastName;
    @NotEmpty(message = "Gender is mandatory")
    private String gender;
    @NotEmpty(message = "Birthday is mandatory")
    private String birthday;
    @Valid
    @NotNull(message = "Contact Information cannot be null")
    private ContactInformationDTO contactInformation;

    @Transient
    public Date getBirthdayDateConverted() throws ParseException {
        if(this.birthday != null){
            return dateFormat.parse(this.birthday);
        }else{
            return null;
        }

    }

    public void setBirthdayDate(Date date) {
        if(date != null){
            this.birthday = dateFormat.format(date);
        }

    }

}
