<h3>${exposition.name}</h3>
<h4>Проекторы</h4>
<#list exposition.projectors as projector>
    <div>
        <h5>Название экспозиции: ${projector.name}</h5>
        <h5>Статус: ${projector.status}</h5>
    </div>
    <hr>
</#list>