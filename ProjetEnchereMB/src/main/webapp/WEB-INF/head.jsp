<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr" data-bs-theme="dark">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<header>

<nav>
<h1>ENI Encheres</h1>
<% if(session.getAttribute("utilisateur")==null){ %>
<a href="ajouterutilisateur">S'inscrire</a>
<a href="ServletLogin">Se connecter</a>
<% } else { %>
<a href="Deconnection">Se déconnecter</a>
<% } %>

</nav>
</header>
</body>
</html>