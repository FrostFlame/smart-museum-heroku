<#include "base.ftl">
<#macro title>
Редактирование страницы
</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
    <#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
    <#if error?has_content>
    <b style="color: red">Error: ${error}</b><br/>
    </#if>
    <#if u.role.name == "ADMIN">
        <#assign link = "/admin/users/${editForm.id}/edit_profile" >
    <#else>
        <#assign link = "/edit_profile">
    </#if>
    <@sf.form action= "${link}" method="post" modelAttribute="editForm" enctype="multipart/form-data">

        <@sf.input path="id" type="text"  value="${editForm.id}" hidden="true"/>
        <@sf.input path="surname" type="text" placeholder="Введите фамилию" value="${editForm.surname}"/>
        <@sf.errors path="surname" cssClass="help-block"/>
        <@sf.input path="name" type="text" placeholder="Введите имя" value="${editForm.name}"/>
        <@sf.errors path="name" cssClass="help-block"/>
        <@sf.input path="thirdName" placeholder="Введите отчество" value="${editForm.thirdName!''}"/>
        <@sf.errors path="thirdName" cssClass="help-block"/>
        <#if editForm.photo?has_content>
            <@sf.input path="photo" type="text"  value="${editForm.photo}" hidden="true"/>
            <img src="/image/${editForm.photo!''}">
        </#if>
        <@sf.input path="photoFile" type="file" placeholder="Фото"/>
        <@sf.errors path="photoFile" cssClass="help-block"/>
        <#if u.role.name == "ADMIN">
            <#if positions?has_content>
            <select name="position">
                <#list positions as position>
                    <option value="${position.id}" <#if position.id == editForm.position>selected="selected"</#if>>${position.name}</option>
                </#list>
            </select>
            </#if>
            <#if roles?has_content>
            <select name="role">
                <#list roles as role>
                    <option value="${role.id}" <#if role.id == editForm.role>selected="selected"</#if>>${role.name}</option>
                </#list>
            </select>
            </#if>
        </#if>
        <@sf.input path="login" type="text" placeholder="Введите логин" value="${editForm.login}"/>
        <@sf.errors path="login" cssClass="help-block"/>
    <input type="submit" value="Сохранить">
    </@sf.form>

    <p>Смена пароля</p><br>
    <#if u.role.name == "ADMIN">
        <#assign link2 = "/admin/users/${editForm.id}/change_password" >
    <#else>
        <#assign link2 = "/change_password">
    </#if>
    <@sf.form action= "${link2}" method="post" modelAttribute="changePasswordForm" >

        <#if u.role.name != "ADMIN">
            <@sf.input path="currentPassword" type="password" placeholder="Введите текущий пароль"/>
            <@sf.errors path="currentPassword" cssClass="help-block"/>
        </#if>
        <@sf.input path="newPassword" type="password" placeholder="Введите новый пароль"/>
        <@sf.errors path="newPassword" cssClass="help-block"/>
        <#if u.role.name != "ADMIN">
            <@sf.input path="newPasswordConf" type="password" placeholder="Введите новый пароль ещё раз"/>
            <@sf.errors path="newPasswordConf" cssClass="help-block"/>
        </#if>
    <input type="submit" value="Изменить">
    </@sf.form>
</#macro>