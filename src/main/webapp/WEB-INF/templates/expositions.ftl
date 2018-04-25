<#include "base.ftl">
<#macro title>
Добавление видео
</#macro>
<#macro content>
<h3><a href="/expositions/add">Добавить новую экспозицию</a></h3>
<#if expositions?has_content>
<h3>Список экспозиций</h3>
<#list expositions as exposition>
    <a href="/expositions/${exposition.id}">${exposition.name}</a>
    <form action="/expositions/${exposition.id}/delete" method="post">
        <input type="submit" value="Удалить">
    </form>
<hr>
</#list>
<#else>
    <h4>Экспозиций не найдено</h4>
</#if>
</#macro>