package com.example.interviews.interviews.controller;

import com.example.interviews.interviews.config.ConnectionFactory;
import com.example.interviews.interviews.dao.IntervieweeDAO;
import com.example.interviews.interviews.model.Interviewee;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("/api/interviewee")
public class IntervieweeController {

    @GetMapping
    public List<Interviewee> getInterviewee() throws Exception {

        try (Connection connection = new ConnectionFactory().getConnection();) {
            IntervieweeDAO intervieweeDAO = new IntervieweeDAO(connection);
            List<Interviewee> intervieweeList = intervieweeDAO.select();
            intervieweeList.stream().forEach(i -> System.out.println(i));

            return intervieweeList;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @GetMapping(path = "/{id}")
    public Interviewee getIntervieweeById(@PathVariable("id") int id) throws Exception {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            IntervieweeDAO intervieweeDAO = new IntervieweeDAO(connection);
            Interviewee result = intervieweeDAO.select(id);

            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @PostMapping
    public String setInterviewee(@RequestBody Interviewee interviewee) throws Exception {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            IntervieweeDAO intervieweeDAO = new IntervieweeDAO(connection);
            String result = intervieweeDAO.insert(interviewee);

            return result;
        }  catch (Exception ex) {
            throw ex;
        }
    }
}
