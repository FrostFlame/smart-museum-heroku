<#include "base.ftl">
<#macro title>
Добавление видео
</#macro>
<#macro content>
<h3>Название экспозиции</h3>
<form action="/expositions/${exposition.id}/edit" method="post">
    <input name="name" type="text" value="${exposition.name}"/>
    <h4>Удаление проекторов</h4>
<#list exposition.projectors as projector>
    ${projector.name}<input type="checkbox" name="delete_projector" value="${projector.id}"><br>
</#list>
    <h4>Добавление новых проекторов</h4>
    <select multiple name="new_projectors">
    <#list projectors as projector>
        <option value="${projector.id}">${projector.name}</option>
    </#list>
    </select><br><br>
    <input type="submit" value="Сохранить">
</form>
<h4><a href="/expositions/${exposition.id}">К странице экспозиции</a></h4>
</#macro>