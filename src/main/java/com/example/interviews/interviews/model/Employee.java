package com.example.interviews.interviews.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Employee {
    private int id;
    private String name;
    private String documentNumber;
}
