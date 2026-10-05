package com.college.lostfound.dao;

import com.college.lostfound.database.DBConnection;
import com.college.lostfound.model.User;
import java.sql.*;

public class UserDAO {
    public boolean register(String name,String rollNo,String email,String phone,String password) throws SQLException {
        String sql="INSERT INTO users(name,roll_no,email,phone,password) VALUES(?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,name);p.setString(2,rollNo);p.setString(3,email);p.setString(4,phone);p.setString(5,password);p.executeUpdate();return true;
        }
    }
    public User login(String email,String password) throws SQLException {
        String sql="SELECT user_id,name,roll_no,email,phone FROM users WHERE email=? AND password=?";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,email);p.setString(2,password);try(ResultSet r=p.executeQuery()){
                if(r.next()) return new User(r.getInt("user_id"),r.getString("name"),r.getString("roll_no"),r.getString("email"),r.getString("phone"));
            }
        } return null;
    }
    public boolean adminLogin(String username,String password) throws SQLException {
        String sql="SELECT admin_id FROM admin WHERE username=? AND password=?";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,username);p.setString(2,password);try(ResultSet r=p.executeQuery()){return r.next();}
        }
    }
}
