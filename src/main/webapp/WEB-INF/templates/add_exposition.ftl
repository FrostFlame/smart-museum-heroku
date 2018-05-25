<#include "base.ftl">
<#macro title>
Добавление экспозиции
</#macro>
<#macro content>
    <#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
    <#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
<div class="page-header col-md-12 col-xs-12">
    <div class="col-md-11 col-xs-11">
        <h2><b>Новая экспозиция</b></h2>
    </div>
</div>
        <div class="col-md-12">
            <div class="col-md-offset-1 col-md-10">
                <h3>Создать новую экспозицию</h3>
<@sf.form action="/expositions/add" method="post" modelAttribute="form">
      <fieldset>

          <div class="form-group">
              <label for="formControlInput1">Название экспозиции</label>
    <@sf.input type="text" path="name" class="form-control" id="formControlInput1"
    placeholder="Например, экспозиция №1" required="true"/>
              <#if error?has_content>
                  <b style="color: red">Error: ${error}</b>
                  <br/>
              </#if>
          </div>
          <div class="form-group">
              <label for="boot-multiselect-demo">Выберите проекторы, которые входят в экспозицию</label><br>
              <@sf.select path="projectorsId" id="boot-multiselect-demo" multiple="multiple">

                  <#list projectors as projector>
                      <@sf.option value="${projector.id}">${projector.name}</@sf.option>
                  </#list>
              </@sf.select>
          </div>
          <div class="col-md-offset-10">
              <input type="submit" class="btn btn-info btn-lg btn-subm" value="Сохранить"/>
          </div>
      </fieldset>
</@sf.form>
                <script type="text/javascript">
                    $(document).ready(function () {
                        $('#boot-multiselect-demo').multiselect({
                            includeSelectAllOption: true,
                            buttonWidth: 400,
                            dropRight: true,
                            nonSelectedText: 'Выберите проекторы',
                            maxHeight: 150
                        });
                    });
                </script>
</#macro>