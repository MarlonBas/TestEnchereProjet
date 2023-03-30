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
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<% Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur"); %>
<% List<Categorie> categories = (List<Categorie>)request.getAttribute("categories"); %>
<%@ include file="head.jsp" %>

	<div class="nouvelleVente">
	<h1>Nouvelle vente</h1>
	<form method="post" action="AjouterArticleVendu">
		<label for="nom">Article :</label>
		<input name ="nom" type="text" required>
			
		<label for="description">Description :</label>
		<textarea name="description" rows=2 cols=30 style="resize:none;vertical-align:top;"></textarea>
			
		<label for="categorie">Catégorie :</label>
		<select id="categorie" name="categorie">
        <% 
        if (categories != null) {
        	for (Categorie categorie : categories){ %>
 		<option value="<%=categorie.getNoCategorie()%>"><%=categorie.getLibelle()%></option>
        <% } 
        }%>
    	</select>
		<label for="prix">Mise à prix : (en Crédits)</label>
		<input name="prix" type="number" min="0" step="10" value="10" required >
		
		<label for="debutenchere" class="labelDate"> Début de l'enchère :</label>
		<input name="debutenchere" class="inputDate" type="date" required>
			
		<label for="finenchere" class="labelDate">Fin de l'enchère :</label>
		<input name="finenchere"  class="inputDate"type="date" required>

  			<p class="adresseRetrait">Adresse de retrait</p>
			<label for="rue">Rue :</label>
			<input name="rue" type="text" value="${utilisateur.adresse.rue }" required>
			<label for="codepostal">Code postal :</label>
			<input name="codepostal" type="text"  pattern="[0-9]{5}" value="${utilisateur.adresse.codePostal }" required>
			<label for="ville">Ville :</label>
			<input name="ville" type="text" value="${utilisateur.adresse.ville }" required>
	
		<%-- Afficher une erreur si on retourne sur la page après une de connection --%>
		    <% if (request.getAttribute("erreur") != null) { %>
		        <p class="erreur"><%= request.getAttribute("erreur") %></p>
		    <% } %>
	
		<input type="submit" name="valider" class="button" value="Enregistrer">
		<a href="encheres" class="button danger">Annuler</a>
		
	</form>
	</div>
</body>
</html>