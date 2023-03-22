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
		<label for="identifiant">Identifiant (pseudo ou email)</label><br>
		<%
		    Cookie[] cookies = request.getCookies();
		    String identifiant = null;
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if (cookie.getName().equals("DernierId")) {
		                identifiant = cookie.getValue();
		                break;
		            }
		        }
		    }
			if (identifiant != null) { %>
         	<input type="text" name="identifiant" value="<%=identifiant%>" required>
	    <br><br>
	    <% } %>
	     <% if (identifiant == null) { %>
	        <input type="text" name="identifiant" required>
		    <br><br>
	    <% } %>
        <label for="password">Mot de passe</label><br>
        <input type="password" name="password" required>
        <br><br>
        <input type="submit" value="Login">
    </form>
    <% if (request.getAttribute("erreur") != null) { %>
        <p style="color: red;"><%= request.getAttribute("erreur") %></p>
    <% } %>
    <br>
    <br>
    <form action="AjoutUtilisateur" method="get">
  		<button type="submit">Créer un compte</button>
	</form>
</body>
</html>
