${projector.getName()}
${projector.getStatus()}
<#if projectorVideos?has_content>
    <#list projectorVideos as projectorVideo>
    ${projectorVideo.getVideo().getName()}
    </#list>
<#else>
У проектора нет видео
</#if>