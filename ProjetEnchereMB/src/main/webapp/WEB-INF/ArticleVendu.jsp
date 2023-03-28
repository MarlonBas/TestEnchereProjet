<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.enchere.bo.ArticleVendu,java.time.format.DateTimeFormatter,java.time.format.FormatStyle" %>
<!DOCTYPE html>
<html lang="fr" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<title>${article.nomArticle}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="CSS/login.css">
</head>
<body>
<%@ include file="head.jsp" %>
	
	<div class="detailVente">
		<div>
		<h1>Détail vente</h1>
		<p class="nomArticle">${article.nomArticle}</p>
		<p>Description de l'article : ${article.description}</p>
		<p>Catégorie : ${article.categorie.libelle}</p>
		<p>Meilleur offre : ${enchere.montantEnchere} par ${enchere.Utilisateur.pseudo}</p>
		<p>Mise à prix: ${article.miseAPrix}</p>
		<p>Fin de l'enchère : ${article.dateFinEncheres.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) }</p>
		<p>Retrait : ${article.adresse.rue} ${article.adresse.codePostal} ${article.adresse.ville}</p>
		<p>Vendeur : ${article.utilisateur.pseudo}</p>
		</div>
		<div class="photoArticle">
			<img src="" alt="Photo indisponible">
		</div>
	</div>
	
</body>
</html>