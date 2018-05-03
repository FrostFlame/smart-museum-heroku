<#include "base.ftl">
<#macro title>
Редактирование страницы
</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
    <#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
    <@sf.form action="/edit_profile" method="post" modelAttribute="editForm">
        <@sf.input path="surname" type="text" placeholder="Введите фамилию" value="${editForm.surname}"/>
        <@sf.errors path="surname" cssClass="help-block"/>
        <@sf.input path="name" type="text" placeholder="Введите имя" value="${editForm.name}"/>
        <@sf.errors path="name" cssClass="help-block"/>
        <@sf.input path="thirdname" placeholder="Введите отчество" value="${editForm.thirdname}"/>
        <@sf.errors path="thirdname" cssClass="help-block"/>
    <select name="role">
        <#list positions as position>
            <option value="${position.id}">${position.name}</option>
        </#list>
    </select>
    <select name="role">
        <#list roles as role>
            <option value="${role.id}">${role.name}</option>
        </#list>
    </select>
        <@sf.input path="login" type="text" placeholder="Введите логин" value="${editForm.login}"/>
        <@sf.errors path="login" cssClass="help-block"/>
        <@sf.input path="currentPassword" type="password" placeholder="Введите текущий пароль"/>
        <@sf.errors path="currentPassword" cssClass="help-block"/>
        <@sf.input path="newPassword" type="password" placeholder="Введите новый пароль"/>
        <@sf.errors path="newPassword" cssClass="help-block"/>
        <@sf.input path="newPasswordConf" type="password" placeholder="Введите новый пароль ещё раз"/>
        <@sf.errors path="newPasswordConf" cssClass="help-block"/>
    <input type="submit" value="Войти">
    </@sf.form>
</#macro>