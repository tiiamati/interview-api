package com.example.interviews.interviews.controller;

import com.example.interviews.interviews.config.ConnectionFactory;
import com.example.interviews.interviews.dao.InterviewDAO;
import com.example.interviews.interviews.model.Employee;
import com.example.interviews.interviews.model.Interview;
import com.example.interviews.interviews.model.InterviewByInterviewId;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    @GetMapping
    public List<Interview> getInterview() throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            List<Interview> interviewList = interviewDAO.select();

            connection.close();

            return interviewList;
        }
    }

    @GetMapping(path = "/employee/{id}")
    public List<Interview> getInterviewByEmployee(@PathVariable("id") int id) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            List<Interview> interviewList = interviewDAO.select(Employee.builder().id(id).build());
            orderInterview(interviewList);

            connection.close();

            return interviewList;
        }
    }

    @PostMapping(path = "/employee/{id}")
    public List<Interview> getInterviewByEmployee(@PathVariable("id") int id, @RequestBody Map<String, Object> payload) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            List<Interview> interviewList = interviewDAO.select(Employee.builder().id(id).build(), payload);

            orderInterview(interviewList);

            connection.close();

            return interviewList;
        }
    }

    @GetMapping(path = "/employee/{id_employee}/{id_interview}")
    public InterviewByInterviewId getInterviewByInterviewId(@PathVariable("id_employee") int id_employee, @PathVariable("id_interview") int id_interview) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            InterviewByInterviewId interviewByInterviewId = interviewDAO.select(id_employee, id_interview);

            connection.close();

            return interviewByInterviewId;
        }
    }

    @PostMapping
    public String insertInterview(@RequestBody Interview interview) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            String result = interviewDAO.insert(interview);

            connection.close();

            return result;
        }
    }

    @PostMapping(path = "/employee/{id_employee}/{id_interview}")
    public String updateInterviewByInterviewId(@PathVariable("id_interview") int id_interview, @RequestBody Interview interview) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);

            String result = interviewDAO.update(interview, id_interview);

            connection.close();

            return result;
        }
    }

    public List<Interview> orderInterview(List<Interview> interviewList) {
        interviewList.sort(Comparator.comparing(Interview::getHour));
        interviewList.sort(Comparator.comparing(Interview::getDate));

        return interviewList;
    }
}
