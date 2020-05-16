package com.example.interviews.interviews.dao;


import com.example.interviews.interviews.model.Employee;
import com.example.interviews.interviews.model.Interview;
import com.example.interviews.interviews.model.Interviewee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterviewDAO {

    private Connection connection;

    public InterviewDAO(Connection connection) {
        this.connection = connection;
    }

    public String insert(Interview interview) throws SQLException {

        String sql = "INSERT INTO INTERVIEW (id_employee, id_interviewee, important, date, annotation) VALUES ( ?, ?, ?, ?, ?)";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, interview.getEmployee().getId());
            preparedStatement.setInt(2, interview.getInterviewee().getId());
            preparedStatement.setBoolean(3, interview.getImportant());
            preparedStatement.setString(4, interview.getDate());
            preparedStatement.setString(5, interview.getAnnotation());

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    interview.setId(resultSet.getInt(1));
                }
            }
        }

        return "Registro Inserido com sucesso!";
    }

    public Interview select(Employee employee, int id) throws SQLException {

        List<Interview> interviewList = new ArrayList<>();

        String sql = "SELECT i.id, interviewee.id, interviewee.name, interviewee.cellphone, interviewee.email, interviewee.curriculum, interviewee.city, " +
                "interviewee.date_of_birth, interviewee.time_worked, interviewee.linkedin, i.important, i.date, i.annotation FROM INTERVIEW i " +
                "INNER JOIN INTERVIEWEE interviewee ON interviewee.id = i.id_interviewee " +
                "INNER JOIN EMPLOYEE e ON e.id = i.id_employee " +
                "WHERE e.id = ? " +
                "AND i.id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setInt(2, id);

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    return Interview.builder()
                            .id(resultSet.getInt(1))
                            .interviewee(Interviewee.builder()
                                    .id(resultSet.getInt(2))
                                    .name(resultSet.getString(3))
                                    .cellphone(resultSet.getString(4))
                                    .cellphone(resultSet.getString(5))
                                    .cellphone(resultSet.getString(6))
                                    .cellphone(resultSet.getString(7))
                                    .cellphone(resultSet.getString(8))
                                    .cellphone(resultSet.getString(9))
                                    .cellphone(resultSet.getString(10))
                                    .build())
                            .important(resultSet.getBoolean(11))
                            .date(resultSet.getString(12))
                            .annotation(resultSet.getString(13))
                            .build();
                }
            }
            return null;
        }
    }

    public List<Interview> select(Employee employee) throws SQLException {

        List<Interview> interviewList = new ArrayList<>();

        String sql = "SELECT i.id, interviewee.id, interviewee.name, interviewee.cellphone, interviewee.email, interviewee.curriculum, interviewee.city, " +
                "interviewee.date_of_birth, interviewee.time_worked, interviewee.linkedin, i.important, i.date, i.annotation FROM INTERVIEW i " +
                "INNER JOIN INTERVIEWEE interviewee ON interviewee.id = i.id_interviewee " +
                "INNER JOIN EMPLOYEE e ON e.id = i.id_employee " +
                "WHERE e.id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, employee.getId());

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Interview interview = Interview.builder()
                            .id(resultSet.getInt(1))
                            .interviewee(Interviewee.builder()
                                                    .id(resultSet.getInt(2))
                                                    .name(resultSet.getString(3))
                                                    .cellphone(resultSet.getString(4))
                                                    .cellphone(resultSet.getString(5))
                                                    .cellphone(resultSet.getString(6))
                                                    .cellphone(resultSet.getString(7))
                                                    .cellphone(resultSet.getString(8))
                                                    .cellphone(resultSet.getString(9))
                                                    .cellphone(resultSet.getString(10))
                                                    .build())
                            .important(resultSet.getBoolean(11))
                            .date(resultSet.getString(12))
                            .annotation(resultSet.getString(13))
                            .build();

                    interviewList.add(interview);
                }
                return interviewList;
            }
        }
    }
}
