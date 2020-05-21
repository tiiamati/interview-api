package com.example.interviews.interviews.controller;

import com.example.interviews.interviews.config.ConnectionFactory;
import com.example.interviews.interviews.dao.InterviewDAO;
import com.example.interviews.interviews.model.Employee;
import com.example.interviews.interviews.model.Interview;
import com.example.interviews.interviews.model.InterviewByInterviewId;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    @GetMapping
    public List<Interview> getInterview() throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            List<Interview> interviewList = interviewDAO.select();

            interviewList.stream().forEach(i -> System.out.println(i));

            return interviewList;
        }
    }

    @GetMapping(path = "/employee/{id}")
    public List<Interview> getInterviewByEmployee(@PathVariable("id") int id) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            List<Interview> interviewList = interviewDAO.select(Employee.builder().id(id).build());

            interviewList.stream().forEach(i -> System.out.println(i));

            return interviewList;
        }
    }

    @GetMapping(path = "/employee/{id_employee}/{id_interview}")
    public InterviewByInterviewId getInterviewByInterviewId(@PathVariable("id_employee") int id_employee, @PathVariable("id_interview") int id_interview) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            InterviewByInterviewId interviewByInterviewId = interviewDAO.select(id_employee, id_interview);

            return interviewByInterviewId;
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
