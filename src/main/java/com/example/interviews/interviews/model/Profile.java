package com.example.interviews.interviews.model;

import lombok.Builder;

@Builder
public class Profile {
    private int id;
    private String description;

    @Override
    public String toString() {
        return "'profile':{'id': '" + this.id + "', 'description': '" + this.description + "'}";
    }
}
