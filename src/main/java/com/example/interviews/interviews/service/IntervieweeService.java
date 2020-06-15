package com.example.interviews.interviews.service;

import com.example.interviews.interviews.dao.IntervieweeDAO;
import com.example.interviews.interviews.model.Interviewee;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviewee")
public class IntervieweeService {

    @GetMapping
    public List<Interviewee> getInterviewee() throws Exception {
        return IntervieweeDAO.select();
    }

    @GetMapping(path = "/{id}")
    public Interviewee getIntervieweeById(@PathVariable("id") Long id) throws Exception {
        return IntervieweeDAO.select(Interviewee.builder().id(id).build());
    }

    @PostMapping
    public String setInterviewee(@RequestBody Interviewee interviewee) {
        return IntervieweeDAO.insert(interviewee);
    }

    @PostMapping(path = "/{id}")
    public String updateInterviewee(@PathVariable("id") Long id, @RequestBody Interviewee interviewee) {
        return IntervieweeDAO.update(id, interviewee);
    }
}
