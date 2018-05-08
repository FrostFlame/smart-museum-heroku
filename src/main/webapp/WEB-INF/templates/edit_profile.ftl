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
        <@sf.input path="thirdName" placeholder="Введите отчество" value="${editForm.thirdName!''}"/>
        <@sf.errors path="thirdName" cssClass="help-block"/>

        <#if positions?has_content>
            <select name="position">
                <#list positions as position>
                    <option value="${position.id}">${position.name}</option>
                </#list>
            </select>
        </#if>
        <#if roles?has_content>
            <select name="role">
                <#list roles as role>
                    <option value="${role.id}">${role.name}</option>
                </#list>
            </select>
        </#if>
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