<#include "base.ftl">
<#macro title>
Экспозиций не найдено
</#macro>
<#macro content>
Экспозиций не найдено
<b><a href="/expositions/add">Создать экспозицию</a></b>
</#macro>

<script>
    $(document).ready(function () {
        $("li.active").removeClass('active');
        $("#expositions").addClass('active');
    })
</script>