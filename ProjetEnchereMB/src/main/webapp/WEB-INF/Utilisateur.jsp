<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.enchere.bo.Utilisateur" %>
<!DOCTYPE html>
<html lang="fr" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<% Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur"); %>
<title>Profil de <%= utilisateur.getPseudo() %> </title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="CSS/style.css">

</head>
<body>
	<%@ include file="head.jsp" %>
	<div class="profilUtilisateur">
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
	</div>
</body>
</html>