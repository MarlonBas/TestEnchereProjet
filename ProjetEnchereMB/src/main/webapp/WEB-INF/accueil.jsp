<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.bll.ArticleVenduManager,fr.eni.enchere.bll.CategorieManager,fr.eni.enchere.bo.ArticleVendu,java.util.List,fr.eni.enchere.bo.Categorie" %>
<!DOCTYPE html>
<html>
<head>
<title>Accueil</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<% List<Categorie> categories = CategorieManager.getInstance().selectAllCategories(); 
	String search = request.getParameter("recherche");%>
<%@ include file="head.jsp" %>
<h1>Liste des enchères</h1>
<form action="Recherche" method="get" class="searchBar">
	<label for="recherche">Nom de l'article recherché : </label>
	<% if (search != null) { %>
	<input type="search" name="recherche" value=<%= search %> />
	<% } %>
	<% if (search == null) { %>
	<input type="search" name="recherche"/>
	<% } %>
	<label for="categorie">Catégorie :</label>
	<select id="categorie" name="categorie">
		<option value="0">Toutes categories</option>
	<% 
        if (categories != null) {
        	for (Categorie categorie : categories){ %>
 		<option value="<%=categorie.getNoCategorie()%>"><%=categorie.getLibelle()%></option>
        <% } 
        }%>
   </select>
   <input type="submit" value="Recherche" class="button"/>
</form>
<div class ="listeEncheres">
	<% 
	if (request.getAttribute("articles") == null) {
	List<ArticleVendu> lstArticles = ArticleVenduManager.getInstance().getAllArticleVendu();
	for(ArticleVendu article : lstArticles){ %>
		<div class="article">
			<h2><%= article.getNomArticle()%></h2>
			<p>Prix actuel : <%= article.getPrixVente() %></p>
			<p>Fin de l'enchere le : <%= article.getDateFinEncheres() %></p>
			<p>Vendeur : <a href="AfficherUtilisateur?pseudo=<%= article.getUtilisateur().getPseudo() %>"><%= article.getUtilisateur().getPseudo() %></a></p>
			
		</div>
	<% }
	} 
	else {
		List<ArticleVendu> lstArticles = (List<ArticleVendu>) request.getAttribute("articles");
		for(ArticleVendu article : lstArticles){ %>
		<div class="article">
			<h2><%= article.getNomArticle()%></h2>
			<p>Prix actuel : <%= article.getPrixVente() %></p>
			<p>Fin de l'enchere le : <%= article.getDateFinEncheres() %></p>
			<p>Vendeur : <a href="AfficherUtilisateur?pseudo=<%= article.getUtilisateur().getPseudo() %>"><%= article.getUtilisateur().getPseudo() %></a></p>
			
		</div>
	<% }
	}%>

</div>
</body>
</html>