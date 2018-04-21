<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
<@sf.form action="/expositions/add" method="post" modelAttribute="form">
      <fieldset>
          <label class=" control-label">Введите название экспозиции</label>
          <br>
          <@sf.input type="text" path="name" placeholder="Введите название..."/>
          <br><br>
          <label class=" control-label">Добавьте проекторы</label>
          <br>
        <@sf.select multiple="true" path="projectorsId">
        <#list projectors as projector>
            <@sf.option value="${projector.id}">${projector.name}</@sf.option>
        </#list>
        </@sf.select>
            <br>
            <br>
          <input type="submit" value="Добавить экспозицию"/>
      </fieldset>
</@sf.form>
