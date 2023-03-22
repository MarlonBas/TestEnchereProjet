<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Se connecter</h1>
    <form action="ServletLogin" method="post">
        <label>Email:</label>
        <input type="text" name="email" required>
        <br><br>
        <label>Password:</label>
        <input type="password" name="password" required>
        <br><br>
        <input type="submit" value="Login">
    </form>
    <% if (request.getAttribute("erreur") != null) { %>
        <p style="color: red;"><%= request.getAttribute("erreur") %></p>
    <% } %>
</body>
</html>