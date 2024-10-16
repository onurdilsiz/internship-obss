<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="protected/hello-servlet">Hello Servlet</a>


<br/>
<form action="servlet" method="get">
    <label for="action">Enter your action:</label>
    <input type="text" id="action" name="action"/>

    <label for="amount">Enter amount:</label>
    <input type="text" id="amount" name="amount"/>
    <input type="submit" value="Submit"/>
</form>
<br/>
</body>
</html>