<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload fichier</title>
</head>
<body>

<h2>Upload de fichiers</h2>

<form action="upload" method="post" enctype="multipart/form-data">

    <label>Nom :</label>
    <input type="text" name="name" />
    <br><br>

    <label>Fichier 1 :</label>
    <input type="file" name="file1" />
    <br><br>

    <label>Fichier 2 :</label>
    <input type="file" name="file2" />
    <br><br>

    <button type="submit">Envoyer</button>

</form>

</body>
</html>
