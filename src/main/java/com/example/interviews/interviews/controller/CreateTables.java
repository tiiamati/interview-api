package com.example.interviews.interviews.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateTables {

    public static void main(String[] args) {

        // criando table employee
        EntityManagerFactory employeeFactory = Persistence.createEntityManagerFactory("employee");
        EntityManager employeeManager = employeeFactory.createEntityManager();
        employeeFactory.close();

        //EntityManagerFactory intervieweeFactory = Persistence.createEntityManagerFactory("interviewee");
        //EntityManager intervieweeManager = intervieweeFactory.createEntityManager();
        //intervieweeFactory.close();

    }
}
