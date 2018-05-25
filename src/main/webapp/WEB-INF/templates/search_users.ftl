<#include "base.ftl">
<#macro title>
Найти пользователя
</#macro>
<#macro content>
<form action="/admin/users/search">
    <input type="text" name="searchField">
<select name="role">
<#if roles?size gt 1>
<option value="all">Все</option>
</#if>
<#list roles as role>
<option value="${role.id}">${role.name}</option>
<#else>
</#list>
</select>

<select name="position">
<#if positions?size gt 1>
<option value="all">Все</option>
</#if>
<#list positions as position>
<option value="${position.id}">${position.name}</option>
<#else>
</#list>
</select>
    <select name="status">
        <option value="all">Все</option>
        <option value="true">Активные</option>
        <option value="false">Заблокированные</option>
    </select>
    <input type="submit" value="Найти">
</form>

<#list users as u>
<h3><a href="/admin/users/${u.id}">${u.fullName}</a></h3>
<h3>Role: ${u.role.name}</h3>
<h3>Position: ${u.position.name}</h3>
<h3>Login: ${u.login}</h3>
<#if u.status>
    <h3>Status: активен</h3>
<#else>
    <h3>Status: заблокирован</h3>
</#if>
<a href="/admin/users/${u.id}/edit">
    <button type="button" class="btn btn-default">
        Редактирвоать
    </button>
</a>
<a href="/admin/users/${u.id}/delete">
    <button type="button" class="btn btn-default">
        Удалить
    </button>
</a>
<hr>
<#else>
Нет пользователей
</#list>
</#macro>

<script>
    $(document).ready(function () {
        $("li.active").removeClass('active');
        $("#users").addClass('active');
    })
</script>