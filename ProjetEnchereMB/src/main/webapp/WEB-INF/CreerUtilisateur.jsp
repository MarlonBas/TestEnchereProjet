<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="fr.eni.enchere.bo.*" %>


<!DOCTYPE html>
<html  lang="fr" data-bs-theme="dark">
<head>
	<meta charset="UTF-8">
	<title>Créer un compte</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
   	<a href="encheres" id="logoAccueil">ENI Encheres</a>
	<div class="creationCompte">
	<h1>Inscription</h1>
	<form method="POST" action="ajouterutilisateur">
	
		<label for="pseudo">Pseudo :</label>
		<input type="text" name="pseudo" value="${utilisateurEnCreation.pseudo}" pattern="[a-zA-Z0-9]+$" required><br/>
		<p class="info">Le pseudo ne doit contenir que des caractères alphanumériques</p>
		
		<label for="nom">Nom :</label>
		<input type="text" name="nom" value="${utilisateurEnCreation.nom}" pattern="^[a-z]+[ \-']?[[a-z]+[ \-']?]*[a-z]+$" required><br/>
		
		<label for="prenom">Prenom :</label>
		<input type="text" name="prenom"  value="${utilisateurEnCreation.prenom}"required><br/>
		
		<label for="telephone">Telephone:</label>
		<input type="text" placeholder="Ex : 0123456789" value="${utilisateurEnCreation.telephone}" name="telephone"><br/>
		
		<label for="email">Email:</label>
		<input type="email" placeholder="Exemple@exemple.com" name="email" value="${utilisateurEnCreation.email}" required><br/>
		
		<label for="rue">Rue:</label>
		<input type="text" name="rue" value="${utilisateurEnCreation.adresse.rue}" required><br/>
		
		<label for="code_postal">Code postal:</label>
		<input type="text" inputmode="numeric" pattern="[0-9]{5}" placeholder="Ex : 75000" name="code_postal" value="${utilisateurEnCreation.adresse.codePostal}"required><br/>
		
		<label for="ville">Ville:</label>
		<input type="text" name="ville" value="${utilisateurEnCreation.adresse.ville}" required><br/>
		
		<label for="mot_de_passe">Mot de passe:</label>
		<input type="password"  placeholder ="********" name="mot_de_passe" required><br/>
		
		<label for="confirmation_mot_de_passe">Confirmation mot de passe:</label>
		<input type ="password" placeholder ="********" name="confirmation_mot_de_passe" required><br/>
		
		
		<input type="submit" name="valider" class="button" value="Créer">
		<a href="encheres" class="button danger">Annuler</a>
		<%-- Afficher une erreur si on retourne sur la page après une de connection --%>
		    <% if (request.getAttribute("erreur") != null) { %>
		        <p class="erreur"><%= request.getAttribute("erreur") %></p>
		    <% } %>
	</form>
	</div>
</body>
</html>