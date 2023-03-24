<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.bo.Utilisateur" %>
<%@ page import="fr.eni.enchere.bo.Categorie" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
</head>
<body>
<% Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur"); %>
<% List<Categorie> categories = (List<Categorie>)request.getAttribute("categories"); %>
<h1>Nouvelle vente</h1>
	<form method="post" action="AjouterArticleVendu">
		<label for="nom">Article :</label>
		<input name ="nom" type="text"><br/>
			
		<label for="description">Description :</label><br/>
		<textarea name="description" type="text" rows=7 cols=30></textarea><br/>
			
		<label for="categorie">Catégorie :</label>
		<input name="categorie" type="text">
		 <select id="category" name="category">
        <% 
        if (categories != null) {
        	for (Categorie categorie : categories){ %>
            <option value="<%=categorie.getNoCategorie()%>"><%=categorie.getLibelle()%></option>
        <% } 
        }%>
    	</select><br/>
			
		<label for="image">Photo de l'article :</label>
		<input name="image" type="file"><br/>
			
		<label for="prix">Mise à prix :</label>
		<input name="prix" type="text"><br/>
			
		<label for="debutenchere"> Début de l'enchère :</label>
		<input name="debutenchere" type="date"><br/>
			
		<label for="finenchere">Fin de l'enchère :</label>
		<input name="finenchere" type="date"><br/>
	<% if(utilisateur != null) { %>	
		<fieldset>
  			<legend>Retrait</legend>
			<label for="rue">Rue :</label>
			<input name="rue" type="text" value="<%= utilisateur.getAdresse().getRue() %>"><br/>
			<label for="codepostal">Code postal :</label>
			<input name="codepostal" type="text" value="<%= utilisateur.getAdresse().getCodePostal() %>"><br/>
			<label for="ville">Ville :</label>
			<input name="ville" type="text" value="<%= utilisateur.getAdresse().getVille() %>"><br/>
		</fieldset>
	<% } %>
	<%-- POUR LE TEST POUR GERER L'ABSENCE DE SESSION --%>
	<% if(utilisateur == null) { %>	
		<fieldset>
  			<legend>Retrait</legend>
			<label for="rue">Rue :</label>
			<input name="rue" type="text"><br/>
			<label for="codepostal">Code postal :</label>
			<input name="codepostal" type="text"><br/>
			<label for="ville">Ville :</label>
			<input name="ville" type="text"><br/>
		</fieldset>
	<% } %>
		<br/>
		<br/>
		<input type="submit" name="valider" value="Enregistrer">
		<input type="submit" name="annuler" value="Annuler">
		
	</form>
</body>
</html>