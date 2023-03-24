<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.enchere.bo.ArticleVendu" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Article Vendu</title>
</head>
<body>
	<h1>Article en vente</h1>
	${article.nomArticle}
	<br/>
	${article.description}
	<br/>
	${article.miseAPrix}
	<br/>
	${article.prixVente}
	<br/>
	${article.dateFinEncheres.toString()}
</body>
</html>