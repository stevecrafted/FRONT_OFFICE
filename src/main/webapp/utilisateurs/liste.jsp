<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Model.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des utilisateurs</title>
    <style>
        body { font-family: Arial; padding: 20px; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        tr:hover { background-color: #f5f5f5; }
        .btn { padding: 8px 15px; text-decoration: none; margin: 2px; display: inline-block; }
        .btn-primary { background: #4CAF50; color: white; }
        .btn-danger { background: #f44336; color: white; }
        .btn-info { background: #2196F3; color: white; }
    </style>
</head>
<body>

<h1>Liste des utilisateurs</h1>

<a href="<%= request.getContextPath() %>/utilisateurs/nouveau" class="btn btn-primary">+ Nouvel utilisateur</a>

<%
    List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("utilisateurs");
    if (utilisateurs != null && !utilisateurs.isEmpty()) {
%>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Email</th>
                <th>Rôle</th>
                <th>Date création</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Utilisateur user : utilisateurs) {
        %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getNom() %></td>
                <td><%= user.getPrenom() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getRole() %></td>
                <td><%= user.getDateCreation() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/utilisateurs/<%= user.getId() %>" class="btn btn-info">Voir</a>
                    <a href="<%= request.getContextPath() %>/utilisateurs/<%= user.getId() %>/edit" class="btn btn-primary">Modifier</a>
                    <a href="<%= request.getContextPath() %>/utilisateurs/<%= user.getId() %>/delete" 
                       class="btn btn-danger" 
                       onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')">Supprimer</a>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
<%
    } else {
%>
    <p>Aucun utilisateur trouvé.</p>
<%
    }
%>

</body>
</html>