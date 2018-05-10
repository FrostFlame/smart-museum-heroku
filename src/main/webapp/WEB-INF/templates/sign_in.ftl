<!doctype html>
<html lang="en">
<head>
    <title>Авторизация</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/auth.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
<div id="main-container" class="container-fluid">
    <div class="row">
        <div id="auth-form" class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Авторизация</h3>
            </div>
        <@sf.form action="/login/process" method="post" modelAttribute="authForm" class="panel-body">
            <div class="input-group">
          <span class="input-group-addon">
            <span class="glyphicon glyphicon-user"></span>
          </span>
                <@sf.input path="login" type="text" class="form-control" placeholder="Имя пользователя" id="login"/>
                <@sf.errors path="login" cssClass="help-block"/>
            </div>
            <div class="input-group">
          <span class="input-group-addon">
            <span class="glyphicon glyphicon-lock"></span>
          </span>
                <@sf.input path="password" type="password" class="form-control" placeholder="Пароль"/>
                <@sf.errors path="password" cssClass="help-block"/>
            </div>
            <#if error??>
                <div class="error">
                    <p>${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
                </div>
            </#if>
            <button type="submit" class="btn btn-primary">Войти</button>
        </@sf.form>
        </div>
    </div>
</div>