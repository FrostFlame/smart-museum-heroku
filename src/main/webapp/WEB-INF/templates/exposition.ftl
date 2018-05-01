<#include "base.ftl">
<#macro title>
Экспозиция ${exposition.name}
</#macro>
<#macro content>
<h3>Название экспозиции: ${exposition.name}</h3>
<#if exposition.projectors?has_content>
<h4>Проекторы</h4>
<#list exposition.projectors as projector>
    <div>
        <h5>${projector.name} Статус: ${projector.status}</h5>
    </div>
    <hr>
</#list>
<#else>
<h4>В данной экспозиции нет проекторов</h4>
</#if>
<h4><a href="/expositions/${exposition.id}/addVideo">Добавить видео к проекторам</a></h4>
<h4><a href="/expositions/${exposition.id}/edit">Редактировать экспозицию</a></h4>
<h4><a href="/expositions">К списку экспозиций</a></h4>
</#macro>