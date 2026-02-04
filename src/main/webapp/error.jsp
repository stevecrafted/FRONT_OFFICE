<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        .box { border: 1px solid #ddd; padding: 16px; max-width: 600px; }
        a { color: #2196F3; text-decoration: none; }
    </style>
</head>
<body>
    <div class="box">
        <h1>Erreur</h1>
        <p>
            <%= request.getAttribute("message") != null
                ? request.getAttribute("message")
                : "Une erreur est survenue." %>
        </p>
        <p><a href="<%= request.getContextPath() %>/utilisateurs">Retour a la liste</a></p>
    </div>
</body>
</html>
