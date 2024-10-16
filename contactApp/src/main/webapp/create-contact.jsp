<%--
  Created by IntelliJ IDEA.
  User: onurd
  Date: 7/31/2024
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Contact</title>
</head>
<body>
<h1>Create Contact</h1>
<form action="CreateContactServlet" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="tel_number">Phone Number:</label>
    <input type="text" id="tel_number" name="tel_number" required><br>
    <input type="submit" value="Create Contact">
</form>
</body>
</html>
