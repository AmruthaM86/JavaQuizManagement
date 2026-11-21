package com.quiz.servlet;

import com.quiz.dao.QuestionDAO;
import com.quiz.dao.ResultDAO;
import com.quiz.model.Question;
import com.quiz.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException {
        User u=(User)req.getSession().getAttribute("user");
        if(u==null){res.sendRedirect("login.jsp");return;}
        req.getSession().setAttribute("quizQuestions",new QuestionDAO().getAll());
        req.getRequestDispatcher("quiz.jsp").forward(req,res);
    }

    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException {
        User u=(User)req.getSession().getAttribute("user");
        if(u==null){res.sendRedirect("login.jsp");return;}
        List<Question> qs=(List<Question>)req.getSession().getAttribute("quizQuestions");
        if(qs==null){res.sendRedirect("quiz");return;}

        Map<Integer,String> answers=new HashMap<>();
        int score=0;
        for(Question q:qs){
            String a=req.getParameter("q_"+q.getId());
            answers.put(q.getId(),a);
            if(a!=null && a.equalsIgnoreCase(q.getCorrectOption())) score++;
        }
        int resultId=new ResultDAO().saveResult(u.getId(),score,qs.size(),answers,qs);
        req.getSession().removeAttribute("quizQuestions");
        req.setAttribute("score",score);req.setAttribute("total",qs.size());
        req.setAttribute("percentage",qs.isEmpty()?0:(score*100.0/qs.size()));
        req.setAttribute("resultId",resultId);
        req.getRequestDispatcher("result.jsp").forward(req,res);
    }
}
