<%--
  Created by IntelliJ IDEA.
  User: onurd
  Date: 8/1/2024
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required><br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br>
  <input type="submit" value="Login">
</form>

<% String errorMessage = request.getParameter("error"); %>
<% if (errorMessage != null) { %>
<p style="color:red;"><%= errorMessage %></p>
<% } %>
</body>
</html>
