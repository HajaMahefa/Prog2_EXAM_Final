package com.hajamodel.modealice.DAO;

import lombok.Getter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
@Getter
public class GenricDAO {
    private final Connection connection;
    private static GenricDAO instance;

    private GenricDAO() {

        try {
            this.connection = new ConnectDB().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        instance = new GenricDAO();

    }

    public static GenricDAO getInstance() {
        return instance;
    }

    public ResultSet getResult(String sql) {
        try {
            return connection.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getNext(String sql) {
        try {
            return connection.prepareStatement(sql).executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}