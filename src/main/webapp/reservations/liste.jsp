<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Model.Reservation" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Réservations</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 20px; 
        }
        table { 
            width: 100%; 
            border-collapse: collapse; 
            margin-top: 20px; 
        }
        th, td { 
            border: 1px solid #ddd; 
            padding: 10px; 
            text-align: left; 
        }
        th { 
            background-color: #f4f4f4; 
        }
        .btn { 
            padding: 5px 10px; 
            text-decoration: none; 
            color: white; 
            border-radius: 3px; 
            margin: 2px; 
        }
        .btn-primary { background-color: #007bff; }
        .btn-success { background-color: #28a745; }
        .btn-danger { background-color: #dc3545; }
        .message { 
            padding: 10px; 
            margin-bottom: 20px; 
            border-radius: 3px; 
        }
        .success { background-color: #d4edda; color: #155724; }
        .error { background-color: #f8d7da; color: #721c24; }
    </style>
</head>
<body>
    <h1>Liste des Réservations</h1>

    <% String message = (String) request.getAttribute("message"); %>
    <% if (message != null) { %>
        <div class="message success"><%= message %></div>
    <% } %>

    <a href="/reservations/nouveau" class="btn btn-success">➕ Nouvelle Réservation</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>ID Hotel</th>
                <th>ID Client</th>
                <th>Nb Passagers</th>
                <th>Date/Heure</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                if (reservations != null && !reservations.isEmpty()) {
                    for (Reservation r : reservations) {
            %>
                <tr>
                    <td><%= r.getId() %></td>
                    <td><%= r.getIdHotel() %></td>
                    <td><%= r.getIdClient() %></td>
                    <td><%= r.getNbPassager() %></td>
                    <td><%= r.getDateHeure() %></td>
                    <td>
                        <a href="/reservations/<%= r.getId() %>" class="btn btn-primary">👁 Voir</a>
                        <a href="/reservations/<%= r.getId() %>/edit" class="btn btn-primary">✏️ Modifier</a>
                        <a href="/reservations/<%= r.getId() %>/delete" class="btn btn-danger" 
                           onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette réservation ?')">
                           🗑 Supprimer
                        </a>
                    </td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr>
                    <td colspan="6" style="text-align: center;">Aucune réservation trouvée</td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>