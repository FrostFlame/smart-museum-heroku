<#include "base.ftl">
<#macro title>
Проектор ${projector.getName()}
</#macro>
<#macro content>
<div class="page-header col-md-12 col-xs-12">
    <div class="col-md-10 col-xs-10">
        <h2><b>${projector.getName()}</b></h2>
    </div>
</div>
<div class="col-md-12 col-xs-12">
    <div class="row">
        <div class="col-xs-12 col-sm-4 text">
            <h4><b>Статус</b></h4>
            <p>
            <form action="/projector/${projector.id}/<#if projector.status == 'D'>turn_on<#else>turn_off</#if>"
                  method="post">
                <button type="submit" class="btn btn-dark"
                        <#if projector.status == 'F'>disabled</#if>><#if projector.status == 'E'>Выключить<#else>
                    Включить</#if></button>
            </form>
            </p>
        </div>
        <div class="col-xs-12 col-sm-4 text">
            <h4><b>Время работы</b></h4>
            <p>
                <b>${projector.getCustomSumTime()}</b> с
            </p>
        </div>
        <div class="col-xs-12 col-sm-4 text">
            <h4><b>Исправность</b></h4>
            <p>
            <form action="/projector/${projector.id}/fault" method="post">
                <#if projector.status == 'F'>
                    <button type="submit" class="btn btn-warning"><span
                            class="glyphicon glyphicon-exclamation-sign"></span>
                        Неисправен
                    </button>
                <#else>
                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span>
                        Исправен
                    </button>
                </#if>
            </form>
            </p>
        </div>
    </div>
    <div class="col-md-12">
        <h3>Список видеоклипов</h3>
        <#if projectorVideos?has_content>
            <#list projectorVideos as projectorVideo>
                <div class="list-group">
                    <a class="list-group-item list-group-item-action">
                    ${projectorVideo.getVideo().getName()}
                        <@security.authorize access="hasAnyRole('MANAGER','ADMIN')">
                            <form action="/projector/${projector.id}/modifyVideo?video_id=${projectorVideo.video.id}"
                                  method="post">
                                <input type="text" id="num" name="num" value="${projectorVideo.num}"/>
                                <input type="submit" value="Изменить"/>
                            </form>

                            <form action="/projector/${projector.id}/deleteVideo?video_id=${projectorVideo.video.id}"
                                  method="post">
                                <input type="submit" value="Удалить"/>
                            </form>
                        </@security.authorize>
                    </a>
                </div>
            </#list>
        <#else>
            <div class="list-group">
                <a class="list-group-item list-group-item-action">Нет видеоклипов, прикрепленных к проектору</a>
            </div>
        </#if>
        <#if projectorStatistics?has_content>
            <#list projectorStatistics as projectorStatistic>
                <div class="list-group">
                    <a class="list-group-item list-group-item-action">
                        Включен: ${projectorStatistic.getBeginDate()}
                        <#if projectorStatistic.getEndDate()?has_content>Выключен: ${projectorStatistic.getEndDate()}
                        <#else>
                            Еще работает
                        </#if>
                    </a>
                </div>
            </#list>
        <#else>
            <div class="list-group">
                <a class="list-group-item list-group-item-action">Нет статистики</a>
            </div>
        </#if>
    </div>
    <script type="application/javascript">

    </script>
</#macro>