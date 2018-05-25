<form action="/registration" method="post">
    <label for="name">Имя</label>
    <input name="name">
    <label for="surname">Фамилия</label>
    <input name="surName">
    <label for="thirdname">Отчество</label>
    <input name="thirdName">
    <label for="login">Логин</label>
    <input name="login">
    <select name="position">
        <#list positions as item>
            <option value="${item.id}">${item.name}</option>
        </#list>
    </select>
    <button type="submit">Зарегистрировать</button>
</form>