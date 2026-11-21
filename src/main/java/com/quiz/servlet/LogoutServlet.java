package com.quiz.servlet;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
        req.getSession().invalidate();res.sendRedirect("login.jsp");
    }
}
