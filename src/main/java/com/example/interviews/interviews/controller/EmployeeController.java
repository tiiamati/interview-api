package com.example.interviews.interviews.controller;

import com.example.interviews.interviews.config.ConnectionFactory;
import com.example.interviews.interviews.dao.EmployeeDAO;
import com.example.interviews.interviews.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @GetMapping
    public List<Employee> getInterview() throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);

            List<Employee> employeeList = employeeDAO.select();

            employeeList.stream().forEach(i -> System.out.println(i));

            return employeeList;
        }
    }

    @GetMapping(path = "/{id}")
    public List<Employee> getInterviewById() throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);

            return employeeDAO.select();
        }
    }

    @PostMapping
    public String setEmployee(@RequestBody Employee employee) throws SQLException {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);

            return employeeDAO.insert(employee);
        }
    }
}
