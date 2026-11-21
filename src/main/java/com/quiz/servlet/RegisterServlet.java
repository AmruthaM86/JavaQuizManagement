package com.quiz.servlet;

import com.quiz.dao.UserDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException {
        boolean ok=new UserDAO().register(req.getParameter("name"),req.getParameter("email"),req.getParameter("password"));
        if(ok) res.sendRedirect("login.jsp?registered=true");
        else {req.setAttribute("error","Registration failed. Email may already exist.");req.getRequestDispatcher("register.jsp").forward(req,res);}
    }
}
