<#include "base.ftl">
<#macro title>
Список видеоклипов
</#macro>
<#macro content>
<div class="page-header col-md-12 col-xs-12">
    <div class="col-md-10 col-xs-10">
        <h2><b>Видеоклипы</b></h2>
        <#if error?has_content>
            <b style="color: red">Error: ${error}</b>
        </#if>
    </div>
    <@security.authorize access="hasRole('ADMIN')">
    <div class="col-md-1 col-xs-1">
        <div class="btn-group">
            <a href="/videos/new_video">
                <button type="button" class="btn btn-default">
                    Добавить видео
                </button>
            </a>
        </div>
    </div>
    </@security.authorize>
</div>
<div class="col-md-12 col-xs-12">
    <form role="search" method="GET" action="/videos">
        <div class="input-group">
            <input type="text" class="form-control" name="searchField" placeholder="Найти видеоклип">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                        </span>
        </div>
    </form>
</div>
<div class="col-md-12 table">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Название</th>
            <th></th>
            <@security.authorize access="hasAnyRole('MANAGER','ADMIN')">
            <th>Удаление</th>
            </@security.authorize>
        </tr>
        </thead>
        <tbody>
            <#list videos as v>
            <tr>
                <td>${v.name}</td>
                <td>
                </td>
                <@security.authorize access="hasAnyRole('MANAGER','ADMIN')">
                <td>
                    <a class="btn btn-danger" href="#ModalDelete1" data-toggle="modal" onclick="addId(${v.id})"><span
                            class="glyphicon glyphicon-remove"></span>
                        Удалить
                    </a>
                </td>
                </@security.authorize>
            </tr>
            <#else>
            Видео не найдено
            </#list>
        </tbody>
    </table>
    <div id="ModalDelete1" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                    </button>
                    <h4 class="modal-title">Удаление видеоклипа</h4>
                </div>
                <div class="modal-body">
                    Вы действительно хотите удалить это видео?
                </div>
                <div class="modal-footer">
                    <form id="formDelete" action="/videos/delete" method="post">
                        <button type="button" class="btn btn-default" onclick="send()">Да</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">Отмена</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var selectedId;
    function addId(id) {
        selectedId = id;
    }
    function send() {
        var input = $("<input>")
                .attr("type", "hidden")
                .attr("name", "id").val(selectedId);
        $('#formDelete').append($(input));
        $('#formDelete').submit();
    }

</script>
</#macro>
