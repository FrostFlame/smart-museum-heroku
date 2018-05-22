<#include "base.ftl">
<#macro title>
Проектор ${projector.getName()}
</#macro>
<#macro content>
${projector.getName()}<br>
Статус: <#if projector.status == 'D'>Выключен
<#elseif projector.status == 'E'>Включен
<#elseif projector.status == 'F'>Неисправен
</#if>
<br/>
<#if error?has_content>
<b style="color: red">Error: ${error}</b>
<br/>
</#if>
<#if projector.getStatus() = 'F'>
<form action="/projector/${projector.id}/fault" method="post">
    <input type="submit" value="Исправен">
</form>
<#else>
<form action="/projector/${projector.id}/fault" method="post">
    <input type="submit" value="Неисправен">
</form>
<#if projector.status == 'E'>
<form action="/projector/${projector.getId()}/turn_off" method="post">
    <input type="submit" value="Выключить">
</form>
<#elseif projector.status == 'D'>
<form action="/projector/${projector.getId()}/turn_on" method="post">
    <input type="submit" value="Включить">
</form>
</#if>
</#if>
<#if projectorVideos?has_content>
    <#list projectorVideos as projectorVideo>
    ${projectorVideo.getVideo().getName()}
        <@security.authorize access="hasAnyRole('MANAGER','ADMIN')">
            <form action="/projector/${projector.id}/modifyVideo?video_id=${projectorVideo.video.id}" method="post">
                <input type="text"  id="num" name="num" value="${projectorVideo.num}" />
                <input type="submit"  value="Изменить" />
            </form>

            <form action="/projector/${projector.id}/deleteVideo?video_id=${projectorVideo.video.id}" method="post">
                <input type="submit"  value="Удалить" />
            </form>
        </@security.authorize>
    <br/>
    </#list>
<#else>
У проектора нет видео
</#if>
<br>
Всего работает времени: ${projector.getCustomSumTime()}<br>
<#if projectorStatistics?has_content>
    <#list projectorStatistics as projectorStatistic>
    Включен: ${projectorStatistic.getBeginDate()}
        <#if projectorStatistic.getEndDate()?has_content>Выключен: ${projectorStatistic.getEndDate()}
        <#else>
        Еще работает
    </#if>
    <br>
    </#list>
</#if>
</#macro>