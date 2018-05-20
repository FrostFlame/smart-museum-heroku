<#include "base.ftl"/>
<html lang="en">
<div class="row">
    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">Опции</div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-2 col-xs-2">
                        <select class="form-control selectpicker">
                            <option active>Проектор</option>
                            <option>first</option>
                            <option>second</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-xs-2">
                        <select class="form-control selectpicker">
                            <option active>День недели</option>
                            <option>Пн</option>
                            <option>Вт</option>
                            <option>Ср</option>
                            <option>Чт</option>
                            <option>Пт</option>
                            <option>Сб</option>
                            <option>Вс</option>
                        </select>
                    </div>
                    <div class="col-md-3 col-xs-3">
                        <select class="form-control selectpicker">
                            <option active>Сортировка по</option>
                            <option>Дни недели</option>
                            <option>???????</option>
                        </select>
                    </div>
                    <div class="col-md-2 col-xs-2">
                        <button type="button">Применить</button>
                    </div>
                    <div class="col-md-3 col-xs-3">
                        <button type="button">Добавить расписание</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">Активные задачи</div>
            <div class="panel-body">
                <div class="row" align="center">
                    <div class="col-md-2 col-xs-2"><b>Проектор</b></div>
                    <div class="col-md-2 col-xs-2"><b>День недели</b></div>
                    <div class="col-md-3 col-xs-3"><b>Время включения</b></div>
                    <div class="col-md-3 col-xs-3"><b>Время выключения</b></div>
                    <div class="col-md-2 col-xs-2"><b>Удаление</b></div>
                </div>
            </div>
            <div class="panel-body">
                <div class="row" align="center">
                    <div class="col-md-2 col-xs-2"><a href="#">first</a></div>
                    <div class="col-md-2 col-xs-2">Пн</div>
                    <div class="col-md-3 col-xs-3">9:00</div>
                    <div class="col-md-3 col-xs-3">19:00</div>
                    <div class="col-md-2 col-xs-2"><button class="btn btn-default" type="button"><span class="glyphicon glyphicon-trash"></span> Удалить</button></div>
                </div>
            </div>
            <div class="panel-body">
                <div class="row" align="center">
                    <div class="col-md-2 col-xs-2"><a href="#">second</a></div>
                    <div class="col-md-2 col-xs-2">Вт</div>
                    <div class="col-md-3 col-xs-3">9:00</div>
                    <div class="col-md-3 col-xs-3">19:00</div>
                    <div class="col-md-2 col-xs-2"><button class="btn btn-default" type="button"><span class="glyphicon glyphicon-trash"></span> Удалить</button></div>
                </div>
            </div>
            <div class="panel-body">
                <div class="row" align="center">
                    <div class="col-md-2 col-xs-2"><a href="#">first</a></div>
                    <div class="col-md-2 col-xs-2">Ср</div>
                    <div class="col-md-3 col-xs-3">9:00</div>
                    <div class="col-md-3 col-xs-3">19:00</div>
                    <div class="col-md-2 col-xs-2"><button class="btn btn-default" type="button"><span class="glyphicon glyphicon-trash"></span> Удалить</button></div>
                </div>
            </div>
            <div class="panel-body">
                <div class="row" align="center">
                    <div class="col-md-2 col-xs-2"><a href="#">second</a></div>
                    <div class="col-md-2 col-xs-2">Чт</div>
                    <div class="col-md-3 col-xs-3">9:00</div>
                    <div class="col-md-3 col-xs-3">19:00</div>
                    <div class="col-md-2 col-xs-2"><button class="btn btn-default" type="button"><span class="glyphicon glyphicon-trash"></span> Удалить</button></div>
                </div>
            </div>
        </div>
    </div>
</div>
</html>