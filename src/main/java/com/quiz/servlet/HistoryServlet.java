package com.quiz.servlet;
import com.quiz.dao.ResultDAO;
import com.quiz.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        User u=(User)req.getSession().getAttribute("user");
        if(u==null){res.sendRedirect("login.jsp");return;}
        req.setAttribute("history",new ResultDAO().history(u.getId()));
        req.getRequestDispatcher("history.jsp").forward(req,res);
    }
}
