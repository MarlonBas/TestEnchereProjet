<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.enchere.bo.ArticleVendu,java.time.format.DateTimeFormatter,java.time.format.FormatStyle,fr.eni.enchere.bo.*,fr.eni.enchere.bll.*,java.util.List,java.util.Iterator" %>
<!DOCTYPE html>
<html lang="fr" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>${article.nomArticle}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="CSS/style.css">
</head>
<body>
<%@ include file="head.jsp" %>
	<% ArticleVendu article = (ArticleVendu) request.getAttribute("article");
       Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");%>
       
       <%
       List<Enchere> encheres = EnchereManager.getInstance().selectEncheresByIdArticle(article.getNoArticle());
       String pseudo = null;
       if (!encheres.isEmpty())
       		pseudo = EnchereManager.getInstance().meilleureEnchere(encheres).getUtilisateur().getPseudo();
       if (pseudo == null) {
    	   pseudo = "aucune enchère";
       }
       %>
       
    <% if (article.getEtatVente().equals("termine")) {
    	if (pseudo.equals(utilisateur.getPseudo())) {%>
    <p>Félicitations vous avez remporter l'enchère ! =)</p>
	<% }
    }%>
	<div class="detailVente">
		<div>
		<h1>Détail vente</h1>
		<p class="nomArticle">${article.nomArticle}</p>
		<p>Description de l'article : <span class="articleInfo"> ${article.description}</span></p>
		<p>Catégorie : <span class="articleInfo">${article.categorie.libelle}</span></p>
		<% if (article.getEtatVente().equals("en cours")) { %>
		<p>Meilleur offre : <span class="articleInfo"> ${article.prixVente} par <%=pseudo%></span></p>
		
		<% } %>
		<p>Mise à prix: <span class="articleInfo"> ${article.miseAPrix}</span></p>
		<p>Fin de l'enchère : <span class="articleInfo"> ${article.dateFinEncheres.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) }</span></p>
		<p>Retrait : <span class="articleInfo"> ${article.adresse.rue} ${article.adresse.codePostal} ${article.adresse.ville}</span></p>
		<p>Vendeur : <a href="AfficherUtilisateur?pseudo=${article.getUtilisateur().getPseudo()}">${article.getUtilisateur().getPseudo()}</a></p>
		</div>
	<% if (!article.getUtilisateur().getPseudo().equals(utilisateur.getPseudo())
			&& article.getEtatVente().equals("en cours")) { %>
	<div>		
	<form action="AfficherArticleVendu" method="post">
		<input name="noArticle" type="hidden" value="${article.noArticle}">
		<label for="proposition">Proposition </label>
		<input name="proposition" type="number" min="${article.prixVente}" required>
		<input type="submit" value="Encherir !" class="button">
		<%if(request.getAttribute("erreur")!=null){ %>
		<p class="info"><%=request.getAttribute("erreur")%></p>
		<% } %>
	</form>
	</div>
	<% } %>
	</div>
	
</body>
</html>