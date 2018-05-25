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
            Редактировать
        </button>
    </a>
    <a href="/admin/users/${u.id}/delete">
        <button type="button" class="btn btn-default">
            Удалить
        </button>
    </a>
        <#if u.status == true>
            <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
            <#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
            <#assign blockDates = {"30 минут":0.5?c, "1 час":1?c, "24 часа": 24?c, "Навсегда": 100500?c}>
            <#assign keys = blockDates?keys>
            <#if error?has_content>
            <b style="color: red">Error: ${error}</b><br/>
            </#if>
            <@sf.form action="/admin/users/block" method="post" modelAttribute="userBlockForm">
            <select name="blockDate">
                <#list keys as key>
                    <option value="${blockDates[key]}">${key}</option>
                </#list>
            </select>
            <input hidden type="text" value="${u.id}" name="userID">
            <input type="submit" value="Заблокировать">
            </@sf.form>
        <hr>
        <#else>
        <form method="post" action="/admin/users/${u.id}/unblock">
            <input type="submit" value="Разблокировать">
        </form>
        </#if>
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