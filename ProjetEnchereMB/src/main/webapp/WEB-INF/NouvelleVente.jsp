<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.bo.Utilisateur" %>
<%@ page import="fr.eni.enchere.bo.Categorie" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="fr" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="CSS/login.css">
</head>
<body>
<% Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur"); %>
<% List<Categorie> categories = (List<Categorie>)request.getAttribute("categories"); %>
<%@ include file="head.jsp" %>

	<div class="nouvelleVente">
	<h1>Nouvelle vente</h1>
	<form method="post" action="AjouterArticleVendu">
		<label for="nom">Article :</label>
		<input name ="nom" type="text"><br/>
			
		<label for="description">Description :</label>
		<textarea name="description" rows=3 cols=30 style="resize:none;vertical-align:top;"></textarea>
			
		<label for="categorie">Catégorie :</label>
		<select id="categorie" name="categorie">
        <% 
        if (categories != null) {
        	for (Categorie categorie : categories){ %>
 		<option value="<%=categorie.getNoCategorie()%>"><%=categorie.getLibelle()%></option>
        <% } 
        }%>
    	</select>
			
		<label for="prix">Mise à prix :</label>
		<input name="prix" type="text">
			
		<label for="debutenchere"> Début de l'enchère :</label>
		<input name="debutenchere" type="date">
			
		<label for="finenchere">Fin de l'enchère :</label>
		<input name="finenchere" type="date">
	<% if(utilisateur != null) { %>	
		<fieldset>
  			<legend>Retrait</legend>
			<label for="rue">Rue :</label>
			<input name="rue" type="text" value="<%= utilisateur.getAdresse().getRue() %>">
			<label for="codepostal">Code postal :</label>
			<input name="codepostal" type="text" value="<%= utilisateur.getAdresse().getCodePostal() %>">
			<label for="ville">Ville :</label>
			<input name="ville" type="text" value="<%= utilisateur.getAdresse().getVille() %>">
		</fieldset>
	<% } %>
	<%-- POUR LE TEST POUR GERER L'ABSENCE DE SESSION --%>
	<% if(utilisateur == null) { %>	
		<div>
  			<p>Adresse de retrait</p>
			<label for="rue">Rue :</label>
			<input name="rue" type="text">
			<label for="codepostal">Code postal :</label>
			<input name="codepostal" type="text">
			<label for="ville">Ville :</label>
			<input name="ville" type="text">
		</div>
	<% } %>
		<input type="submit" name="valider" value="Enregistrer">
		<input type="submit" name="annuler" value="Annuler">
		
	</form>
	</div>
</body>
</html>