<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.enchere.bll.ArticleVenduManager,fr.eni.enchere.bll.CategorieManager,fr.eni.enchere.bo.ArticleVendu,java.util.List,fr.eni.enchere.bo.Categorie,fr.eni.enchere.bo.Utilisateur,java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<title>Accueil</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<% List<Categorie> categories = CategorieManager.getInstance().selectAllCategories(); 
	String search = request.getParameter("recherche");
	Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur"); %>
<%@ include file="head.jsp" %>
<h1>Liste des enchères</h1>
<form action="Recherche" method="get" class="searchBar">
	<label for="recherche">Nom de l'article recherché : </label>
	<% if (search != null && !search.contains("/")) { %>
			<input type="search" name="recherche" value=<%= search %> />
	<% 	} else { %>
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

<% if (utilisateur != null) { %>
<div class ="listeEncheres">
	<% List<ArticleVendu> lstArticlesUtilisateur = ArticleVenduManager.getInstance().getAllArticleVendu();
	Iterator<ArticleVendu> iterator = lstArticlesUtilisateur.iterator();
	  while (iterator.hasNext()) {
	        ArticleVendu article = iterator.next();
	        if (!article.getUtilisateur().getPseudo().equals(utilisateur.getPseudo())) {
	            iterator.remove();
	        }
	    } 
	    if (!lstArticlesUtilisateur.isEmpty()) { %>
	    <h3>Mes ventes</h3><br/>
	    <% for(ArticleVendu article : lstArticlesUtilisateur){ %>
		<div class="article">
			<h2><%= article.getNomArticle() %></h2>
			<p>Prix actuel : <%= article.getPrixVente() %></p>
			<p>Fin de l'enchere le : <%= article.getDateFinEncheres() %></p>
		</div>
	<% 		}
	    } %>
</div>
<br/>
<div class ="listeEncheres">
	<%
	// Ici il va falloir chercher la liste des encheres d'un utilisateur puis créer une liste de tout
	// les articles qui figure dans cette liste d'enchères
	
	//List<Enchere> encheres = EnchereManager.
	List<ArticleVendu> lstArticlesEnchere = ArticleVenduManager.getInstance().getAllArticleVendu();
	Iterator<ArticleVendu> iterator2 = lstArticlesEnchere.iterator();
	  while (iterator2.hasNext()) {
	        ArticleVendu article = iterator2.next();
	        if (1 == 1) {
	            iterator2.remove();
	        }
	    } 
	    if (!lstArticlesEnchere.isEmpty()) { %>
	    <h3>Mes achats</h3><br/>
	    <% for(ArticleVendu article : lstArticlesEnchere){ %>
		<div class="article">
			<h2><%= article.getNomArticle() %></h2>
			<p>Prix actuel : <%= article.getPrixVente() %></p>
			<p>Fin de l'enchere le : <%= article.getDateFinEncheres() %></p>
			<p>Vendeur : <a href="AfficherUtilisateur?pseudo=<%= article.getUtilisateur().getPseudo() %>"><%= article.getUtilisateur().getPseudo() %></a></p>
		</div>
	<% 		}
	    }
%>	    
</div>
<br/>
<div class ="listeEncheres">
	<%  
	// Ici il va falloir récupérer les encheres de l'utilisateur et les articles dont la vente est terminé
	// et pour chaque article dont la vente est terminé on compare le prix avec l'enchere de l'utilisateur
	// si c'est pareil alors l'utilisateur à remporté et c'est un achat à affiché
	List<ArticleVendu> lstArticlesAchats = ArticleVenduManager.getInstance().getAllArticleVendu();
	Iterator<ArticleVendu> iterator3 = lstArticlesAchats.iterator();
	  while (iterator3.hasNext()) {
	        ArticleVendu article = iterator3.next();
	        if (1 == 1) {
	            iterator3.remove();
	        }
	    } 
	    if (!lstArticlesAchats.isEmpty()) { %>
	    <h3>Mes achats</h3><br/>
	    <% for(ArticleVendu article : lstArticlesAchats){ %>
		<div class="article">
			<h2><%= article.getNomArticle() %></h2>
			<p>Prix actuel : <%= article.getPrixVente() %></p>
			<p>Fin de l'enchere le : <%= article.getDateFinEncheres() %></p>
			<p>Vendeur : <a href="AfficherUtilisateur?pseudo=<%= article.getUtilisateur().getPseudo() %>"><%= article.getUtilisateur().getPseudo() %></a></p>
		</div>
	<% 		}
	    }
%>
</div>
<br/>
<% } %>

<div class ="listeEncheres">
<h3>A vendre</h3><br/>
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