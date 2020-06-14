package com.example.interviews.interviews.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
public class Interview {
    private int id;
    private Employee employee;
    private Interviewee interviewee;
    private Boolean important;
    private String date;
    private String hour;
    private String annotation;
}
