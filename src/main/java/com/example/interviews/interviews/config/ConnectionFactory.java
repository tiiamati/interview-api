package com.example.interviews.interviews.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.sql.SQLException;

public class ConnectionFactory {

    private String URL_DATABASE = "jdbc:mysql://localhost/interviews?useTimezone=true&serverTimezone=UTC";
    private String USER_DATABASE = "root";
    private String PASSWORD_DATABASE = "cinema123";
    private DataSource dataSource;

    public ConnectionFactory() {



        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(URL_DATABASE);
        comboPooledDataSource.setUser(USER_DATABASE);
        comboPooledDataSource.setPassword(PASSWORD_DATABASE);

        comboPooledDataSource.setMaxPoolSize(20);

        this.dataSource = comboPooledDataSource;
    }

    public java.sql.Connection getConnection () throws SQLException {
        return this.dataSource.getConnection();
    }
}
