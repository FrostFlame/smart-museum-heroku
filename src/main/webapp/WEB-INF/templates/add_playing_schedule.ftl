<#include "base.ftl">
<#macro title>Расписание</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
    <#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
    <div class="row" id="schedule-content">
        <div class="panel-group" id="add-task-panel">
            <div class="panel panel-default">
                <div class="panel-heading">Добавить задачу</div>
                <div class="panel-body">
                    <@sf.form class="add-task-form" action="/playing_schedule/${exposition.id}/add" method="post" modelAttribute="form">
                        <fieldset>
                            <div class="row add-task-row">
                                <div class="col-md-6 col-xs-6">Время включения</div>
                                <div class="col-md-6 col-xs-6"><@sf.input path="beginTime" type="time" max="23:59" value="${(form.beginTime?string('HH:mm'))!'00:00'}" /></div>
                            </div>
                            <div class="row add-task-row">
                                <div class="col-xs-6 col-md-6">&nbsp;</div>
                                <div class="col-md-6 col-xs-4"><@sf.errors path="beginTime" cssClass="help-block"/></div>
                            </div>
                            <div class="row add-task-row">
                                <div class="col-xs-6 col-md-6">Время выключения</div>
                                <div class="col-xs-6 col-md-6"><@sf.input path="endTime" type="time" max="23:59 " value="${(form.endTime?string('HH:mm'))!'00:00'}"/></div>
                            </div>
                            <div class="row add-task-row">
                                <div class="col-md-6 col-xs-6">&nbsp;</div>
                                <div class="col-md-6 col-xs-6"><@sf.errors path="endTime" cssClass="help-block"/></div>
                            </div>
                            <div class="row add-task-row">
                                <div class="col-xs-6 col-md-6">День недели</div>
                                <div class="col-xs-6 col-md-6">
                                    <@sf.select multiple="true" size="3" path="weekDaysId" >
                                        <#list weekDays as w><@sf.option  value="${w.id}">${w.name}</@sf.option></#list>
                                    </@sf.select>
                                </div>
                                <div class="row add-task-row">
                                    <div class="col-xs-6 col-md-6">&nbsp;</div>
                                    <div class="col-xs-6 col-md-6"><@sf.errors path="weekDaysId" cssClass="help-block"/></div>
                                </div>
                            </div>
                            <div class="row" add-task-row>
                                <div class="col-xs-6 col-md-6">Проектор</div>
                                <div class="col-xs-6 col-md-6">
                                    <@sf.select multiple="true" size="3" path="projectorsId" >
                                        <#list projectors as p>
                                            <@sf.option value="${p.id}">${p.name}</@sf.option>
                                        </#list>
                                    </@sf.select>
                                </div>
                            <div class="row" add-task-row>
                                <div class="col-md-6 col-xs-6">&nbsp;</div>
                                <div class="col-md-6 col-xs-6"><@sf.errors path="projectorsId" cssClass="help-block"/></div>
                            </div>
                            <div class="panel-body add-task-button"><button type="submit">Добавить</button></div>
                        </fieldset>
                    </@sf.form>
                </div>
            </div>
        </div>
    </div>
</#macro>


    <#--<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>-->

    <#--<#if error?has_content>-->
    <#--<b style="color: red">Error: ${error}</b><br/>-->
    <#--</#if>-->

    <#--<@sf.form  action="/playing_schedule/${exposition.id}/add" method="post" modelAttribute="form">-->
    <#--<fieldset>-->

        <#--<label class=" control-label">Время включения</label>-->
        <#--<@sf.input path="beginTime" type="time" max="23:59" value="${(form.beginTime?string('HH:mm'))!'00:00'}" />-->
        <#--<@sf.errors path="beginTime" cssClass="help-block"/>-->

        <#--<label class=" control-label">Время выключения</label>-->
        <#--<@sf.input path="endTime" type="time" max="23:59 " value="${(form.endTime?string('HH:mm'))!'00:00'}"/>-->
        <#--<@sf.errors path="endTime" cssClass="help-block"/>-->

        <#--<label class=" control-label">День недели</label>-->
        <#--<@sf.select multiple="true" size="7" path="weekDaysId" >-->
            <#--<#list weekDays as w>-->
                <#--<@sf.option  value="${w.id}">${w.name}</@sf.option>-->
            <#--</#list>-->
        <#--</@sf.select>-->
        <#--<@sf.errors path="weekDaysId" cssClass="help-block"/>-->

        <#--<label class=" control-label">Проектор</label>-->
        <#--<@sf.select multiple="true" size="10" path="projectorsId" >-->
            <#--<#list projectors as p>-->
                <#--<@sf.option value="${p.id}">${p.name}</@sf.option>-->
            <#--</#list>-->
        <#--</@sf.select>-->
        <#--<@sf.errors path="projectorsId" cssClass="help-block"/>-->

        <#--<div class="row">-->
            <#--<button  type="submit">Добавить</button>-->
        <#--</div>-->
    <#--</fieldset>-->
    <#--</@sf.form>-->

<script>
    $(document).ready(function () {
        $("li.active").removeClass('active');
        $("#schedule").addClass('active');
    })
</script>
