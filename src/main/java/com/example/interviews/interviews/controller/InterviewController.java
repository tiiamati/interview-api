package com.example.interviews.interviews.controller;

import com.example.interviews.interviews.model.Interview;

import java.util.Comparator;
import java.util.List;


public class InterviewController {

    public static List<Interview> orderInterview(List<Interview> interviewList) {

        try {
            interviewList.sort(Comparator.comparing(Interview::getHour));
            interviewList.sort(Comparator.comparing(Interview::getDate));

            return interviewList;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
