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
		<p>Description de l'article : ${article.description}</p>
		<p>Catégorie : ${article.categorie.libelle}</p>
		<% if (article.getEtatVente().equals("en cours")) { %>
		<p>Meilleur offre : ${article.prixVente} par <%=pseudo%></p>
		
		<% } %>
		<p>Mise à prix: ${article.miseAPrix}</p>
		<p>Fin de l'enchère : ${article.dateFinEncheres.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) }</p>
		<p>Retrait : ${article.adresse.rue} ${article.adresse.codePostal} ${article.adresse.ville}</p>
		<p>Vendeur : ${article.utilisateur.pseudo}</p>
		</div>
	<% if (!article.getUtilisateur().getPseudo().equals(utilisateur.getPseudo())
			&& article.getEtatVente().equals("en cours")) { %>
	<div>		
	<form action="AfficherArticleVendu" method="post">
		<input name="noArticle" type="hidden" value="${article.noArticle}">
		<label for="proposition">Proposition </label>
		<input name="proposition" type="number" step="10" min="${article.miseAPrix}" required>
		<input type="submit" value="Encherir !">
		<p class="info"><%=request.getAttribute("erreur")%></p>
	</form>
	</div>
	<% } %>
	</div>
	
</body>
</html>