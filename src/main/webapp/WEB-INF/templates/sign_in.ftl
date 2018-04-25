<#include "base.ftl">
<#macro title>
    Авторизация
</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
    <#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
    <@sf.form action="/login/process" method="post" modelAttribute="authForm">
        <@sf.input path="login" type="text" placeholder="Введите логин"/>
        <@sf.errors path="login" cssClass="help-block"/>
        <@sf.input path="password" type="password" placeholder="Введите пароль"/>
        <@sf.errors path="password" cssClass="help-block"/>
        Remember Me: <input type="checkbox" name="remember-me" />
    <input type="submit" value="Войти">
    </@sf.form>
</#macro>