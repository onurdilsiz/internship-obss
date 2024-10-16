<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Secured Page</title>
</head>
<body>
<h1>Welcome to Secured Area, <%= session.getAttribute("username") %>!</h1>
</body>
</html>
