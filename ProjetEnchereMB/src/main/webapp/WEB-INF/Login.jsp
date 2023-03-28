<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="fr" data-bs-theme="dark">
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="CSS/login.css">
    
    <title>Se connecter</title>
</head>
<body>
<a href="encheres" id="logoAccueil">ENI Encheres</a>
<div class="boiteLogin">
<h1>Connexion</h1>
	<div class="formulaire">
	<%-- Afficher une erreur si on retourne sur la page après une de connection --%>
    <% if (request.getAttribute("erreur") != null) { %>
        <p class="erreur"><%= request.getAttribute("erreur") %></p>
    <% } %>
    <form action="ServletLogin" method="post">
		<label for="identifiant">Identifiant (pseudo ou email)</label><br>
		<%-- Récupère un cookie DernierId qui contient le dernier identifiant qui a marché --%>
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
        <input type="password" name="password" placeholder="**********" required><br>
        <br>
        <input type="submit" value="Se connecter" class="button">
        <a class="button" href="ajouterutilisateur">Créer un compte</a>
        <br>
        <a href="encheres">Mot de passe oublié ?</a>
    </form> 
    
   
	
	</div>
	</div>
</body>
</html>
