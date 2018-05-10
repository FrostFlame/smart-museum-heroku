<#include "base.ftl">
<#macro title>
Добавить видео
</#macro>
<#macro content>
    <div class="page-header col-md-12 col-xs-12">
        <div class="col-md-11 col-xs-11">
            <h2><b>Добавить видео</b></h2>
        </div>
    </div>
    <div class="col-md-12">
        <div class="col-md-offset-1 col-md-10">
            <#if error?has_content>
                <b style="color: red">Error: ${error}</b>
            </#if>
            <h3>Добавить видеоклипы к проекторам</h3>
            <#if projectors?has_content>
                <form action="/expositions/${exposition.id}/addVideo" method="post">
                    <div class="form-group col-md-6">
                        <label for="boot-multiselect-video">Выберите видеоклипы, которые хотите добавить</label><br>
                        <select  id="boot-multiselect-video" multiple="multiple" multiple size="10" name="videos_id">
                            <#list videos as v>
                                <option value="${v.id}">${v.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="boot-multiselect-pro">Выберите проекторы, которые входят в экспозицию</label><br>
                        <select  id="boot-multiselect-pro" multiple="multiple" multiple size="10" name="projectors_id">
                            <#list projectors as p>
                                <option value="${p.id}">${p.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="col-md-offset-10">
                        <button type="submit" class="btn btn-info btn-lg btn-subm">Сохранить</button>
                    </div>
                </form>
            <#else>
                <h3><a href="/expositions/${exposition.id}/edit">Добавьте проекторы в эксозицию</a></h3>
            </#if>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#boot-multiselect-video').multiselect({
                includeSelectAllOption: true,
                buttonWidth: 400,
                dropRight: true,
                nonSelectedText: 'Выберите видеоклипы',
                maxHeight: 150
            });
        });

        $(document).ready(function() {
            $('#boot-multiselect-pro').multiselect({
                includeSelectAllOption: true,
                buttonWidth: 400,
                dropRight: true,
                nonSelectedText: 'Выберите проекторы',
                maxHeight: 150
            });
        });
    </script>
</#macro>

