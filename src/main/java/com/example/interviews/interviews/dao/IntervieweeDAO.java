package com.example.interviews.interviews.dao;



import com.example.interviews.interviews.model.Interviewee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class IntervieweeDAO {

    private Connection connection;

    public IntervieweeDAO(Connection connection) {
        this.connection = connection;
    }

    public String insert(Interviewee interviewee) throws SQLException {

        String sql = "INSERT INTO INTERVIEWEE (name, cellphone, email, curriculum, city, date_of_birth, time_worked, linkedin) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, interviewee.getName());
            preparedStatement.setString(2, interviewee.getCellphone());
            preparedStatement.setString(3, interviewee.getEmail());
            preparedStatement.setString(4, interviewee.getCurriculum());
            preparedStatement.setString(5, interviewee.getCity());
            preparedStatement.setString(6, interviewee.getDateOfBirth());
            preparedStatement.setString(7, interviewee.getTimeWorkedInTheArea());
            preparedStatement.setString(8, interviewee.getLinkedin());

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    interviewee.setId(resultSet.getInt(1));
                }

                preparedStatement.close();
            }
        }

        return "Registro Inserido com sucesso!";
    }

    public List<Interviewee> select() throws SQLException {

        List<Interviewee> intervieweesList = new ArrayList<>();

        String sql = "SELECT * FROM INTERVIEWEE";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    Interviewee interviewee = Interviewee.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .cellphone(resultSet.getNString(3))
                            .email(resultSet.getNString(4))
                            .curriculum(resultSet.getNString(5))
                            .city(resultSet.getNString(6))
                            .dateOfBirth(resultSet.getNString(7))
                            .timeWorkedInTheArea(resultSet.getNString(8))
                            .linkedin(resultSet.getNString(9))
                            .build();

                    intervieweesList.add(interviewee);
                }

                preparedStatement.close();

                return intervieweesList;
            }
        }
    }

    public Interviewee select(int id) throws SQLException {

        String sql = "SELECT * FROM INTERVIEWEE WHERE id = ? ";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    return Interviewee.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .cellphone(resultSet.getNString(3))
                            .email(resultSet.getNString(4))
                            .curriculum(resultSet.getNString(5))
                            .city(resultSet.getNString(6))
                            .dateOfBirth(resultSet.getNString(7))
                            .timeWorkedInTheArea(resultSet.getNString(8))
                            .linkedin(resultSet.getNString(9))
                            .build();
                }

                preparedStatement.close();
            }
        }
        return null;
    }
}
