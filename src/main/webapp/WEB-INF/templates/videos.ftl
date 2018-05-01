<#include "base.ftl">
<#macro title>
Видео
</#macro>
<#macro content>
<#if error?has_content>
<b style="color: red">Error: ${error}</b>
<br/>
</#if>
<form method="POST" action="/videos/add" enctype="multipart/form-data">
    <input required type="text" name="name" id="name">
    <input required type="file" name="file" id="file">
    <input type="submit" value="Добавить">
</form>
<br/>
<#list videos as v>
Name: ${v.name}
<form action="/videos/delete?id=${v.id}" method="post">
    <input type="submit"  value="Удалить" /></td>
</form>
<hr>
<#else>
Нет видео
</#list>
</#macro>