package com.quiz.dao;

import com.quiz.model.Question;
import com.quiz.util.DBConnection;
import java.sql.*;
import java.util.*;

public class QuestionDAO {
    public List<Question> getAll() {
        List<Question> list=new ArrayList<>();
        String sql="SELECT * FROM questions ORDER BY id";
        try(Connection c=DBConnection.getConnection();
            PreparedStatement p=c.prepareStatement(sql);
            ResultSet r=p.executeQuery()) {
            while(r.next()) list.add(map(r));
        } catch(Exception e){e.printStackTrace();}
        return list;
    }

    public Question getById(int id) {
        try(Connection c=DBConnection.getConnection();
            PreparedStatement p=c.prepareStatement("SELECT * FROM questions WHERE id=?")) {
            p.setInt(1,id); ResultSet r=p.executeQuery();
            if(r.next()) return map(r);
        } catch(Exception e){e.printStackTrace();}
        return null;
    }

    private Question map(ResultSet r)throws Exception {
        return new Question(r.getInt("id"),r.getString("question_text"),
            r.getString("option_a"),r.getString("option_b"),
            r.getString("option_c"),r.getString("option_d"),
            r.getString("correct_option"));
    }

    public boolean add(String q,String a,String b,String c,String d,String correct) {
        String sql="INSERT INTO questions(question_text,option_a,option_b,option_c,option_d,correct_option) VALUES(?,?,?,?,?,?)";
        try(Connection con=DBConnection.getConnection(); PreparedStatement p=con.prepareStatement(sql)){
            p.setString(1,q);p.setString(2,a);p.setString(3,b);p.setString(4,c);p.setString(5,d);p.setString(6,correct);
            return p.executeUpdate()==1;
        }catch(Exception e){return false;}
    }

    public boolean update(int id,String q,String a,String b,String c,String d,String correct) {
        String sql="UPDATE questions SET question_text=?,option_a=?,option_b=?,option_c=?,option_d=?,correct_option=? WHERE id=?";
        try(Connection con=DBConnection.getConnection(); PreparedStatement p=con.prepareStatement(sql)){
            p.setString(1,q);p.setString(2,a);p.setString(3,b);p.setString(4,c);p.setString(5,d);p.setString(6,correct);p.setInt(7,id);
            return p.executeUpdate()==1;
        }catch(Exception e){return false;}
    }

    public boolean delete(int id) {
        try(Connection con=DBConnection.getConnection(); PreparedStatement p=con.prepareStatement("DELETE FROM questions WHERE id=?")){
            p.setInt(1,id); return p.executeUpdate()==1;
        }catch(Exception e){return false;}
    }
}
