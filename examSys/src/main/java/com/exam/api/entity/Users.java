package com.exam.api.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"SSID", "EMAIL"})
})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String SSID;

    private  String F_NAME;

    private String L_NAME;

    private  String EMAIL;

    private String DEPT;

    private String GENDER;

    private Date DOB;
    @JsonIgnore
    private String PASSWORD;

}
