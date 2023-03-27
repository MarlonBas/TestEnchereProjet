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
		
		<form action="ModifierUtilisateur" method="POST">
			<label for="pseudo">Pseudo :</label>
			<input name ="pseudo" type="text" value="<%= utilisateur.getPseudo() %>" readonly><br/>
			
			<label for="nom">Nom :</label>
			<input name="nom" type="text" value="<%= utilisateur.getNom() %>"><br/>
			
			<label for="prenom">Prénom :</label>
			<input name="prenom" type="text" value="<%= utilisateur.getPrenom() %>"><br/>
			
			<label for="email">Email :</label>
			<input name="email" type="email" value="<%= utilisateur.getEmail() %>"><br/>
			
			<label for="telephone">Téléphone :</label>
			<input name="telephone" type="text" value="<%= utilisateur.getTelephone() %>"><br/>
			
			<label for="rue"> Rue :</label>
			<input name="rue" type="text" value="<%= utilisateur.getAdresse().getRue() %>"><br/>
			
			<label for="code_postal">Code Postal :</label>
			<input name="code_postal" type="text" value="<%= utilisateur.getAdresse().getCodePostal() %>"><br/>
			
			<label for="ville">Ville :</label>
			<input name="ville" type="text" value="<%= utilisateur.getAdresse().getVille() %>"><br/>
			<br/>
			
			<input type="submit" name="valider" value="Modifier">
		</form>
		<br/>
		<span>Je veux supprimer mon compte pour toujours <br/>-- ATTENTION --<br/>
		Ceci est irreversible et entrainera la perte de tout vos credits</span>
		<form action="SupprimerUtilisateur" method="POST">
			<input type="submit" name="valider" value="Supprimer">
		</form>
</body>
</html>