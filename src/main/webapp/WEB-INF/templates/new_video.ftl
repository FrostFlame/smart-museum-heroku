<#include "base.ftl">
<#macro title>
Новое видео
</#macro>
<#macro content>
    <div class="page-header col-md-12 col-xs-12">
        <div class="col-md-11 col-xs-11">
            <h2><b>Новый видеоклип</b></h2>
        </div>
    </div>
    <div class="col-md-12">
        <div class="col-md-offset-1 col-md-10">
            <#if error?has_content>
                <b style="color: red">Error: ${error}</b>
                <br/>
            </#if>
            <h3>Добавить новый видеоклип</h3>
            <form method="POST" action="/videos/add" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="formControlInput1">Название видеоклипа</label>
                    <input required type="text"  name="name" class="form-control" id="formControlInput1"
                           placeholder="Например, видеоклип №120">
                </div>
                <div class="form-group">
                    <label for="formFile">Добавление файла</label>
                    <input required type="file" name="file" id="formFile">
                </div>
                <div class="col-md-offset-10">
                    <button type="submit" class="btn btn-info btn-lg btn-subm">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</#macro>





