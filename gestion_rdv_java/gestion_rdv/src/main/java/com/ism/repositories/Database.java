package com.ism.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {
    void connect() throws SQLException, ClassNotFoundException;

    void disconnect() throws SQLException;

    ResultSet executeSelect(PreparedStatement preparedStatement) throws SQLException;

    int executeUpdate(PreparedStatement preparedStatement) throws SQLException;

    PreparedStatement getPs(String insertQuery);
}
