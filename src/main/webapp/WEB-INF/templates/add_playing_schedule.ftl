<#include "base.ftl">
<#macro title>
Добавление расписания
</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

    <#if error?has_content>
    <b style="color: red">Error: ${error}</b><br/>
    </#if>

    <@sf.form  action="/playing_schedule/${exposition.id}/add" method="post" modelAttribute="form">
    <fieldset>

        <label class=" control-label">Время включения</label>
        <@sf.input path="beginTime" type="time" max="23:59" value="${(form.beginTime?string('HH:mm'))!'00:00'}" />
        <@sf.errors path="beginTime" cssClass="help-block"/>

        <label class=" control-label">Время выключения</label>
        <@sf.input path="endTime" type="time" max="23:59 " value="${(form.endTime?string('HH:mm'))!'00:00'}"/>
        <@sf.errors path="endTime" cssClass="help-block"/>

        <label class=" control-label">День недели</label>
        <@sf.select multiple="true" size="7" path="weekDaysId" >
            <#list weekDays as w>
                <@sf.option  value="${w.id}">${w.name}</@sf.option>
            </#list>
        </@sf.select>
        <@sf.errors path="weekDaysId" cssClass="help-block"/>

        <label class=" control-label">Проектор</label>
        <@sf.select multiple="true" size="10" path="projectorsId" >
            <#list projectors as p>
                <@sf.option value="${p.id}">${p.name}</@sf.option>
            </#list>
        </@sf.select>
        <@sf.errors path="projectorsId" cssClass="help-block"/>

        <div class="row">
            <button  type="submit">Добавить</button>
        </div>
    </fieldset>
    </@sf.form>
</#macro>