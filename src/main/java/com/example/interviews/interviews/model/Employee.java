package com.example.interviews.interviews.model;

import lombok.Builder;

@Builder
public class Employee {
    private int id;
    private String name;
    private String documentNumber;

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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "'employee':{'id': '" + this.id +
                "', 'name': '" + this.name +
                "', 'documentNumber': '" + this.documentNumber +
                "'}";
    }
}
