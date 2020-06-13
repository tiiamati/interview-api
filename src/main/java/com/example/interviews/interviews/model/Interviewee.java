package com.example.interviews.interviews.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Interviewee {
    private int id;
    private String name;
    private String cellphone;
    private String email;
    private String linkedin;
    private String curriculum;
    private String city;
    private String dateOfBirth;
    private String timeWorkedInTheArea;
}
