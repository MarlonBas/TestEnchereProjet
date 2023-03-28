<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="CSS/head.css">
</head>
<body>
<header>
<nav>

<a href="encheres" id="logoAccueil">ENI Encheres</a>
<div>
<% if(session.getAttribute("utilisateur")==null){ %>
<a href="ajouterutilisateur" class="button" >S'inscrire</a>
<a href="ServletLogin" class="button">Se connecter</a>
<% } else { %>
<span>Bienvenue  ${utilisateur.prenom} </span>
<a href="AjouterArticleVendu" class="button">Mettre en vente un article</a>
<a href="MonCompte" class="button">Voir mon profil</a>
<a href="Deconnection" class="button danger">Se déconnecter</a>

</div>
<% } %>
</nav>
</header>
</body>
</html>