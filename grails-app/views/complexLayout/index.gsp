<%--
  Created by IntelliJ IDEA.
  User: master
  Date: 12.02.16
  Time: 0:12
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Домашняя аптечка для своих</title>
    <link rel="stylesheet" type="text/css" href="/assets/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/assets/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/assets/demo.css">
    <script type="text/javascript" src="/assets/jquery.min.js"></script>
    <script type="text/javascript" src="/assets/jquery.easyui.min.js"></script>
</head>

<body>
<h2>Домашняя аптечка для своих</h2>

<p>Приветствуем Вас на нашем сайте</p>

<div style="margin:20px 0;"></div>

<div class="easyui-layout" style="width:700px;height:350px;">
    <div data-options="region:'north'" style="height:50px"></div>

    <div data-options="region:'south',split:true" style="height:50px;">
        <p>Присоединяйтесь, не самая плохая компания :)</p>
    </div>

    <div data-options="region:'east',split:true" title="Для Вас" style="width:180px;">
        %{--<ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true,dnd:true">--}%
        <p>На нашем сайте вы можете описать содержимое Вашей домашней аптечки.</br>
            В дальнейшем, поддерживая в актуальном состоянии это описание, Вы сможете предоставить доступ к
            нему своим родным, друзьям, знакомым.  Получив подобный доступ к аптечкам наших пользователей,
            Вы сможете в любой момент попробовать найти в них нужный Вам препарат и договориться о его получении.
        </p>
        %{--</ul>--}%
    </div>

    <div data-options="region:'west',split:true" title="Выбирайте" style="width:150px;">
        <div class="easyui-accordion" data-options="fit:true,border:false">
            <div title="Ваша аптечка" style="padding:10px;">
                Актуальный список лекарств и препаратов Вашей аптечки
            </div>

            <div title="Справочники" data-options="selected:true" style="padding:10px;">
                Справочники для описания лекарств
            </div>

            <div title="Доп. информация" style="padding:10px">
                Как позволить всем увидеть Вашу аптечку
            </div>

            <div data-options="region:'center',title:'Другие аптечки',iconCls:'icon-search'" style="padding:10px">
                Содержимое доступных Вам аптечек
            </div>
        </div>
    </div>

    <div data-options="region:'center',title:'Название выбранного раздела',iconCls:'icon-tip'">
        <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
            %{--<div title="О разделе" data-options="href:'_content.html'" style="padding:10px"></div>--}%
            <div title="О разделе" style="padding:10px"></div>

            <div title="Список лекарств" style="padding:5px">
                <table class="easyui-datagrid" id="dg"
                       data-options="url:'supplies/getFull',method:'get',singleSelect:true,fit:true,fitColumns:true">
                    <thead>
                    <tr>
                        %{--<th data-options="field:'title'" width="200">Наименоване</th>--}%
                        <th field="title" width="250">Наименоване</th>
                        %{--<th data-options="field:'structure'" width="100">Вариант</th>--}%
                        %{--<th data-options="field:'amountType',align:'right'" width="100">Фасовка</th>--}%
                        %{--<th data-options="field:'capacity',align:'right'" width="100">Ёмкость</th>--}%
                        %{--<th data-options="field:'amount'" width="100">Количество</th>--}%
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>
    $(document).ready(function () {
//        $('#dg').datagrid('reload');
//        $('#dg').datagrid('load', {
//            load: function () {
//              alert()
//            }
//        });
    });
</script>
</body>
</html>