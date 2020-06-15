package com.example.interviews.interviews.dao;

import com.example.interviews.interviews.config.ConnectionFactory;
import com.example.interviews.interviews.model.Employee;
import com.example.interviews.interviews.model.Interviewee;
import com.example.interviews.interviews.translate.Strings;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.List;


public class IntervieweeDAO {

    public static String insert(Interviewee interviewee) {

        try {
            EntityManager em = ConnectionFactory.Connection();

            em.getTransaction().begin();
            em.persist(interviewee);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }

        return Strings.BD_SAVE;
    }

    public static List<Interviewee> select() {
        try {
            String jpql = "select i from Interviewee i";
            TypedQuery<Interviewee> query = ConnectionFactory.Connection().createQuery(jpql, Interviewee.class);
            return query.getResultList();
        } catch (Exception ex){
            throw ex;
        }
    }

    public static Interviewee select(Interviewee interviewee) {
        try {
            EntityManager em = ConnectionFactory.Connection();
            return em.find(Interviewee.class, interviewee.getId());
        } catch (Exception ex){
            throw ex;
        }
    }

    public static String update(Long id, Interviewee interviewee) {

        try {
            EntityManager em = ConnectionFactory.Connection();

            Interviewee e = em.find(Interviewee.class, id);

            em.getTransaction().begin();

            e.setName(interviewee.getName());
            e.setCellphone(interviewee.getCellphone());
            e.setCity(interviewee.getCity());
            e.setCurriculum(interviewee.getCurriculum());
            e.setDateOfBirth(interviewee.getDateOfBirth());
            e.setEmail(interviewee.getEmail());
            e.setLinkedin(interviewee.getLinkedin());
            e.setTimeWorkedInTheArea(interviewee.getTimeWorkedInTheArea());

            em.getTransaction().commit();

        } catch (Exception ex) {
            throw ex;
        }

        return Strings.BD_UPDATE;
    }
}
