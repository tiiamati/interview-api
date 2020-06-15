package com.example.interviews.interviews.dao;

import com.example.interviews.interviews.config.ConnectionFactory;
import com.example.interviews.interviews.model.*;
import com.example.interviews.interviews.translate.Strings;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class InterviewDAO {

    public static String insert(Interview interview) {

        try {
            EntityManager em = ConnectionFactory.Connection();

            em.getTransaction().begin();
            em.persist(interview);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }

        return Strings.BD_SAVE;
    }

    public static List<Interview> select() {

        try {
            String jpql = "select i from Interview i";
            TypedQuery<Interview> query = ConnectionFactory.Connection().createQuery(jpql, Interview.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Interview select(Interview interview) {
        try {
            EntityManager em = ConnectionFactory.Connection();
            return em.find(Interview.class, interview.getId());
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List<Interview> select(Employee employee) {
        try {
            String jpql = "select i from Interview i where i.employee = :employee and cast(i.date as date) >= current_date()";
            TypedQuery<Interview> query = ConnectionFactory.Connection().createQuery(jpql, Interview.class);
            return query.setParameter("employee", employee).getResultList();
        } catch (Exception ex){
            throw ex;
        }
    }

    public static String update(Interview interview, Long id) {

        try {
            EntityManager em = ConnectionFactory.Connection();

            Interview interviewDAO = em.find(Interview.class, id);

            em.getTransaction().begin();

            interviewDAO.setAnnotation(interview.getAnnotation());
            interviewDAO.setDate(interview.getDate());
            interviewDAO.setEmployee(interview.getEmployee());
            interviewDAO.setHour(interview.getHour());
            interviewDAO.setImportant(interview.getImportant());
            interviewDAO.setStatus(interview.getStatus());
            interviewDAO.setInterviewee(interview.getInterviewee());
            interviewDAO.setDone(interview.getDone());

            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }

        return Strings.BD_UPDATE;
    }
}
