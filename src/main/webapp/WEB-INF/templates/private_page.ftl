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
    <input  id = "role" type="text"  value="${u.role.name}" hidden="true"/>
    <div>Роль: ${u.role.name}</div>
    <div>Должность: ${u.position.name}</div>
    <#if u.photo?has_content>
    <div>Фото:<img class="user-photo" src="/image/${u.photo!}"></div>
    <#else>
    <div>Фото:<img class="user-photo" src="/resources/static/img/image_unavailable.jpg"></div>
    </#if>
    <div class="btn-group">
        <a href="/profile/edit">
            <button type="button" class="btn btn-default">
                Редактировать профиль
            </button>
        </a>
    </div>
    <script>
        $(document).ready(function () {
            $("li.active").removeClass('active');
            var role = document.getElementById("role").value;
            if (role != 'ADMIN'){
                $("#profile").addClass('active');
            }

        })
    </script>
</body>
</html>