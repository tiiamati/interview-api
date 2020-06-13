package com.example.interviews.interviews.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
