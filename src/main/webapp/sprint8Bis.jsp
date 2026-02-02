<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    
    <form method="get" action="sprintHuitBis">
        <input type="text" name="e.name">
        <input type="text" name="e.departement[0].name">
        <input type="text" name="e.departement[1].name">

        <input type="text" name="e.departement[0].manager.name">
        <input type="text" name="e.departement[1].manager.name">  

        <input type="submit" value="submit">
    </form>

</body>
</html>
