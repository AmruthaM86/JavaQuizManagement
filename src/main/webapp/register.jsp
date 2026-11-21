<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Register</title><link rel="stylesheet" href="css/style.css"></head>
<body><div class="container" style="max-width:500px"><div class="card">
<h1>Create Account</h1>
<% if(request.getAttribute("error")!=null){ %><div class="error"><%=request.getAttribute("error")%></div><% } %>
<form action="register" method="post">
<label>Name</label><input class="input" name="name" required>
<label>Email</label><input class="input" type="email" name="email" required>
<label>Password</label><input class="input" type="password" name="password" minlength="4" required>
<button class="btn" type="submit">Register</button>
</form><p>Already registered? <a href="login.jsp">Login</a></p>
</div></div></body></html>
