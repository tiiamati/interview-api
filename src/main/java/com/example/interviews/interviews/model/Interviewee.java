package com.example.interviews.interviews.model;

import lombok.Builder;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTimeWorkedInTheArea() {
        return timeWorkedInTheArea;
    }

    public void setTimeWorkedInTheArea(String timeWorkedInTheArea) {
        this.timeWorkedInTheArea = timeWorkedInTheArea;
    }

    @Override
    public String toString() {
        return "'interviewee':{'id': '" + this.id +
                "', 'name': '" + this.name +
                "', 'cellphone': '" + this.cellphone +
                "', 'email': '" + this.email +
                "', 'linkedin': '" + this.linkedin +
                "', 'curriculum': '" + this.curriculum +
                "', 'city': '" + this.city +
                "', 'dateOfBirth': '" + this.dateOfBirth +
                "', 'timeWorkedInTheArea': '" + this.timeWorkedInTheArea +
                "'}";
    }
}
