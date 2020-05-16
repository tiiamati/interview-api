package com.example.interviews.interviews.controller;

import com.example.interviews.interviews.config.ConnectionFactory;
import com.example.interviews.interviews.dao.EmployeeDAO;
import com.example.interviews.interviews.dao.InterviewDAO;
import com.example.interviews.interviews.model.Employee;
import com.example.interviews.interviews.model.Interview;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    @GetMapping(path = "/employee/{id}")
    public List<Interview> getInterview(@PathVariable("id") int id) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            List<Interview> interviewList = interviewDAO.select(Employee.builder().id(id).build());

            interviewList.stream().forEach(i -> System.out.println(i));

            return interviewList;
        }
    }

    @GetMapping(path = "/employee/{id_employee}/{id_interview}")
    public Interview getInterviewById(@PathVariable("id_employee") int id_employee, @PathVariable("id_interview") int id_interview) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            return interviewDAO.select(Employee.builder().id(id_employee).build(), id_interview);
        }
    }

    @PostMapping
    public String setInterview(@RequestBody Interview interview) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            return interviewDAO.insert(interview);
        }
    }
}
