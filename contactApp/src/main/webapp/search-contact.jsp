<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: onurd
  Date: 7/31/2024
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
  <title>Search Contact</title>
</head>
<body>
<form action="search-contact" method="post">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name">
  <input type="submit" value="Search">
</form>

<!-- Display search results if any -->
<%
  List<Map<String, String>> contacts = (List<Map<String, String>>) request.getAttribute("contacts");
  if (contacts != null && !contacts.isEmpty()) {
    for (Map<String, String> contact : contacts) {
      String id = contact.get("id");
      String name = contact.get("name");
      String tel_number = contact.get("tel_number");
%>
<div>
  <p>ID: <%= id %></p>
  <p>Name: <%= name %></p>
  <p>Telephone Number: <%= tel_number %></p>
  <form action="edit-contact.jsp" method="get">
    <input type="hidden" name="id" value="<%= id %>">
    <input type="hidden" name="name" value="<%= name %>">
    <input type="hidden" name="number" value="<%= tel_number %>">

    <input type="submit" value="Edit">
  </form>
</div>
<%
    }
  }
%>
</body>
</html>
