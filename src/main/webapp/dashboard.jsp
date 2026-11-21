<%@ page import="com.quiz.model.User" %>
<% User user=(User)session.getAttribute("user"); if(user==null){response.sendRedirect("login.jsp");return;} %>
<!DOCTYPE html><html><head><title>Dashboard</title><link rel="stylesheet" href="css/style.css"></head>
<body><div class="nav"><div class="brand">Quiz Management System</div><div><a href="dashboard.jsp">Dashboard</a><a href="history">History</a><a href="logout">Logout</a></div></div>
<div class="container"><div class="hero"><h1>Welcome, <%=user.getName()%>!</h1><p>Test your knowledge with interactive multiple-choice quizzes.</p><a class="btn" href="quiz">Start Quiz</a></div>
<div class="grid" style="margin-top:22px"><div class="card"><h2>Online Quiz</h2><p>Answer MCQ questions with a 5-minute timer.</p></div>
<div class="card"><h2>Instant Results</h2><p>Get your score and percentage immediately.</p></div>
<div class="card"><h2>Quiz History</h2><p>Review your previous quiz attempts.</p><a class="btn secondary" href="history">View History</a></div>
<% if(user.isAdmin()){ %><div class="card"><h2>Admin Panel</h2><p>Manage quiz questions.</p><a class="btn" href="admin/questions">Manage Questions</a></div><% } %>
</div></div></body></html>
