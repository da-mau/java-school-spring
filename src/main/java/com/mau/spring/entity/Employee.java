package com.mau.spring.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Contact Information cannot be null")
    private ContactInformation contactInformation;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @OrderColumn(name = "order")
    private List<Position> positions;

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
        this.contactInformation.setEmployee(this);
    }

    public void setPositions(List<Position> positions) {
            this.positions = positions;
            for(Position p : positions){
                p.setEmployee(this);
    }
//        this.position = position;
//        this.position.setEmployee(this);
    }


}
