<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.enchere.bo.Utilisateur" %>
<!DOCTYPE html>
<html lang="fr" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>Mon compte</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="CSS/login.css">
<% Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur"); %>
</head>
<body>
	<a href="encheres" id="logoAccueil">ENI Encheres</a>
		
		<div class="modifCompte">
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
			
			<input type="submit" name="valider" class="button" value="Modifier">
		</form>
		<br/>
		<p>Je veux supprimer mon compte pour toujours</p>
		<p>-- ATTENTION --</p>
		<p>Ceci est irreversible et entrainera la perte de tout vos credits</p>
		<form action="SupprimerUtilisateur" method="POST">
			<input type="submit" name="valider" class="button danger" value="Supprimer">
		</form>
		</div>
</body>
</html>