<#include "base.ftl">
<#macro title>
Блокирование пользователя
</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
    <#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
    <#if error?has_content>
    <b style="color: red">Error: ${error}</b><br/>
    </#if>
    <@sf.form action="/profile/${userBlockForm.userID}/block" method="post" modelAttribute="editForm">
        <#if userBlockForm.blockDates?has_content>
        <select name="blockDate">
            <#list userBlockForm.blockDates as date>
                <option value="${date.value}">${date.key}</option>
            </#list>
        </select>
        </#if>
    <input type="submit" value="Заблокировать">
    </@sf.form>
</#macro>