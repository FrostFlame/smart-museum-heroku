<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Private page</title>
</head>
<body>
    <div>Имя: ${u.name}</div>
    <div>Фамилия: ${u.surname}</div>
    <div>Отчество: ${u.thirdName}</div>
    <div>Роль: ${u.role.name}</div>
    <div>Должность: ${u.position.name}</div>
    <div>Фото: <img src="${u.photo!}"></div>
</body>
</html>