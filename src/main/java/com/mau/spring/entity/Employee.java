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
    public static final String STATUS_INACTIVE = "Inactive";
    public static final String STATUS_ACTIVE = "Active";
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String corpEmail;
    private String firstName;
    private String lastName;
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private String status;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ContactInformation contactInformation;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @OrderColumn(name = "position_order")
    private List<Position> positions;

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
        this.contactInformation.setEmployee(this);
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
        if(positions != null){
            for (Position p : positions) {
                p.setEmployee(this);
            }
        }

    }

    public void addPosition(Position position) {
        position.setStatus(STATUS_ACTIVE);
        if (this.getPositions().isEmpty()) {
            List<Position> positionAux = new ArrayList<>();
            positionAux.add(position);
            this.setPositions(positionAux);
        } else {
            position.setEmployee(this);
            List<Position> positions = this.getPositions();
            for(Position aux : positions){
                aux.setStatus(STATUS_INACTIVE);
            }
            positions.add(position);
        }
    }
}
