<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.enchere.bo.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon compte</title>
<% Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur"); %>
</head>
<body>
	<header>
		<h1>ENI - Encheres</h1>
	</header>
		
		<form>
			<label for="pseudo">Pseudo :</label>
			<input name ="pseudo" type="text" value="<%= utilisateur.getPseudo() %>" readonly><br/>
			
			<label for="nom">Nom :</label>
			<input name="nom" type="text" value="<%= utilisateur.getNom() %>" readonly><br/>
			
			<label for="prenom">Prénom :</label>
			<input name="prenom" type="text" value="<%= utilisateur.getPrenom() %>" readonly><br/>
			
			<label for="email">Email :</label>
			<input name="email" type="email" value="<%= utilisateur.getEmail() %>" readonly><br/>
			
			<label for="telephone">Téléphone :</label>
			<input name="telephone" type="text" value="<%= utilisateur.getTelephone() %>" readonly><br/>
			
			<label for="rue"> Rue :</label>
			<input name="rue" type="text" value="<%= utilisateur.getAdresse().getRue() %>" readonly><br/>
			
			<label for="cp">Code Postal :</label>
			<input name="cp" type="text" value="<%= utilisateur.getAdresse().getCodePostal() %>" readonly><br/>
			
			<label for="ville">Ville :</label>
			<input name="ville" type="text" value="<%= utilisateur.getAdresse().getVille() %>" readonly><br/>
	
		</form>
	
	
	
</body>
</html>