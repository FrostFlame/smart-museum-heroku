<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<#assign c=JspTaglibs["http://www.springframework.org/security/tags"]>
<@sf.form action="/projector/create" method="post" modelAttribute="projectorForm">
    <@sf.input path="name" type="text" placeholder="Введите название"/>
    <@sf.errors path="name" cssClass="help-block"/>
<input type="submit" value="Создать">
</@sf.form>