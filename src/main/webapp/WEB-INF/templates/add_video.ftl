

<#if error?has_content>
<b style="color: red">Error: ${error}</b><br/>
<#else>

    <#if projectors?has_content>
    <form action="/expositions/${exposition.id}/addVideo" method="post">

        <label class=" control-label">Проектор</label>
        <select multiple size="10" id="projectors_id" name="projectors_id">
            <#list projectors as p>
                <option value="${p.id}">${p.name}</option>
            </#list>
        </select>



        <label class=" control-label">Видео</label>
        <select multiple size="7" id="videos_id" name="videos_id">
            <#list videos as v>
                <option value="${v.id}">${v.name}</option>
            </#list>
        </select>


        <button  type="submit">Добавить</button>

    </form>

    <#else>
    <h3><a href="/expositions/${exposition.id}/edit">Добавьте проекторы в эксозицию</a></h3>
    </#if>
</#if>