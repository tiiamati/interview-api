package com.example.interviews.interviews.dao;

import com.example.interviews.interviews.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public String insert(Employee employee) throws SQLException {

        String sql = "INSERT INTO EMPLOYEE (name, document_number) VALUES ( ?, ?)";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getDocumentNumber());

            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    employee.setId(resultSet.getInt(1));
                }
            }
        }
        return "Registro Inserido com sucesso!";
    }

    public Employee select(int id) throws SQLException {

        String sql = "SELECT * FROM EMPLOYEE WHERE id = ?";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    return Employee.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .documentNumber(resultSet.getNString(3))
                            .build();
                }
            }
            return null;
        }
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
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .documentNumber(resultSet.getNString(3))
                            .build();

                    employeesList.add(employee);
                }

                return employeesList;
            }
        }
    }
}
