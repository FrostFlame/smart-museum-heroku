<#include "base.ftl">
<#macro title>Расписание</#macro>
<#macro content>
<div class="row" id="schedule-content">
    <div class="col-xs-3 col-md-3">
        <div class="panel panel-default">
            <div class="panel-heading">Экспозиции</div>
            <ul class="nav flex-column">
                <#if expositions?has_content>
                    <#list expositions as e>
                            <div class="panel-body <#if exposition.id==e.id>active</#if>"><a  href="/playing_schedule/${e.id}"><span class="glyphicon glyphicon-blackboard"></span>&nbsp;${e.name}</a><br></div>
                    </#list>
                <#else><p>Нет доступных экспозиций</p>
                </#if>
            </ul>
        </div>
    </div>
    <div class="col-xs-9 col-md-9">
        <div class="panel-group">
            <#if projectors?has_content>
                <div class="panel panel-default">
                <div class="panel-heading">Опции сортировки</div>
                <div class="panel-body">
                    <form class="schedule-form" action="/playing_schedule/${exposition.id}" method="get">
                        <div class="row">
                            <div class="col-md-3 col-xs-3">
                                <select multiple class="form-control selectpicker" id="projectorsId" name="projectors_id">
                                    <option value="" selected disabled>Проектор</option>
                                    <#list projectors as p>
                                        <option value="${p.id}">${p.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <select multiple class="form-control selectpicker" id="weekDaysId" name="weekDays_id">
                                    <option value="" selected disabled>День недели</option>
                                    <#list weekDays as w>
                                        <option value="${w.id}">${w.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <select class="form-control selectpicker" id="sort" name="sort">
                                    <option value="" selected disabled>Сортировка по</option>
                                    <#if  (sort!"") = "projectors" >
                                        <option  value="projectors">Проекторы</option>
                                        <option  value="dayWeeks">Дни недели</option>
                                    <#else>
                                        <option  value="dayWeeks">Дни недели</option>
                                        <option  value="projectors">Проекторы</option>
                                    </#if>
                                </select>
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <button class="btn btn-default" type="submit">Применить</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="panel panel-default" id="active-tasks-panel">
                <div class="panel-heading">Активные задачи</div>
                <@security.authorize access="hasRole('ADMIN')">
                <div class="panel-body">
                    <a href="/playing_schedule/${exposition.id}/add" class="btn btn-default" type="submit">Добавить задачу</a>
                </div>
                </@security.authorize>
                <#if playingSchedule?has_content>
                    <div class="panel-body">
                        <div class="row" align="center">
                            <div class="col-md-2 col-xs-2"><b>Проектор</b></div>
                            <div class="col-md-2 col-xs-2"><b>День недели</b></div>
                            <div class="col-md-3 col-xs-3"><b>Время включения</b></div>
                            <div class="col-md-3 col-xs-3"><b>Время выключения</b></div>
                            <@security.authorize access="hasRole('ADMIN')">
                            <div class="col-md-2 col-xs-2"><b>Удаление</b></div>
                            </@security.authorize>
                        </div>
                    </div>
                    <#list playingSchedule as p>
                        <div class="panel-body">
                            <div class="row" align="center">
                                <div class="col-md-2 col-xs-2"><a href="/projector/${p.projector.id}" >${p.projector.name}</a></div>
                                <div class="col-md-2 col-xs-2">${p.weekDay.name}</div>
                                <div class="col-md-3 col-xs-3">${p.beginTime}</div>
                                <div class="col-md-3 col-xs-3">${p.endTime}</div>
                                <@security.authorize access="hasRole('ADMIN')">
                                <div class="col-md-2 col-xs-2">
                                    <form class="delete-task-button" action="/playing_schedule/${exposition.id}/delete?id=${p.id}&page=${page}" method="post">
                                        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-trash"></span> Удалить</button>
                                    </form>
                                </div>
                                </@security.authorize>
                            </div>
                        </div>
                    </#list>
                    <div class="text-center">
                        <ul class = "pagination justify-content-center">
                            <li class="page-item <#if page == 0>disabled</#if>">
                                <a class="page-link" href="<#if page == 0>#<#else>/playing_schedule/${exposition.id}/goToAnotherPage?page=0</#if>">
                                    Первая
                                </a>
                            </li>
                            <li class="page-item <#if (page-1) lt 0>disabled</#if>">
                                <a class="page-link" href="<#if (page-1) lt 0>#<#else>/playing_schedule/${exposition.id}/goToAnotherPage?page=${page-1}</#if>" aria-label="Предыдущая">
                                    <span aria-hidden="true">«</span>
                                    <span class="sr-only">Предыдущая</span>
                                </a>
                            </li>

                            <#list pages as p>
                                <li class="page-item <#if page == p>active</#if>">
                                    <a class="page-link"  href="/playing_schedule/${exposition.id}/goToAnotherPage?page=${p}">${p}</a>
                                </li>
                            </#list>
                            <li class="page-item  <#if (page+1) gt lastPage>disabled</#if>">
                                <a class="page-link" href="<#if (page+1) gt lastPage>#<#else>/playing_schedule/${exposition.id}/goToAnotherPage?page=${page+1}</#if>" aria-label="Следующая">
                                    <span aria-hidden="true">»</span>
                                    <span class="sr-only">Следующая</span>
                                </a>
                            </li>
                            <li class="page-item <#if page == lastPage>disabled</#if>">
                                <a class="page-link" href="<#if page == lastPage>#<#else>/playing_schedule/${exposition.id}/goToAnotherPage?page=${lastPage}</#if>">
                                    Последняя
                                </a>
                            </li>
                        </ul>
                    </div>
                <#else>
                    <div class="panel-body">
                        <p>Нет активных задач</p>
                    </div>
                </#if>
            </div>
            <#else>
            <h3><a href="/expositions/${exposition.id}/edit">Добавьте проекторы в эксозицию</a></h3>
            </#if>
        </div>
    </div>
</div>
</#macro>

<script>
    $(document).ready(function () {
        $("#schedule").addClass('active');
    })
</script>
