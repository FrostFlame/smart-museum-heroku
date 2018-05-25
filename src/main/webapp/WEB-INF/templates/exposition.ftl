<#include "base.ftl">
<#macro title>
Экспозиция ${exposition.name}
</#macro>
<#macro content>
<div class="page-header col-md-12 col-xs-12">
    <div class="col-md-11 col-xs-11">
        <h2><b>${exposition.name}</b></h2>
    </div>
    <div class="col-md-1 col-xs-1">
        <div class="btn-group">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-cog"></i>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="/expositions/${exposition.id}/addVideo">Добавить видео к проекторам</a></li>
                <li><a href="/expositions/${exposition.id}/edit">Редактировать экспозицию</a></li>
                <li><a href="/expositions">К списку экспозиций</a></li>
            </ul>
        </div>
    </div>
</div>

    <#if exposition.projectors?has_content>
<div class="col-md-12">
    <div class="col-md-offset-1 col-md-10">
        <h3>Список проекторов</h3>
    </div>
</div>
    <div class="col-md-12 table">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Статус</th>
                </tr>
                </thead>
                <tbody>
        <#list exposition.projectors as projector>
        <tr>
            <td><a href="/projector/${projector.id}">${projector.name}</a></td>
            <td>${projector.status}</td>
        </tr>
        </#list>
                </tbody>
            </table>
    <#else>
<div class="col-md-12">
    <div class="col-md-offset-1 col-md-10">
        <h3>В данной экспозиции нет проекторов</h3>
    </div>
</div>
    </#if>
</#macro>