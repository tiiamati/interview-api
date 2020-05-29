package com.example.interviews.interviews.controller;

import com.example.interviews.interviews.config.ConnectionFactory;
import com.example.interviews.interviews.dao.IntervieweeDAO;
import com.example.interviews.interviews.model.Interviewee;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/interviewee")
public class IntervieweeController {

    @GetMapping
    public List<Interviewee> getInterviewee() throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            IntervieweeDAO intervieweeDAO = new IntervieweeDAO(connection);

            List<Interviewee> intervieweeList = intervieweeDAO.select();
            intervieweeList.stream().forEach(i -> System.out.println(i));

            connection.close();

            return intervieweeList;
        }
    }

    @GetMapping(path = "/{id}")
    public Interviewee getIntervieweeById(@PathVariable("id") int id) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            IntervieweeDAO intervieweeDAO = new IntervieweeDAO(connection);

            Interviewee result = intervieweeDAO.select(id);
            connection.close();

            return result;
        }
    }

    @PostMapping
    public String setInterviewee(@RequestBody Interviewee interviewee) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            IntervieweeDAO intervieweeDAO = new IntervieweeDAO(connection);

            String result = intervieweeDAO.insert(interviewee);
            connection.close();

            return result;
        }
    }
}
