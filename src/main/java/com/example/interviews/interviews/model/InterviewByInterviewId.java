package com.example.interviews.interviews.model;

import lombok.Builder;

import java.util.List;

@Builder
public class InterviewByInterviewId {
    private int id;
    private Interviewee interviewee;
    private List<Properties> properties;
    private Boolean important;
    private String date;
    private String annotation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Interviewee getInterviewee() {
        return interviewee;
    }

    public void setInterviewee(Interviewee interviewee) {
        this.interviewee = interviewee;
    }

    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
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
}
