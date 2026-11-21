<%@ page import="java.util.*,com.quiz.model.Question" %>
<!DOCTYPE html><html><head><title>Manage Questions</title><link rel="stylesheet" href="../css/style.css"></head>
<body><div class="nav"><div class="brand">Admin - Questions</div><div><a href="../dashboard.jsp">Dashboard</a><a href="../logout">Logout</a></div></div>
<div class="container">
<div class="card"><h1><%=request.getAttribute("editQuestion")==null?"Add Question":"Edit Question"%></h1>
<% Question e=(Question)request.getAttribute("editQuestion"); %>
<form method="post" action="<%=request.getContextPath()%>/admin/questions">
<input type="hidden" name="action" value="<%=e==null?"add":"edit"%>"><% if(e!=null){%><input type="hidden" name="id" value="<%=e.getId()%>"><%}%>
<label>Question</label><textarea class="input" name="question" required><%=e==null?"":e.getQuestionText()%></textarea>
<label>Option A</label><input class="input" name="a" value="<%=e==null?"":e.getOptionA()%>" required>
<label>Option B</label><input class="input" name="b" value="<%=e==null?"":e.getOptionB()%>" required>
<label>Option C</label><input class="input" name="c" value="<%=e==null?"":e.getOptionC()%>" required>
<label>Option D</label><input class="input" name="d" value="<%=e==null?"":e.getOptionD()%>" required>
<label>Correct Option</label><select class="input" name="correct" required>
<% for(String x:new String[]{"A","B","C","D"}){ %><option value="<%=x%>" <%=e!=null&&x.equals(e.getCorrectOption())?"selected":""%>><%=x%></option><% } %></select>
<button class="btn" type="submit"><%=e==null?"Add Question":"Update Question"%></button>
<% if(e!=null){%><a class="btn secondary" href="questions">Cancel</a><%}%>
</form></div>
<div class="card"><h2>Existing Questions</h2><table><tr><th>ID</th><th>Question</th><th>Correct</th><th>Action</th></tr>
<% List<Question> qs=(List<Question>)request.getAttribute("questions"); for(Question q:qs){ %>
<tr><td><%=q.getId()%></td><td><%=q.getQuestionText()%></td><td><%=q.getCorrectOption()%></td><td>
<a class="btn" href="questions?action=edit&id=<%=q.getId()%>">Edit</a>
<a class="btn danger" href="questions?action=delete&id=<%=q.getId()%>" onclick="return confirm('Delete this question?')">Delete</a>
</td></tr><% } %></table></div></div></body></html>
