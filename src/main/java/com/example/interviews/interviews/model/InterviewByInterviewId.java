package com.example.interviews.interviews.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class InterviewByInterviewId {
    private int id;
    private Interviewee interviewee;
    private List<Properties> properties;
    private Boolean important;
    private String date;
    private String hour;
    private String annotation;
}
