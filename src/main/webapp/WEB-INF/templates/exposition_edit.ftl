<#include "base.ftl">
<#macro title>
Редактирование ${exposition.name}
</#macro>
<#macro content>
<div class="page-header col-md-12 col-xs-12">
    <div class="col-md-11 col-xs-11">
        <h2><b>Редактирование - ${exposition.name}</b></h2>
    </div>
    <div class="col-md-1 col-xs-1">
        <div class="btn-group">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-cog"></i>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="/expositions/${exposition.id}">К странице экспозиции</a></li>
            </ul>
        </div>
    </div>
</div>

<form action="/expositions/${exposition.id}/edit" method="post">
    <div class="col-md-12">
        <div class="form-group">
            <label for="name">Название</label>
            <div class="form-group row">
                <div class="col-xs-4">
                    <input name="name" type="text" class="form-control" id="formControlInput1"
                           value="${exposition.name}"/>
                </div>
            </div>
        </div>
        <hr>
        <div class="form-group">
            <label for="delete_projector">Удаление проекторов</label><br>
<#list exposition.projectors as projector>
<label class="form-check-label">
    <input type="checkbox" name="delete_projector" value="${projector.id}"> ${projector.name}</label><br>
</#list>
        </div>
        <hr>
        <div class="form-group">
            <label for="boot-multiselect-demo">Добавление новых проекторов</label><br>
            <select id="boot-multiselect-demo" multiple="multiple" name="new_projectors">
    <#list projectors as projector>
        <option value="${projector.id}">${projector.name}</option>
    </#list>
            </select>
        </div>
    </div>
    <div class="col-md-offset-10">
        <input type="submit" class="btn btn-info btn-lg btn-subm" value="Сохранить"/>
    </div>
</form>
<script type="text/javascript">
    $(document).ready(function () {
        $('#boot-multiselect-demo').multiselect({
            includeSelectAllOption: true,
            buttonWidth: 400,
            dropRight: true,
            nonSelectedText: 'Выберите проекторы',
            maxHeight: 150
        });
    });
</script>
</#macro>