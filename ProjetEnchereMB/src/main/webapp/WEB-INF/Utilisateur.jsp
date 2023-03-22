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
<form method="POST" action="ajouterutilisateur">

<label for="pseudo">Pseudo :</label>
<input type="text" name="pseudo" required><br/>

<label for="nom">Nom :</label>
<input type="text" name="nom" required><br/>

<label for="prenom">Prenom :</label>
<input type="text" name="prenom" required><br/>

<label for="telephone">Telephone:</label>
<input type="text" placeholder="Ex : 0123456789" name="telephone"><br/>

<label for="email">Email:</label>
<input type="email" placeholder="Exemple@exemple.com" name="email" required><br/>

<label for="rue">Rue:</label>
<input type="text" name="rue" required><br/>

<label for="code_postal">Code postal:</label>
<input type="text" inputmode="numeric" pattern="[0-9]{5}" placeholder="Ex : 75000" name="code_postal" required><br/>

<label for="ville">Ville:</label>
<input type="text" name="ville" required><br/>

<label for="mot_de_passe">Mot de passe:</label>
<input type="password"  placeholder ="********" name="mot_de_passe" required><br/>

<label for="confirmation_mot_de_passe">Confirmation mot de passe:</label>
<input type ="password" placeholder ="********" name="confirmation_mot_de_passe" required><br/>


<input type="submit" name="valider" value="Créer">
<input type="submit" name="annuler" value="Annuler">
</form>
</html>