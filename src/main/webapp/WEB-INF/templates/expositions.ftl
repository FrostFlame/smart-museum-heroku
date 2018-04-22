<h3>Список экспозиций</h3>
<#list expositions as exposition>
    <a href="/expositions/${exposition.id}">${exposition.name}</a>
    <form action="/expositions/${exposition.id}/delete" method="post">
        <input type="submit" value="Удалить">
    </form>
<hr>
</#list>