<#include "base.ftl">
<#macro title>
Найти пользователя
</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
    <#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
    <#assign blockDates = {"30 минут":0.5?c, "1 час":1?c, "24 часа": 24?c, "Навсегда": 100500?c}>
    <#assign keys = blockDates?keys>
<div class="page-header col-md-12 col-xs-12">
    <div class="col-md-11 col-xs-11">
        <h2><b>Список пользователей</b></h2>
    </div>
    <div class="col-md-1 col-xs-1">
        <div class="btn-group">
            <a type="button" class="btn btn-default" href="/admin/users/registration">
                Добавить пользователя
            </a>
        </div>
    </div>
</div>
    <#if success?has_content>
    <div class="alert alert-success" role="alert">
    ${success}
    </div>
    </#if>
<form role="search" action="/admin/users/search">
    <div class="input-group col-md-12">
        <input name="searchField" type="text" class="form-control" placeholder="Найти пользователя">
        <span class="input-group-btn">
    	                <button class="btn btn-default" type="submit">
	      	                <i class="glyphicon glyphicon-search"></i>
                        </button>
	                </span>
    </div>
    <div class="form-group col-md-4">
        <select name="role" id="boot-multiselect1">
            <#if roles?size gt 1>
                <option value="all">Все</option>
            </#if>
            <#list roles as role>
                <option value="${role.id}">${role.name}</option>
            <#else>
            </#list>
        </select>
    </div>
    <div class="form-group col-md-4">
        <select name="position" id="boot-multiselect2">
            <#if positions?size gt 1>
                <option value="all">Все</option>
            </#if>
            <#list positions as position>
                <option value="${position.id}">${position.name}</option>
            <#else>
            </#list>
        </select>
    </div>
    <div class="form-group col-md-4">
        <select name="status" id="boot-multiselect3">
            <option value="all">Все</option>
            <option value="true">Активные</option>
            <option value="false">Заблокированные</option>
        </select>
    </div>
</form>
<table class="table table-hover users">
    <thead>
    <tr>
        <th>ФИ</th>
        <th>Логин</th>
        <th>Статус</th>
        <th>Роль</th>
        <th>Должность</th>
        <th>Изменение</th>
    </tr>
    </thead>
    <tbody>
        <#list users as u>
        <tr>
            <td><a href="/admin/users/${u.id}">${u.fullName}</a></td>
            <td>${u.login}</td>
            <td>
                <#if u.status>
                    <a type="button" class="btn btn-success" href="#ModalBlock" data-toggle="modal"
                       onclick="addId(${u.id})"></span>
                        Активен
                    </a>
                <#else>
                    <form method="post" action="/admin/users/${u.id}/unblock">
                        <button type="submit" class="btn btn-warning"></span>
                            Заблокирован
                        </button>
                    </form>
                </#if>
            </td>
            <td>${u.role.name}</td>
            <td>${u.position.name}</td>
            <td>
                <a type="button" class="btn btn-info" href="/admin/users/${u.id}/edit"><span
                        class="glyphicon glyphicon-pencil"></span>
                </a>
                <a class="btn btn-danger" href="#ModalDelete1" data-toggle="modal" onclick="addId(${u.id})"><span
                        class="glyphicon glyphicon-remove"></span>
                </a>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
<div id="ModalDelete1" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h4 class="modal-title">Удаление пользователя</h4>
            </div>
            <div class="modal-body">
                Вы действительно хотите удалить этого пользователя?
            </div>
            <div class="modal-footer">
                <form id="formDelete" action="/admin/users/delete" method="post">
                    <button type="button" class="btn btn-default" onclick="deleteSend()">Да</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Отмена
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="ModalBlock" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                </button>
                <h3 class="modal-title">Блокировка пользователя</h3>
            </div>
            <div class="modal-body">
                <h4>Время блокировки пользователя</h4>
                <@sf.form id="formBlock" action="/admin/users/block" method="post" modelAttribute="userBlockForm" style="font-size: medium">
                    <#list keys as key>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="blockDate" id="t${blockDates[key]}"
                                   value="${blockDates[key]}">
                            <label class="form-check-label" for="t${blockDates[key]}">
                            ${key}
                            </label>
                        </div>
                    </#list>
                </@sf.form>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" onclick="blockSend()">OK</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Отмена
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#boot-multiselect1').multiselect({
            includeSelectAllOption: true,
            buttonWidth: 300,
            dropRight: true,
            nonSelectedText: 'Выбрать',
            maxHeight: 150
        });
    });
    $(document).ready(function () {
        $('#boot-multiselect2').multiselect({
            includeSelectAllOption: true,
            buttonWidth: 300,
            dropRight: true,
            nonSelectedText: 'Выбрать',
            maxHeight: 150
        });
    });
    $(document).ready(function () {
        $('#boot-multiselect3').multiselect({
            includeSelectAllOption: true,
            buttonWidth: 300,
            dropRight: true,
            nonSelectedText: 'Выбрать',
            maxHeight: 150
        });
    });
</script>
<script type="text/javascript">
    var selectedId;
    function addId(id) {
        selectedId = id;
    }

    function deleteSend() {
        var input = $("<input>")
                .attr("type", "hidden")
                .attr("name", "id").val(selectedId);
        $('#formDelete').append($(input));
        $('#formDelete').submit();
    }

    function blockSend() {
        var input = $("<input>")
                .attr("type", "hidden")
                .attr("name", "userID").val(selectedId);
        $('#formBlock').append($(input));
        $('#formBlock').submit();
    }

</script>
</#macro>

<script>
    $(document).ready(function () {
        $("li.active").removeClass('active');
        $("#users").addClass('active');
    })
</script>