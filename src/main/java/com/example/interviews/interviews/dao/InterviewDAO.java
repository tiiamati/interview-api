package com.example.interviews.interviews.dao;


import com.example.interviews.interviews.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterviewDAO {

    private String selectQuery = "SELECT " +
            "i.id, i.important, i.date, i.annotation, " +
            "e.id, e.name, " +
            "interviewee.id, interviewee.name, interviewee.cellphone, interviewee.email, interviewee.curriculum, interviewee.city, " +
            "interviewee.date_of_birth, interviewee.time_worked, interviewee.linkedin FROM INTERVIEW i " +
            "INNER JOIN INTERVIEWEE interviewee ON interviewee.id = i.id_interviewee " +
            "INNER JOIN EMPLOYEE e ON e.id = i.id_employee ";

    private Connection connection;

    public InterviewDAO(Connection connection) {
        this.connection = connection;
    }

    private Interview setInterview(ResultSet resultSet) throws SQLException {

        return Interview.builder()
                .id(resultSet.getInt(1))
                .important(resultSet.getBoolean(2))
                .date(resultSet.getString(3))
                .annotation(resultSet.getString(4))
                .employee(Employee.builder()
                    .id(resultSet.getInt(5))
                    .name(resultSet.getString(6))
                    .build())
                .interviewee(Interviewee.builder()
                        .id(resultSet.getInt(7))
                        .name(resultSet.getString(8))
                        .cellphone(resultSet.getString(9))
                        .email(resultSet.getString(10))
                        .curriculum(resultSet.getString(11))
                        .city(resultSet.getString(12))
                        .dateOfBirth(resultSet.getString(13))
                        .timeWorkedInTheArea(resultSet.getString(14))
                        .linkedin(resultSet.getString(15))
                        .build())
                .build();
    }

    private Interview setInterviewEmployee(ResultSet resultSet) throws SQLException {

        return Interview.builder()
                .id(resultSet.getInt(1))
                .important(resultSet.getBoolean(2))
                .date(resultSet.getString(3))
                .annotation(resultSet.getString(4))
                .interviewee(Interviewee.builder()
                        .id(resultSet.getInt(7))
                        .name(resultSet.getString(8))
                        .build())
                .build();
    }

    private InterviewByInterviewId setInterviewEmployeeDetailed(ResultSet resultSet) throws SQLException {

        return InterviewByInterviewId.builder()
                .id(resultSet.getInt(1))
                .important(resultSet.getBoolean(2))
                .date(resultSet.getString(3))
                .annotation(resultSet.getString(4))
                .interviewee(Interviewee.builder()
                        .id(resultSet.getInt(7))
                        .name(resultSet.getString(8))
                        .cellphone(resultSet.getString(9))
                        .email(resultSet.getString(10))
                        .curriculum(resultSet.getString(11))
                        .city(resultSet.getString(12))
                        .dateOfBirth(resultSet.getString(13))
                        .timeWorkedInTheArea(resultSet.getString(14))
                        .linkedin(resultSet.getString(15))
                        .build())
                .properties(setToarrayListInterviewee(resultSet))
                .build();
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

    public List<Interview> select() throws SQLException {

        List<Interview> interviewList = new ArrayList<>();

        String sql = selectQuery;

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    interviewList.add(setInterview(resultSet));
                }
                return interviewList;
            }
        }
    }

    public InterviewByInterviewId select(int employee, int id) throws SQLException {

        String sql = selectQuery + "WHERE e.id = ? AND i.id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, employee);
            preparedStatement.setInt(2, id);

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    return setInterviewEmployeeDetailed(resultSet);
                }
            }
            return null;
        }
    }

    public List<Interview> select(Employee employee) throws SQLException {

        List<Interview> interviewList = new ArrayList<>();

        String sql = selectQuery + "WHERE e.id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, employee.getId());

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Interview interview = setInterviewEmployee(resultSet);

                    interviewList.add(interview);
                }
                return interviewList;
            }
        }
    }

    public List<Properties> setToarrayListInterviewee(ResultSet resultSet) throws SQLException {

        List<Properties> properties = new ArrayList<>();

        Properties cellphone = Properties.builder()
                .label("Celular: ")
                .description(resultSet.getString(9))
                .build();
        properties.add(cellphone);

        Properties email = Properties.builder()
                .label("Email: ")
                .description(resultSet.getString(10))
                .build();
        properties.add(email);

        Properties city = Properties.builder()
                .label("Cidade: ")
                .description(resultSet.getString(12))
                .build();
        properties.add(city);

        Properties curriculum = Properties.builder()
                .label("Curriculo: ")
                .description(resultSet.getString(11))
                .build();
        properties.add(curriculum);

        Properties dateOfBirth = Properties.builder()
                .label("Data de nascimento: ")
                .description(resultSet.getString(13))
                .build();
        properties.add(dateOfBirth);

        Properties linkedin = Properties.builder()
                .label("Linkedin: ")
                .description(resultSet.getString(15))
                .build();
        properties.add(linkedin);

        Properties timeWorked = Properties.builder()
                .label("Tempo na area: ")
                .description(resultSet.getString(14))
                .build();
        properties.add(timeWorked);

        return properties;
    }
}
