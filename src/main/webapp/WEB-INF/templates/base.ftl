<#macro page_head>
  <title>Base</title>
<meta charset="UTF-8">
    <link rel="stylesheet" href="/webapp/static/css/common.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</#macro>

<#macro page_body>
<div class="row">

    <div class="header-smart col-xs-8">
        <h1>Smart museum</h1>
    </div>
    <a href="#"><span class="glyphicon glyphicon-user col account"></span></a>
    <a class="col" href="#">  Выход</a>
    <div class="container">
        <div class="row">
            <div class="col-md-3" >

                <ul class="nav nav-tabs nav-stacked left-menu">
                    <li class="active">
                        <a href="#">Главная</a>
                    </li>
                    <li>
                        <a href="#">Первый этаж</a>
                    </li>
                    <li>
                        <a href="#">Второй этаж</a>
                    </li>
                    <li>
                        <a href="#">Третий этаж</a>
                    </li>
                    <li>
                        <a href="#">Четвертый этаж</a>
                    </li>
                </ul>
            </div>
            <div class="col-md-8">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span> Главная</a></li>


                </ul>
            </div>



            <div>

            </div>
        </div>
    </div>
</div>



</#macro>


<#macro display_page>
  <!doctype html>
  <html lang="en">
  <head>
      <@page_head/>
  </head>
  <body>
      <@page_body/>
  </body>
  </html>
</#macro>