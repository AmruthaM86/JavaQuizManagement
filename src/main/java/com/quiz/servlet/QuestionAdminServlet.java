package com.quiz.servlet;

import com.quiz.dao.QuestionDAO;
import com.quiz.model.Question;
import com.quiz.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/admin/questions")
public class QuestionAdminServlet extends HttpServlet {
    private QuestionDAO dao=new QuestionDAO();

    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        User u=(User)req.getSession().getAttribute("user");
        if(u==null || !u.isAdmin()){res.sendError(403);return;}
        String action=req.getParameter("action");
        if("delete".equals(action)){
            dao.delete(Integer.parseInt(req.getParameter("id")));
            res.sendRedirect(req.getContextPath()+"/admin/questions");return;
        }
        if("edit".equals(action)){
            req.setAttribute("editQuestion",dao.getById(Integer.parseInt(req.getParameter("id"))));
        }
        req.setAttribute("questions",dao.getAll());
        req.getRequestDispatcher("/admin/questions.jsp").forward(req,res);
    }

    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        User u=(User)req.getSession().getAttribute("user");
        if(u==null || !u.isAdmin()){res.sendError(403);return;}
        String action=req.getParameter("action");
        int id="edit".equals(action)?Integer.parseInt(req.getParameter("id")):0;
        String q=req.getParameter("question"),a=req.getParameter("a"),b=req.getParameter("b"),
               c=req.getParameter("c"),d=req.getParameter("d"),correct=req.getParameter("correct");
        if("edit".equals(action)) dao.update(id,q,a,b,c,d,correct); else dao.add(q,a,b,c,d,correct);
        res.sendRedirect(req.getContextPath()+"/admin/questions");
    }
}
