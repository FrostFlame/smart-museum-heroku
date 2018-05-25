<#include "base.ftl">
<#macro title>
Проекторы
</#macro>
<#macro content>
<div class="col-md-12 col-xs-12">
    <#if success?has_content>
    <div class="alert alert-success" role="alert">
        ${success}
    </div>
    </#if>
    <#if error?has_content>
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </#if>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
    <#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
    <@sf.form action="/projector/create" method="post" modelAttribute="projectorForm">
        <div class="input-group">
            <@sf.input path="name" type="text" placeholder="Введите название" required="true" class="form-control"/>
            <span class="input-group-btn">
                <input type="submit" class="btn btn-default" value="Создать">
            </span>
        </div>
        <#if error?has_content>
            <b style="color: red">Error: ${error}</b>
            <br/>
        </#if>
        <@sf.errors path="name" cssClass="help-block"/>
    </@sf.form>
</div>
<div class="col-md-12 table">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Название</th>
            <th>Удаление</th>
            <th>Исправность</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <#if projectors?has_content>
                <#list projectors as projector>
                    <td><a href="/projector/${projector.getId()}">${projector.getName()}</a></td>
                    <td>
                        <a class="btn btn-danger" href="#ModalDelete1" data-toggle="modal" onclick="addId(${projector.id})"><span
                                class="glyphicon glyphicon-remove"></span>
                            Удалить
                        </a>
                    </td>
                    <td>
                        <#if projector.status == 'F'>
                            <button type="button" class="btn btn-warning"><span
                                    class="glyphicon glyphicon-exclamation-sign"></span>
                                Неисправен
                            </button>
                        <#else>
                            <button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span>
                                Исправен&nbsp;&nbsp;&nbsp;&nbsp;
                            </button>
                        </#if>
                    </td>
                </tr>
                </#list>
            <#else>
                Нет проекторов
            </#if>
        </tbody>
    </table>
    <div id="ModalDelete1" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                    </button>
                    <h4 class="modal-title">Удаление проектора</h4>
                </div>
                <div class="modal-body">
                    Вы действительно хотите удалить этот проектор?
                </div>
                <div class="modal-footer">
                    <form id="formDelete" action="/projector/delete" method="post">
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

<script>
    $(document).ready(function () {
        $("li.active").removeClass('active');
        $("#projectors").addClass('active');
    })
</script>