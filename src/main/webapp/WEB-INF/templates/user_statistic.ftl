

<form action="/user_statistic/search" method="get">

    <label class=" control-label">Поиск</label>
    <input type="text" name="searchField">

    <label class=" control-label">Пользователь</label>
    <select multiple size="10" id="users" name="users">
        <#list users as u>
            <option value="${u.id}">${u.login}</option>
        </#list>
    </select>



    <label class=" control-label">Действие</label>
    <select multiple size="7" id="actions" name="actions">
        <#list actions as a>
            <option value="${a.id}">${a.name}</option>
        </#list>
    </select>


    <label class=" control-label">Сущность</label>
    <select multiple size="6" id="entities" name="entities">
    <#list entities as e>
        <option value="${e.id}">${e.name}</option>
    </#list>
    </select>




    <button  type="submit">Применить</button>

</form>



<table>
    <thead>
    <tr>
        <th>
            Пользователь
        </th>
        <th>
            Дата
        </th>
        <th>
            Действие
        </th>
        <th>
            Сущность
        </th>
        <th>
            id
        </th>
    </tr>
    </thead>
<#list user_statistic as u>
    <tbody>
    <tr>
        <td><a href="#" >${u.user.login}</a></td>
        <td>${u.datetime}</td>
        <td>${u.actionType.name}</td>
        <td>${u.tableName.name}</td>
        <#if u.link?has_content>
            <td><a href="${u.link}${u.tableid}" >${u.tableid}</a></td>
        <#else>
            <td>${u.tableid}</td>
        </#if>

    </tr>
    </tbody>
<#else>
    Не нашлось ни одного элемента
</#list>
</table>