package com.mau.spring.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.beans.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class EmployeeAdminDTO {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");
    private Long employeeId;
    private String corpEmail;
    private String firstName;
    private String lastName;
    private String gender;
    private String birthday;
    private String status;
    private ContactInformationDTO contactInformation;
    private List<PositionDTO> positions;


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

    public void setPositions(List<PositionDTO> positions) {
        this.positions = positions;
        if(positions != null){
            for (PositionDTO p : positions) {
            }
        }

    }

    public void addPosition(PositionDTO position) {
        if (this.getPositions().isEmpty()) {
            List<PositionDTO> positionAux = new ArrayList<>();
            positionAux.add(position);
            this.setPositions(positionAux);
        } else {
            this.getPositions().add(position);
        }
    }
}
