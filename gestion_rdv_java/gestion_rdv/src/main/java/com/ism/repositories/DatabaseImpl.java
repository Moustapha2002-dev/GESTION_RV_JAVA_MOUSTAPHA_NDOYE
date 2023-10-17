package com.ism.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseImpl implements Database {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/gestion_rdv_java";
    private final String user = "root";
    private final String password = "";
    private Connection conn = null;

    protected PreparedStatement selectPreparedStatement;
    protected PreparedStatement updatePreparedStatement;


    @Override
    public void connect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void disconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
    }

    @Override
    public ResultSet executeSelect(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
        return resultSet;
    }

    @Override
    public int executeUpdate(PreparedStatement preparedStatement) throws SQLException {
        int rowsUpdated = 0;
        try {
            rowsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

            throw e;
        }
        return rowsUpdated;
    }


    @Override
    public PreparedStatement getPs(String insertQuery) {
        try {
            return conn.prepareStatement(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Failed to create PreparedStatement for insertQuery");
        }
    }

}
