<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Quiz Result</title><link rel="stylesheet" href="css/style.css"></head>
<body><div class="nav"><div class="brand">Quiz Result</div><a href="dashboard.jsp">Dashboard</a></div>
<div class="container center"><div class="card"><h1>Quiz Completed!</h1>
<p class="stat"><%=request.getAttribute("score")%> / <%=request.getAttribute("total")%></p>
<h2>Score: <%=request.getAttribute("score")%></h2>
<h2>Percentage: <%=String.format("%.2f",((Number)request.getAttribute("percentage")).doubleValue())%>%</h2>
<a class="btn" href="quiz">Take Again</a> <a class="btn secondary" href="history">View History</a>
</div></div></body></html>
