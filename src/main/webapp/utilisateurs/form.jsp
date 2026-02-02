<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="org.example.Model.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire Utilisateur</title>
    <style>
        body { font-family: Arial; padding: 20px; max-width: 600px; margin: 0 auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, select { width: 100%; padding: 8px; box-sizing: border-box; }
        button { padding: 10px 20px; background: #4CAF50; color: white; border: none; cursor: pointer; }
        button:hover { background: #45a049; }
        .error { color: red; }
    </style>
</head>
<body>

<%
    String action = (String) request.getAttribute("action");
    Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
    boolean isEdit = "update".equals(action);
    
    String nom = utilisateur != null ? utilisateur.getNom() : "";
    String prenom = utilisateur != null ? utilisateur.getPrenom() : "";
    String email = utilisateur != null ? utilisateur.getEmail() : "";
    String motDePasse = utilisateur != null ? utilisateur.getMotDePasse() : "";
    String role = utilisateur != null ? utilisateur.getRole() : "user";
    int id = utilisateur != null ? utilisateur.getId() : 0;
%>

<h1><%= isEdit ? "Modifier" : "Créer" %> un utilisateur</h1>

<% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %></p>
<% } %>

<form action="<%= request.getContextPath() %>/utilisateurs/<%= isEdit ? "update" : "create" %>" method="post">
    
    <% if (isEdit) { %>
        <input type="hidden" name="id" value="<%= id %>">
    <% } %>
    
    <div class="form-group">
        <label>Nom :</label>
        <input type="text" name="nom" value="<%= nom %>" required>
    </div>
    
    <div class="form-group">
        <label>Prénom :</label>
        <input type="text" name="prenom" value="<%= prenom %>" required>
    </div>
    
    <div class="form-group">
        <label>Email :</label>
        <input type="email" name="email" value="<%= email %>" required>
    </div>
    
    <div class="form-group">
        <label>Mot de passe :</label>
        <input type="password" name="motDePasse" value="<%= motDePasse %>" required>
    </div>
    
    <div class="form-group">
        <label>Rôle :</label>
        <select name="role">
            <option value="user" <%= "user".equals(role) ? "selected" : "" %>>Utilisateur</option>
            <option value="admin" <%= "admin".equals(role) ? "selected" : "" %>>Administrateur</option>
        </select>
    </div>
    
    <button type="submit"><%= isEdit ? "Mettre à jour" : "Créer" %></button>
    <a href="<%= request.getContextPath() %>/utilisateurs">Annuler</a>
</form>

</body>
</html>