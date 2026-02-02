<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="org.example.Model.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
    <title>Détails Utilisateur</title>
    <style>
        body { font-family: Arial; padding: 20px; max-width: 600px; margin: 0 auto; }
        .detail { margin-bottom: 15px; }
        .label { font-weight: bold; }
        .btn { padding: 10px 20px; text-decoration: none; display: inline-block; margin: 5px; }
        .btn-primary { background: #4CAF50; color: white; }
        .btn-danger { background: #f44336; color: white; }
    </style>
</head>
<body>

<%
    Utilisateur user = (Utilisateur) request.getAttribute("utilisateur");
    if (user != null) {
%>

<h1>Détails de l'utilisateur</h1>

<div class="detail">
    <span class="label">ID :</span> <%= user.getId() %>
</div>

<div class="detail">
    <span class="label">Nom :</span> <%= user.getNom() %>
</div>

<div class="detail">
    <span class="label">Prénom :</span> <%= user.getPrenom() %>
</div>

<div class="detail">
    <span class="label">Email :</span> <%= user.getEmail() %>
</div>

<div class="detail">
    <span class="label">Rôle :</span> <%= user.getRole() %>
</div>

<div class="detail">
    <span class="label">Date de création :</span> <%= user.getDateCreation() %>
</div>

<div>
    <a href="<%= request.getContextPath() %>/utilisateurs/<%= user.getId() %>/edit" class="btn btn-primary">Modifier</a>
    <a href="<%= request.getContextPath() %>/utilisateurs/<%= user.getId() %>/delete" 
       class="btn btn-danger" 
       onclick="return confirm('Êtes-vous sûr ?')">Supprimer</a>
    <a href="<%= request.getContextPath() %>/utilisateurs" class="btn">Retour à la liste</a>
</div>

<%
    }
%>

</body>
</html>