package com.example.interviews.interviews.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interviewee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cellphone;
    private String email;
    private String linkedin;
    private String curriculum;
    private String city;
    private String dateOfBirth;
    private String timeWorkedInTheArea;
    private Timestamp timestamp;
    private Status status;
}
