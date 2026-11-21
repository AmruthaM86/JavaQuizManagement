<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Login</title><link rel="stylesheet" href="css/style.css"></head>
<body><div class="container" style="max-width:500px"><div class="card">
<h1>Quiz Management System</h1><h2>Login</h2>
<% if(request.getAttribute("error")!=null){ %><div class="error"><%=request.getAttribute("error")%></div><% } %>
<% if("true".equals(request.getParameter("registered"))){ %><div class="success">Registration successful. Please login.</div><% } %>
<form action="login" method="post">
<label>Email</label><input class="input" type="email" name="email" required>
<label>Password</label><input class="input" type="password" name="password" required>
<button class="btn" type="submit">Login</button>
</form><p>New user? <a href="register.jsp">Create account</a></p>
</div></div></body></html>
