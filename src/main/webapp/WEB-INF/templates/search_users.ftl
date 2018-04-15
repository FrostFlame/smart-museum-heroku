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
        <option value="null">Все</option>
        <option value="true">Активные</option>
        <option value="false">Заблокированные</option>
    </select>
    <input type="submit" value="Найти">
</form>

<#list users as u>
<h3><a href="#">${u.fullName}</a></h3>
<h3>Role: ${u.role.name}</h3>
<h3>Position: ${u.position.name}</h3>
<h3>Login: ${u.login}</h3>
<br>
<#else>
Нет пользователей
</#list>