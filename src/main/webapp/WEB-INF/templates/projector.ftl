${projector.getName()}
${projector.getStatus()}
<form action="/projector/turn_on/${projector.getId()}" method="post">
    <input type="submit" value="Включить">
</form>
<form action="/projector/turn_off/${projector.getId()}" method="post">
    <input type="submit" value="Выключить">
</form>
<#if projectorVideos?has_content>
    <#list projectorVideos as projectorVideo>
    ${projectorVideo.getVideo().getName()}
    </#list>
<#else>
У проектора нет видео
</#if>
Всего работает времени: ${projector.getSumTime()}
<#if projectorStatistics?has_content>
    <#list projectorStatistics as projectorStatistic>
    Включен: ${projectorStatistic.getBeginDate()}
    Выключен: ${projectorStatistic.getEndDate()}
    </#list>
</#if>