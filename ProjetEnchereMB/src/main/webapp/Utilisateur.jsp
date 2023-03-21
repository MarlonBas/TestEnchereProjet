<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="fr.eni.enchere.bo.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un compte</title>
</head>
<body>
<form method="POST" action="AjouterUtilisateur">
Pseudo:<input type="texte" name="Pseudo"><br>
Nom:<input type="text" name="Nom"><br/>
Prenom:<input type="text" name="Prenom"><br/>
Telephone:<input type="texte" name="Telephone">
Email:<input type="email" name="Email"><br/>
Rue:<input type="text" name="Rue"><br/>
Code postal: <input type="number" name="CodePostal"><br>
Ville:<input type="text" name="Ville"><br/>
Mot de passe: <input type="password" name="MotDePasse"><br>
Confirmation mot de passe:<input type ="password" name="Confirmation mot de passe"><br>

<input type="submit" name="action" value="Créer">
<input type="submit" name="action" value="Annuler">
</form>
</html>