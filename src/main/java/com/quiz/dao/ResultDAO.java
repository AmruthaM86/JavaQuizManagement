package com.quiz.dao;

import com.quiz.model.Question;
import com.quiz.util.DBConnection;
import java.sql.*;
import java.util.*;

public class ResultDAO {
    public int saveResult(int userId,int score,int total,Map<Integer,String> answers,List<Question> questions) {
        double pct=total==0?0:(score*100.0/total);
        try(Connection c=DBConnection.getConnection()) {
            c.setAutoCommit(false);
            PreparedStatement p=c.prepareStatement(
                "INSERT INTO quiz_results(user_id,score,total_questions,percentage) VALUES(?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
            p.setInt(1,userId);p.setInt(2,score);p.setInt(3,total);p.setDouble(4,pct);p.executeUpdate();
            ResultSet keys=p.getGeneratedKeys(); keys.next(); int resultId=keys.getInt(1);

            PreparedStatement a=c.prepareStatement(
                "INSERT INTO quiz_answers(result_id,question_id,selected_option,is_correct) VALUES(?,?,?,?)");
            for(Question q:questions){
                String selected=answers.get(q.getId());
                a.setInt(1,resultId);a.setInt(2,q.getId());
                a.setString(3,selected);
                a.setBoolean(4,selected!=null && selected.equalsIgnoreCase(q.getCorrectOption()));
                a.addBatch();
            }
            a.executeBatch(); c.commit(); return resultId;
        } catch(Exception e){e.printStackTrace();return -1;}
    }

    public List<Map<String,Object>> history(int userId) {
        List<Map<String,Object>> list=new ArrayList<>();
        String sql="SELECT id,score,total_questions,percentage,taken_at FROM quiz_results WHERE user_id=? ORDER BY taken_at DESC";
        try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){
            p.setInt(1,userId);ResultSet r=p.executeQuery();
            while(r.next()){
                Map<String,Object> m=new HashMap<>();
                m.put("id",r.getInt("id"));m.put("score",r.getInt("score"));
                m.put("total",r.getInt("total_questions"));m.put("percentage",r.getDouble("percentage"));
                m.put("date",r.getTimestamp("taken_at"));list.add(m);
            }
        }catch(Exception e){e.printStackTrace();}
        return list;
    }
}
