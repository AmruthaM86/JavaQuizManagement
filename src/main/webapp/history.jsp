<%@ page import="java.util.*" %>
<!DOCTYPE html><html><head><title>Quiz History</title><link rel="stylesheet" href="css/style.css"></head>
<body><div class="nav"><div class="brand">Quiz History</div><div><a href="dashboard.jsp">Dashboard</a><a href="logout">Logout</a></div></div>
<div class="container"><div class="card"><h1>Previous Attempts</h1>
<% List<Map<String,Object>> history=(List<Map<String,Object>>)request.getAttribute("history"); if(history==null || history.isEmpty()){ %>
<p>No quiz attempts yet.</p>
<% } else { %><table><tr><th>Attempt</th><th>Score</th><th>Total</th><th>Percentage</th><th>Date</th></tr>
<% int i=1; for(Map<String,Object> m:history){ %><tr><td><%=i++%></td><td><%=m.get("score")%></td><td><%=m.get("total")%></td><td><%=m.get("percentage")%>%</td><td><%=m.get("date")%></td></tr><% } %></table><% } %>
</div></div></body></html>
