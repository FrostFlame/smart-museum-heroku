<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Private page</title>
</head>
<body>
    <div>Имя: ${user.name}</div>
    <div>Фамилия: ${user.surname}</div>
    <div>Отчество: ${user.thirdName}</div>
    <div>Роль: ${user.role.name}</div>
    <div>Должность: ${user.position.name}</div>
    <div>Фото: <img src="${user.photo}"></div>
</body>
</html>