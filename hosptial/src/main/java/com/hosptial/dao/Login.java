package com.hosptial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hosptial.connection.DBConnection;
import com.hosptial.model.User;

public class Login {
	public static User getUserByEmailAndPassword(String email, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            String q = "SELECT * FROM User WHERE email = ? AND password = ?";
            statement = con.prepareStatement(q);
            statement.setString(1, email);
            statement.setString(2, password);
            rs = statement.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setSpecification(rs.getString("specification"));
                return user;
            }
        } finally {
            // Close ResultSet, Statement, and Connection in reverse order of their creation
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (con != null) con.close();
        }
        return null;
    }

    public boolean registerUser(User user) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = DBConnection.getConnection();
            String q = "INSERT INTO User (name, email, password, role,specification) VALUES (?, ?, ?, ?,?)";
            statement = con.prepareStatement(q);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, "patient");
            statement.setString(5, "use");

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } finally {
            // Close the statement and connection in reverse order of their creation
            if (statement != null) statement.close();
            if (con != null) con.close();
        }
    }
}
