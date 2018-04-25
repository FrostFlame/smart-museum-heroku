<#include "base.ftl">
<#macro title>
Проектор ${projector.getName()}
</#macro>
<#macro content>
${projector.getName()}
${projector.getStatus()}
<br/>
<#if error?has_content>
<b style="color: red">Error: ${error}</b>
<br/>
</#if>
<form action="/projector/${projector.getId()}/turn_on" method="post">
    <input type="submit" value="Включить">
</form>
<form action="/projector/${projector.getId()}/turn_off" method="post">
    <input type="submit" value="Выключить">
</form>
<#if projectorVideos?has_content>
    <#list projectorVideos as projectorVideo>
    ${projectorVideo.getVideo().getName()}
    <form action="/projector/${projector.id}/modifyVideo?video_id=${projectorVideo.video.id}" method="post">
        <input type="text"  id="num" name="num" value="${projectorVideo.num}" />
        <input type="submit"  value="Изменить" />
    </form>
    <form action="/projector/${projector.id}/deleteVideo?video_id=${projectorVideo.video.id}" method="post">
        <input type="submit"  value="Удалить" />
    </form>
    <br/>
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
</#macro>