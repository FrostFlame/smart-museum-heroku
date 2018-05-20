<!doctype html>
<html lang="en">
<head>
    <title>Авторизация</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/static/css/auth.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.js"></script>
</head>
<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
<div id="main-container" class="container-fluid">
    <div class="row"><div class="header-smart"><h1>Smart museum</h1></div></div>
    <div class="row">
        <div id="auth-form" class="panel panel-default">
            <div class="panel-heading"><h3 class="panel-title">Авторизация</h3></div>
            <@sf.form action="/login/process" method="post" modelAttribute="authForm" class="panel-body">
                <div class="form-group has-feedback">
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                        <@sf.input path="login" type="text" class="form-control" name="password" placeholder="Имя пользователя" id="login"/>
                        <@sf.errors path="login" cssClass="help-block"/>
                        <span class="glyphicon form-control-feedback"></span>
                    </div>
                    <span class="help-block">&nbsp;</span>
                </div>
                <div class="form-group has-feedback">
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                        <@sf.input path="password" type="password" name="password" class="form-control" id="password" placeholder="Пароль"/>
                        <@sf.errors path="password" cssClass="help-block"/>
                        <span class="glyphicon form-control-feedback"></span>
                    </div>
                    <span class="help-block">&nbsp;</span>
                </div>
                <div class="input-group"><input type="checkbox"> Запомнить меня</div>
                <button type="submit" class="btn btn-default">Войти</button>
                <div class="error">
                    <#if error??><p>${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
                    <#else><p>&nbsp;</p>
                    </#if>
                </div>
            </@sf.form>
        </div>
    </div>
</div>
</html>

<script>
    $(document).ready(function () {

        function check() {
            $('#auth-form').validate({
                errorPlacement: function (error, element) {
                    $(element).parent().next('.help-block').text(error.text())
                },
                rules: {
                    login: {
                        required: true
                    },
                    password: {
                        required: true
                    }
                },
                messages: {
                    login: "Введите имя пользователя",
                    password: "Введите пароль"
                },
                highlight: function (element) {
                    $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
                    $(element).next('.glyphicon').removeClass('glyphicon-ok').addClass('glyphicon-remove');
                },
                unhighlight: function (element) {
                    $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
                    $(element).next('.glyphicon').removeClass('glyphicon-remove').addClass('glyphicon-ok');
                    $(element).parent().next('.help-block').text('Супер!');
                }
            });
        }

        setTimeout(check, 0);
    })
</script>