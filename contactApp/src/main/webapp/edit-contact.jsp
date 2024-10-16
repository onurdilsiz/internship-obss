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
    <title>Edit Contact</title>
</head>
<body>
<h1>Edit Contact</h1>
<%
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String number = request.getParameter("number");


    // Fetch contact details based on ID and display them here
%>
<form action="edit-contact" method="post">
    <input type="hidden" id="id" name="id" value= "<%= id %>" >
    <label for="name">Name:</label>
    <input type="text" id="name" name="name"   value= "<%= name %>"><br>
    <label for="tel_number">Phone Number:</label>
    <input type="text" id="tel_number" name="tel_number" value= "<%= number %>"><br>
    <input type="submit" value="Edit Contact">
</form>
</body>
</html>
