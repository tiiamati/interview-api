package com.example.interviews.interviews.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;
    @ManyToOne
    private Interviewee interviewee;
    private Boolean important;
    private String date;
    private String hour;
    private String annotation;
    private Boolean done;
    private Timestamp timestamp;
    private Status status;
}
