<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Model.Reservation" %>
<%@ page import="org.example.Model.Hotel" %>
<!DOCTYPE html>
<html>
<head>
    <title><%= "create".equals(request.getAttribute("action")) ? "Nouvelle Réservation" : "Modifier Réservation" %></title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 20px; 
            max-width: 600px; 
        }
        .form-group { 
            margin-bottom: 15px; 
        }
        label { 
            display: block; 
            margin-bottom: 5px; 
            font-weight: bold; 
        }
        input[type="text"],
        input[type="number"],
        select {
            width: 100%; 
            padding: 8px; 
            border: 1px solid #ddd; 
            border-radius: 3px; 
            box-sizing: border-box;
        }
        .btn { 
            padding: 10px 20px; 
            border: none; 
            border-radius: 3px; 
            cursor: pointer; 
            text-decoration: none;
            display: inline-block;
        }
        .btn-success { 
            background-color: #28a745; 
            color: white; 
        }
        .btn-secondary { 
            background-color: #6c757d; 
            color: white; 
        }
        .message { 
            padding: 10px; 
            margin-bottom: 20px; 
            border-radius: 3px; 
        }
        .error { 
            background-color: #f8d7da; 
            color: #721c24; 
        }
    </style>
</head>
<body>
    <h1><%= "create".equals(request.getAttribute("action")) ? "Nouvelle Réservation" : "Modifier Réservation" %></h1>

    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <div class="message error"><%= error %></div>
    <% } %>

    <% 
        String action = (String) request.getAttribute("action");
        Reservation reservation = (Reservation) request.getAttribute("reservation");
        List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
        
        String formAction = "create".equals(action) ? "/reservations/create" : "/reservations/update";
    %>

    <form method="POST" action="<%= formAction %>">
        
        <% if ("update".equals(action) && reservation != null) { %>
            <input type="hidden" name="id" value="<%= reservation.getId() %>">
        <% } %>

        <div class="form-group">
            <label for="idHotel">Hotel *</label>
            <select id="idHotel" name="idHotel" required>
                <option value="">-- Sélectionnez un hotel --</option>
                <% 
                    if (hotels != null) {
                        for (Hotel h : hotels) {
                            boolean selected = reservation != null && reservation.getIdHotel() == h.getId();
                %>
                    <option value="<%= h.getId() %>" <%= selected ? "selected" : "" %>>
                        <%= h.getNom() %>
                    </option>
                <% 
                        }
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="idClient">ID Client *</label>
            <input type="text" 
                   id="idClient" 
                   name="idClient" 
                   value="<%= reservation != null ? reservation.getIdClient() : "" %>" 
                   required>
        </div>

        <div class="form-group">
            <label for="nbPassager">Nombre de Passagers *</label>
            <input type="number" 
                   id="nbPassager" 
                   name="nbPassager" 
                   min="1"
                   value="<%= reservation != null ? reservation.getNbPassager() : "" %>" 
                   required>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-success">
                <%= "create".equals(action) ? "➕ Créer" : "💾 Mettre à jour" %>
            </button>
            <a href="/reservations" class="btn btn-secondary">❌ Annuler</a>
        </div>
    </form>
</body>
</html>