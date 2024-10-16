<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        .google-link {
            font-weight: bold;
            color: blue;
        }
    </style>
</head>


<body>
<%--<%@ include file="header.jsp" %>--%>

<%--<h1>Hello World!</h1>--%>
<%--<h1>First JSP Example</h1>--%>
<%--<%--%>
<%--    String message = "This is from Java code block!";--%>
<%--    Date date = new Date();--%>
<%--&lt;%&ndash;%>&ndash;%&gt;--%>
<%--<%--%>
<%--    String name = request.getParameter("name");--%>

<%--    if (name != null && !name.isEmpty()) {--%>
<%--        out.println("<h2>Hello, " + name + "!</h2>");--%>
<%--    } else {--%>
<%--        out.println("<h2>Please enter your name </h2>");--%>
<%--    }--%>
<%--%>--%>
<p class="google-link">
    <a href="https://www.google.com" style="color: blue;">Go to Google</a>
</p>

<%--<p><%= message %></p>--%>
<%--<p>Current date and time: <%= date.toString() %></p>--%>

<br/>
<form action="hello-servlet" method="get">
    <label for="destination">Enter your destination:</label>
    <input type="text" id="destination" name="destination"/>
    <input type="submit" value="Submit"/>
</form>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>
