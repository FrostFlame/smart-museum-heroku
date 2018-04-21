

<#if error?has_content>
<b style="color: red">Error: ${error}</b><br/>
<#else>
    <#if expositions?has_content>
    <b>Экспозиции</b><br>
    <#list expositions as e>
    <b><a href="/playing_schedule/${e.id}">${e.name}</a></b>
    <br>
    </#list>
    </#if>



    <#if projectors?has_content>
    <form action="/playing_schedule/${exposition.id}/add" method="get">
        <input type="submit"  value="Добавить новое расписание" />
    </form>
    <form  action="/playing_schedule/${exposition.id}" method="get" >

            <label class=" control-label">Проектор</label>
            <select multiple size="10" id="projectorsId" name="projectors_id">
                <#list projectors as p>
                    <option value="${p.id}">${p.name}</option>
                </#list>
            </select>



            <label class=" control-label">День недели</label>
            <select multiple size="7" id="weekDaysId" name="weekDays_id">
                <#list weekDays as w>
                    <option value="${w.id}">${w.name}</option>
                </#list>
            </select>


        <label class=" control-label">Сортировка по:</label>
        <select id="sort" name="sort" class="form-control">
                <option  value="dayWeeks">Дни недели</option>
                <option  value="projectors">Проекторы</option>
        </select>


            <button  type="submit">Применить</button>

    </form>


    <table>
    <thead>
        <tr>
            <th>
                Проектор
            </th>
            <th>
                День недели
            </th>
            <th>
                Время включения
            </th>
            <th>
                Время выключения
            </th>
            <th>
                Удалить
            </th>
        </tr>
    </thead>
    <#list playingSchedule as p>
    <tbody>
        <tr>
            <td><a href="#" >${p.projector.name}</a></td>
            <td>${p.weekDay.name}</td>
            <td>${p.beginTime}</td>
            <td>${p.endTime}</td>
            <td><form action="/playing_schedule/${exposition.id}/delete?id=${p.id}" method="post">
                <input type="submit"  value="Удалить" /></td>
                </form>
            </td>
        </tr>
    </tbody>
    <#else>
    Не нашлось ни одного расписания
    </#list>
    </table>

    <#else>
    <h3><a href="#">Добавьте проекторы в эксозицию</a></h3>
    </#if>
</#if>
