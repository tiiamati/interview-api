package com.example.interviews.interviews.model;

import lombok.Builder;

@Builder
public class Interview {
    private int id;
    private Employee employee;
    private Interviewee interviewee;
    private Boolean important;
    private String date;
    private String annotation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Interviewee getInterviewee() {
        return interviewee;
    }

    public void setInterviewee(Interviewee interviewee) {
        this.interviewee = interviewee;
    }

    public Boolean getImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "{'interview': {'id': '" + this.id +
                ", " + this.interviewee.toString() +
                ", 'important': '" + this.important +
                "', 'date': '" + this.date +
                "', 'annotation': '" + this.annotation +
                "'}}";
    }

}
