<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        .card { border: 1px solid #ddd; padding: 16px; max-width: 480px; }
        .label { color: #666; font-size: 12px; text-transform: uppercase; }
        .value { font-size: 18px; margin-bottom: 12px; }
    </style>
</head>
<body>
    <div class="card">
        <h1>Profil utilisateur</h1>
        <div class="label">Username</div>
        <div class="value"><%= request.getAttribute("username") %></div>
        <div class="label">Email</div>
        <div class="value"><%= request.getAttribute("email") %></div>
    </div>
</body>
</html>
