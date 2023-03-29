<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.bll.ArticleVenduManager,
fr.eni.enchere.bll.CategorieManager,
fr.eni.enchere.bo.ArticleVendu,
java.util.List,
java.util.ArrayList,
java.time.LocalDate,
fr.eni.enchere.bo.Categorie,
fr.eni.enchere.bo.Utilisateur,
java.util.Iterator,
fr.eni.enchere.bo.Enchere,
fr.eni.enchere.bll.EnchereManager" %>
<!DOCTYPE html>
<html>
<head>
<title>Accueil</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<% 
	List<Categorie> categories = CategorieManager.getInstance().selectAllCategories();
	List<ArticleVendu> articles = ArticleVenduManager.getInstance().getAllArticleVendu();
	List<ArticleVendu> articlesR = (List<ArticleVendu>) request.getAttribute("articles");
	List<Enchere> encheres = EnchereManager.getInstance().selectAllEncheres();
	String search = request.getParameter("recherche");
	Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
%>
	
<%@ include file="head.jsp" %>


<h1>Liste des enchères</h1>
<form action="Recherche" method="get" class="searchBar">
	<label for="recherche">Nom de l'article recherché : </label>
	<% if (search != null && !search.contains("/")) { %>
			<input type="search" name="recherche" value="<%= search %>" />
	<% 	} else { %>
	<input type="search" name="recherche" value=""/>
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

<% if (utilisateur != null) { %>

	<% List<ArticleVendu> articlesU = new ArrayList<>();
	articlesU.addAll(articles); 
	Iterator<ArticleVendu> iterator = articlesU.iterator();
	  while (iterator.hasNext()) {
	        ArticleVendu article = iterator.next();
	        if (!article.getUtilisateur().getPseudo().equals(utilisateur.getPseudo())) {
	            iterator.remove();
	        }
	    } 
	    if (!articlesU.isEmpty()) { %>
	    <div class ="listeEncheres">
	    <h3>Mes ventes</h3>
	    <% for(ArticleVendu article : articlesU){ %>
		<div class="article">
			<a class="titreArticle" href="AfficherArticleVendu?id_article=<%= article.getNoArticle() %>"><%= article.getNomArticle() %></a>
			<p>Prix actuel : <%= article.getPrixVente() %></p>
			<p>Fin de l'enchere le : <%= article.getDateFinEncheres() %></p>
		</div>
	</div>
	<% 		}
	    } %>

	<%
	List<Enchere> encheresE = new ArrayList<>();
	encheresE.addAll(encheres);
	List<ArticleVendu> articlesE = new ArrayList<>();
	Iterator<Enchere> iterator2 = encheresE.iterator();
	  while (iterator2.hasNext()) {
	        Enchere enchere = iterator2.next();
	        if (enchere.getUtilisateur().equals(utilisateur)) {
	        	if (enchere.getArticleVendu().getDateFinEncheres().isAfter(LocalDate.now()))
	            	articlesE.add(enchere.getArticleVendu());
	        }
	    } 
	    if (!articlesE.isEmpty()) { %>
	 <div class ="listeEncheres">
	    <h3>Mes achats</h3>
	    <% for(ArticleVendu article : articlesE){ %>
		<div class="article">
			<a class="titreArticle" href="AfficherArticleVendu?id_article=<%= article.getNoArticle() %>"><%= article.getNomArticle() %></a>
			<p>Prix actuel : <%= article.getPrixVente() %></p>
			<p>Fin de l'enchere le : <%= article.getDateFinEncheres() %></p>
			<p>Vendeur : <a href="AfficherUtilisateur?pseudo=<%= article.getUtilisateur().getPseudo() %>"><%= article.getUtilisateur().getPseudo() %></a></p>
		</div>
	</div>
	<% 		}
	    }
%>	    



	<%  
	List<Enchere> encheresA = new ArrayList<>();
	encheresE.addAll(encheres);
	List<ArticleVendu> articlesA = new ArrayList<>();
	Iterator<Enchere> iterator3 = encheresA.iterator();
	  while (iterator3.hasNext()) {
	        Enchere enchere = iterator3.next();
	        if (enchere.getUtilisateur().equals(utilisateur)) {
	        	if (enchere.getArticleVendu().getDateFinEncheres().isBefore(LocalDate.now()) && enchere.getArticleVendu().getPrixVente() == enchere.getMontantEnchere())
	            	articlesA.add(enchere.getArticleVendu());
	        }
	    } 
	    if (!articlesA.isEmpty()) { %>
	    <div class ="listeEncheres">
	    <h3>Mes achats</h3>
	    <% for(ArticleVendu article : articlesA){ %>
		<div class="article">
			<a class="titreArticle" href="AfficherArticleVendu?id_article=<%= article.getNoArticle() %>"><%= article.getNomArticle() %></a>
			<p>Prix actuel : <%= article.getPrixVente() %></p>
			<p>Fin de l'enchere le : <%= article.getDateFinEncheres() %></p>
			<p>Vendeur : <a href="AfficherUtilisateur?pseudo=<%= article.getUtilisateur().getPseudo() %>"><%= article.getUtilisateur().getPseudo() %></a></p>
		</div>
		</div>
		<% } %>
	<% }  %>	
<% } %>

<div class ="listeEncheres">
<h3>A vendre</h3>
	<%	
	List<ArticleVendu> articlesRB = new ArrayList<ArticleVendu>();
	if (articlesR == null) {
		articlesRB.addAll(articles);
	}
	if (articlesR != null) {
		articlesRB.addAll(articlesR);
	}
	if(articlesRB !=null){
	for(ArticleVendu article : articlesRB){ %>
		<div class="article">
			<%if(utilisateur != null) { %>
			<a class="titreArticle" href="AfficherArticleVendu?id_article=<%= article.getNoArticle() %>"><%= article.getNomArticle() %></a>
			<% }else{ %>
			<h3><%= article.getNomArticle() %></h3>
			<% } %>
			<p>Prix actuel : <%= article.getPrixVente() %></p>
			<p>Fin de l'enchere le : <%= article.getDateFinEncheres() %></p>
			<p>Vendeur : <a href="AfficherUtilisateur?pseudo=<%= article.getUtilisateur().getPseudo() %>"><%= article.getUtilisateur().getPseudo() %></a></p>
		</div>
	<% } %>
	<% }else{ %>
			<div><p>Aucun article en vente :/</p></div>
	<% } %>
</div>
</body>
</html>