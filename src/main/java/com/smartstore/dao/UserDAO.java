package com.smartstore.dao;

import com.smartstore.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean loginUser(user user) {

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = SQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            if (conn == null) {
                System.out.println("Connection failed!");
                return false;
            }

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user.setRole(rs.getString("role"));
                return true;
            }

        } catch (Exception e) {
            System.out.println("Login Query Error: " + e.getMessage());
        }

        return false;
    }
}