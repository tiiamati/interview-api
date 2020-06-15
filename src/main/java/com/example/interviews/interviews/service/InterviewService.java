package com.example.interviews.interviews.service;

import com.example.interviews.interviews.controller.InterviewController;
import com.example.interviews.interviews.dao.InterviewDAO;
import com.example.interviews.interviews.model.Employee;
import com.example.interviews.interviews.model.Interview;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview")
public class InterviewService {

    @GetMapping
    public List<Interview> getInterview() throws Exception {
        return InterviewDAO.select();
    }

    @GetMapping(path = "/employee/{id}")
    public List<Interview> getInterviewByEmployee(@PathVariable("id") Long id) {
        List<Interview> interviews = InterviewDAO.select(Employee.builder().id(id).build());
        return InterviewController.orderInterview(interviews);
    }

    @GetMapping(path = "/{id}")
    public Interview getInterviewByInterviewId(@PathVariable("id") Long id) {
        return InterviewDAO.select(Interview.builder().id(id).build());
    }

    @PostMapping
    public String insertInterview(@RequestBody Interview interview) {
        return InterviewDAO.insert(interview);
    }

    @PostMapping(path = "/{id}")
    public String updateInterview(@RequestBody Interview interview, @PathVariable("id") Long id) {
        return InterviewDAO.update(interview, id);
    }
}
