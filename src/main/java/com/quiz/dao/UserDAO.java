package com.quiz.dao;

import com.quiz.model.User;
import com.quiz.util.DBConnection;
import java.sql.*;

public class UserDAO {
    public User login(String email, String password) {
        String sql="SELECT id,name,email,role FROM users WHERE email=? AND password=?";
        try(Connection c=DBConnection.getConnection();
            PreparedStatement p=c.prepareStatement(sql)) {
            p.setString(1,email); p.setString(2,password);
            ResultSet r=p.executeQuery();
            if(r.next()) return new User(r.getInt("id"),r.getString("name"),
                r.getString("email"),r.getString("role"));
        } catch(Exception e){ e.printStackTrace(); }
        return null;
    }

    public boolean register(String name,String email,String password) {
        String sql="INSERT INTO users(name,email,password,role) VALUES(?,?,?,'STUDENT')";
        try(Connection c=DBConnection.getConnection();
            PreparedStatement p=c.prepareStatement(sql)) {
            p.setString(1,name); p.setString(2,email); p.setString(3,password);
            return p.executeUpdate()==1;
        } catch(Exception e){ return false; }
    }
}
