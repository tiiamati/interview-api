package com.example.interviews.interviews.dao;

import com.example.interviews.interviews.config.ConnectionFactory;
import com.example.interviews.interviews.model.Employee;
import com.example.interviews.interviews.translate.Strings;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class EmployeeDAO {

    public static String insert(Employee employee) {

        try {
            EntityManager em = ConnectionFactory.Connection();

            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }

        return Strings.BD_SAVE;
    }

    public static String update(Long id, Employee employee) {

        try {
            EntityManager em = ConnectionFactory.Connection();

            Employee e = em.find(Employee.class, id);

            em.getTransaction().begin();

            e.setName(employee.getName());
            e.setDocumentNumber(employee.getDocumentNumber());
            e.setStatus(employee.getStatus());

            em.getTransaction().commit();

        } catch (Exception ex) {
            throw ex;
        }

        return Strings.BD_UPDATE;
    }

    public static Employee select(Employee employee) {
        try {
            EntityManager em = ConnectionFactory.Connection();
            return em.find(Employee.class, employee.getId());
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List<Employee> select() {
        try {
            String jpql = "select e from Employee e";
            TypedQuery<Employee> query = ConnectionFactory.Connection().createQuery(jpql, Employee.class);
            return query.getResultList();
        } catch (Exception ex){
            throw ex;
        }
    }
}


