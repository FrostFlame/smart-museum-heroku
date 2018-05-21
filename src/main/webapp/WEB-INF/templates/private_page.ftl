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
    <div>Фото: <img src="/image/${u.photo!''}"></div>
    <div class="btn-group">
        <a href="/profile/edit">
            <button type="button" class="btn btn-default">
                Редактирвоать профиль
            </button>
        </a>
    </div>
</body>
</html>