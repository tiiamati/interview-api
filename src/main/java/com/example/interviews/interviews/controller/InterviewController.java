package com.example.interviews.interviews.controller;

import com.example.interviews.interviews.config.ConnectionFactory;
import com.example.interviews.interviews.dao.InterviewDAO;
import com.example.interviews.interviews.model.Employee;
import com.example.interviews.interviews.model.Interview;
import com.example.interviews.interviews.model.InterviewByInterviewId;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/interview")
public class InterviewController {

    @GetMapping
    public List<Interview> getInterview() throws Exception {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);
            List<Interview> interviewList = interviewDAO.select();

            return interviewList;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping(path = "/employee/{id}")
    public List<Interview> getInterviewByEmployee(@PathVariable("id") int id) throws Exception {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);
            List<Interview> interviewList = interviewDAO.select(Employee.builder().id(id).build());
            orderInterview(interviewList);

            return interviewList;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @PostMapping(path = "/employee/{id}")
    public List<Interview> getInterviewByEmployee(@PathVariable("id") int id, @RequestBody Map<String, Object> payload) throws Exception {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);
            List<Interview> interviewList = interviewDAO.select(Employee.builder().id(id).build(), payload);
            orderInterview(interviewList);

            return interviewList;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping(path = "/employee/{id_employee}/{id_interview}")
    public InterviewByInterviewId getInterviewByInterviewId(@PathVariable("id_employee") int id_employee, @PathVariable("id_interview") int id_interview) throws Exception {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);
            InterviewByInterviewId interviewByInterviewId = interviewDAO.select(id_employee, id_interview);

            return interviewByInterviewId;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @PostMapping
    public String insertInterview(@RequestBody Interview interview) throws Exception {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);
            String result = interviewDAO.insert(interview);

            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @PostMapping(path = "/employee/{id_employee}/{id_interview}")
    public String updateInterviewByInterviewId(@PathVariable("id_interview") int id_interview, @RequestBody Interview interview) throws Exception {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            InterviewDAO interviewDAO = new InterviewDAO(connection);
            String result = interviewDAO.update(interview, id_interview);

            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Interview> orderInterview(List<Interview> interviewList) {

        try {
            interviewList.sort(Comparator.comparing(Interview::getHour));
            interviewList.sort(Comparator.comparing(Interview::getDate));

            return interviewList;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
