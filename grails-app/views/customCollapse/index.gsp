<%--
  Created by IntelliJ IDEA.
  User: master
  Date: 10.02.16
  Time: 22:58
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ваша домашняя аптечка</title>
    <link rel="stylesheet" type="text/css" href="/assets/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/assets/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/assets/demo.css">
    <script type="text/javascript" src="/assets/jquery.min.js"></script>
    <script type="text/javascript" src="/assets/jquery.easyui.min.js"></script>
</head>
<body>
<h2>Ваша домашняя аптечка</h2>
<p>Это пробый вариант оформления</p>
<div style="margin:20px 0;"></div>
<div id="cc" class="easyui-layout" style="width:700px;height:350px;">
    <div data-options="region:'east',split:true,hideCollapsedContent:false" title="East" style="width:100px;"></div>
    <div data-options="region:'west',split:true,collapsed:true,
                hideExpandTool: true,
                expandMode: null,
                hideCollapsedContent: false,
                collapsedSize: 68,
                collapsedContent: function(){
                    return $('#titlebar');
                }
                " title="West" style="width:100px;"></div>
    <div data-options="region:'center',title:'Название раздела'">
        <table class="easyui-datagrid"
               data-options="url:'datagrid_data1.json',method:'get',border:false,singleSelect:true,fit:true,fitColumns:true">
            <thead>
            <tr>
                <th data-options="field:'itemid'" width="80">Item ID</th>
                <th data-options="field:'productid'" width="100">Product ID</th>
                <th data-options="field:'listprice',align:'right'" width="80">List Price</th>
                <th data-options="field:'unitcost',align:'right'" width="80">Unit Cost</th>
                <th data-options="field:'attr1'" width="150">Attribute</th>
                <th data-options="field:'status',align:'center'" width="60">Status</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<div id="titlebar" style="padding:2px">
    <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'layout-button-right'" onclick="$('#cc').layout('expand','west')"></a>
    <a href="javascript:load1()" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-picture',size:'large',iconAlign:'top'">Фото</a>
    <a href="javascript:alert(0)" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-shapes',size:'large',iconAlign:'top'">Фасовка</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-smartart',size:'large',iconAlign:'top'">Описания</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" data-options="iconCls:'icon-large-chart',size:'large',iconAlign:'top'">Остатки</a>
</div>
<script type="text/javascript">
    $(function(){
        load1();
    });

    function load1(){
        $('#pg').pivotgrid({
            url:'pivotgrid_data1.json',
            method:'get',
            pivot:{
                rows:['Country','Category'],
                columns:['Color'],
                values:[
                    {field:'Price',op:'sum'},
                    {field:'Discount',op:'sum'}
                ]
            },
            forzenColumnTitle:'<span style="font-weight:bold">Pivot Grid</span>',
            valuePrecision:0,
            valueStyler:function(value,row,index){
                if (/Discount$/.test(this.field) && value>100 && value<500){
                    return 'background:#D8FFD8'
                }
            }
        })
    }
    function load2(){
        $('#pg').pivotgrid({
            url:'pivotgrid_data2.json',
            method:'get',
            pivot:{
                rows:['form','name'],
                columns:['year'],
                values:[
                    {field:'gdp'},
                    {field:'oil'},
                    {field:'balance'}
                ]
            },
            valuePrecision:3,
            valueStyler:function(value,row,index){
                if (/balance$/.test(this.field) && value<0){
                    return 'background:pink'
                }
            }
        })
    }
</script>
</body>
</html>