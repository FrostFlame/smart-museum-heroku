<#include "base.ftl">
<#macro title>
Блокирование пользователя
</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
    <#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
    <#assign blockDates = {"30 минут":0.5?c, "1 час":1?c, "24 часа": 24?c, "Навсегда": 100500?c}>
    <#assign keys = blockDates?keys>
    <#if error?has_content>
    <b style="color: red">Error: ${error}</b><br/>
    </#if>
    <@sf.form action="/admin/users/profile/block" method="post" modelAttribute="userBlockForm">
        <select name="blockDate">
            <#list keys as key>
                <option value="${blockDates[key]}">${key}</option>
            </#list>
        </select>
    <input type="submit" value="Заблокировать">
    </@sf.form>
</#macro>