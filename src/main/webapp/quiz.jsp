<%@ page import="java.util.*,com.quiz.model.Question" %>
<% List<Question> questions=(List<Question>)session.getAttribute("quizQuestions"); if(questions==null){response.sendRedirect("quiz");return;} %>
<!DOCTYPE html><html><head><title>Take Quiz</title><link rel="stylesheet" href="css/style.css"></head>
<body><div class="nav"><div class="brand">Java Quiz</div><a href="dashboard.jsp">Dashboard</a></div>
<div class="container"><div id="timer" class="timer">Time remaining: 5:00</div>
<form id="quizForm" action="quiz" method="post">
<% int n=1; for(Question q:questions){ %><div class="card question"><h2><%=n++%>. <%=q.getQuestionText()%></h2>
<label class="option"><input type="radio" name="q_<%=q.getId()%>" value="A"> A. <%=q.getOptionA()%></label>
<label class="option"><input type="radio" name="q_<%=q.getId()%>" value="B"> B. <%=q.getOptionB()%></label>
<label class="option"><input type="radio" name="q_<%=q.getId()%>" value="C"> C. <%=q.getOptionC()%></label>
<label class="option"><input type="radio" name="q_<%=q.getId()%>" value="D"> D. <%=q.getOptionD()%></label>
</div><% } %>
<button class="btn" type="submit">Submit Quiz</button></form></div><script src="js/quiz.js"></script></body></html>
