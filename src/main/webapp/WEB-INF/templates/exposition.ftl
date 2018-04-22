<h3>Название экспозиции: ${exposition.name}</h3>
<h4>Проекторы</h4>
<#list exposition.projectors as projector>
    <div>
        <h5>${projector.name}</h5>
        <h5>Статус: ${projector.status}</h5>
    </div>
    <hr>
</#list>
<h4><a href="/expositions/${exposition.id}/edit">Редактировать экспозицию</a></h4>