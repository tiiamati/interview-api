package com.example.interviews.interviews.dao;

import com.example.interviews.interviews.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public static String insert(Employee employee) {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("employee");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        }

        return "Registro Inserido com sucesso!";
    }

    public static String update(Long id, Employee employee) {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("employee");
            EntityManager em = emf.createEntityManager();

            Employee e = em.find(Employee.class, id);

            em.getTransaction().begin();

            e.setName(employee.getName());
            e.setDocumentNumber(employee.getDocumentNumber());
            e.setStatus(employee.getStatus());

            em.getTransaction().commit();

        } catch (Exception ex) {
            throw ex;
        }

        return "Registro alterado com sucesso!";
    }

    public Employee select(int id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("employee");
        EntityManager em = emf.createEntityManager();

        Employee e = em.find(Employee.class, id);

        return e;
    }

    public List<Employee> select() throws SQLException {

        List<Employee> employeesList = new ArrayList<>();

        String sql = "SELECT * FROM EMPLOYEE";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Employee employee = Employee.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .documentNumber(resultSet.getNString(3))
                            .build();

                    employeesList.add(employee);
                }

                preparedStatement.close();
            }
            return employeesList;
        }
    }
}


