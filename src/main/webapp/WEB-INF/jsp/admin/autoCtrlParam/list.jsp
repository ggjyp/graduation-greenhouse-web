<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tld/datetag.tld" %>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en"> <!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>智能温室原型系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <link rel="stylesheet" type="text/css" href="/media/css/select2_metro.css"/>
    <link rel="stylesheet" href="/media/css/DT_bootstrap.css"/>
    <link href="/media/css/glyphicons.css" rel="stylesheet">
    <link href="/media/css/halflings.css" rel="stylesheet">
</head>
<body class="page-header-fixed">
<jsp:include page="../tool/top.jsp"></jsp:include>
<div class="page-container row-fluid">
    <jsp:include page="../tool/sidebar.jsp">
        <jsp:param name="class" value="control-auto-ctrl-param"></jsp:param>
    </jsp:include>
    <div class="page-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        自动控制参数管理
                        <small></small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/admin/index">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">自动控制参数管理</a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <div class="row-fluid">
                <a href="/admin/autoCtrlParam/add"
                   class="btn black">新增自动控制参数<i class="m-icon-swapright m-icon-white"></i></a>
                <br>
                <br>
            </div>
            <div class="row-fluid">

                <div class="span12">
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet box green">
                        <div class="portlet-title">
                            <div class="caption">自动控制参数管理
                                <small>(*共找到${count}条符合条件的记录)</small>
                            </div>
                            <div class="tools" style="display:none">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <table class="table table-striped table-bordered table-hover table-full-width"
                                   id="sample_1">
                                <thead>
                                <form method="post" action="/admin/autoCtrlParam/list">
                                    <tr style="vertical-align: middle">
                                        <td colspan="11" style="vertical-align: middle">
                                            <div class="dataTables_info paging_bootstrap pagination">
                                                <ul>
                                                    <li>
                                                        自动控制参数名：
                                                        <input type="text" value="" name="paramName"
                                                               style="width: 125px">
                                                    </li>
                                                    <li>
                                                        <input type="hidden" name="page" value="1">
                                                        <button type="submit"
                                                                style="padding: 5px 14px;vertical-align: top;"
                                                                class="btn green">搜索
                                                        </button>
                                                    </li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </form>
                                <tr>
                                    <th class="hidden-480">id</th>
                                    <th>自动控制参数名称</th>
                                    <th class="hidden-480">创建时间</th>
                                    <th class="hidden-480">修改时间</th>
                                    <th class="hidden-480">是否启用</th>
                                    <th class="hidden-480">详情</th>
                                    <th class="hidden-480">删除</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${records}" var="i">
                                    <tr>
                                        <td class="hidden-480">${i.id}</td>
                                        <td>
                                            <div class="success"></div>
                                            <a href="/admin/autoCtrlParam/detail?id=${i.id}">${i.paramName}</a>
                                        </td>
                                        <td class="hidden-480">
                                            <ex:date value="${i.createDate}" pattern="yyyy-MM-dd"></ex:date>
                                        </td>
                                        <td class="hidden-480">
                                            <ex:date value="${i.modifyDate}" pattern="yyyy-MM-dd"></ex:date>
                                        </td>
                                        <td class="hidden-480">
                                            <c:if test="${i.isUsing=='0'}">
                                                <button class="btn mini red toUsing"
                                                        data-id="${i.id}"
                                                        data-tempMin="${i.temperatureMin}" data-tempMax="${i.temperatureMax}"
                                                        data-humMin="${i.humidityMin}" data-humMax="${i.humidityMax}"
                                                        data-lightMin="${i.lightIntensityMin}" data-lightMax="${i.lightIntensityMax}"
                                                        data-soilMin="${i.soilMoistureMin}" data-soilMax="${i.soilMoistureMax}"
                                                >
                                                    <i class="icon-remove"></i>未启用
                                                </button>
                                                <%--<i class=" icon-remove toUsing" data-id="${i.id}"></i>--%>
                                            </c:if>
                                            <c:if test="${i.isUsing=='1'}">
                                                <button class="btn mini green toUsing"
                                                   data-id="${i.id}" >
                                                    <i class="icon-ok" aria-readonly="true"></i>已启用
                                                </button>
                                                <%--<i class=" icon-ok toUsing" data-id="${i.id}" aria-readonly="true"></i>--%>
                                            </c:if>
                                        </td>
                                        <td class="hidden-480">
                                            <a href="/admin/autoCtrlParam/detail?id=${i.id}"
                                               class="btn mini purple">
                                                <i class="icon-edit"></i>详情
                                            </a>
                                        </td>
                                        <td class="hidden-480">
                                            <button class="btn mini black delbtn"
                                                    data-id="${i.id}"><i class="icon-trash"></i> 删除
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <div class="span6" style="width:100%;">
                                    <div class="dataTables_info paging_bootstrap pagination pull-right">
                                        <ul>

                                            <li class="prev <c:if test="${page=='1'}">disabled</c:if>">
                                                <a onclick="gotopage('${page-1}')">← <span
                                                        class="hidden-480">上一页</span></a>
                                            </li>

                                            <li class="active">
                                                <a>${page}</a>
                                            </li>
                                            <li class="next <c:if test="${maxpage<=page}">disabled</c:if>">
                                                <a onclick="gotopage('${page+1}')"><span
                                                        class="hidden-480">下一页</span> → </a>
                                            </li>
                                            <li style="margin-left: 5px">
                                            <li style="vertical-align: top;line-height: 30px;">
                                                ${page}/${maxpage}</li>
                                            </li>
                                            <li>
                                                <input style="width: 30px;" type="number" min="1" value="${page}"
                                                       id="pageinput" max="${maxpage}"/>
                                            <li style="vertical-align: top;line-height: 30px;">页</li>
                                            </li>
                                            <li>
                                                <button type="button"
                                                        style="padding: 5px 14px;vertical-align: top;"
                                                        class="btn green"
                                                        onclick="javascript:gotopage($('#pageinput').val()+'')">
                                                    前往
                                                </button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                </tfoot>
                            </table>
                        </div>
                    </div>

                    <!-- END EXAMPLE TABLE PORTLET-->
                </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->
        </div>


        <!-- END PAGE CONTENT-->

    </div>
</div>
<jsp:include page="../tool/footer.jsp"></jsp:include>
<script>
    $(".delbtn").click(function () {
        var id = $(this).attr("data-id");
        if (confirm("确认删除?")) {
            $.ajax({
                type: "post",
                url: "/admin/autoCtrlParam/delete",
                dataType: "json",
                timeout: 200000,
                data: {
                    id: id
                },
                success: function (data) {
                    if (data.state == '00000') {
                        domethod(pop("删除成功"), 1000)
                    }
                    else {
                        notice(data.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    notice("网络异常");
                }
            });
        }
    });

    $(".toUsing").click(function () {
        var id = $(this).attr("data-id");
        var tempMin = $(this).attr("data-tempMin");
        var tempMax = $(this).attr("data-tempMax");
        var humMin = $(this).attr("data-humMin");
        var humMax = $(this).attr("data-humMax");
        var lightMin = $(this).attr("data-lightMin");
        var lightMax = $(this).attr("data-lightMax");
        var soilMin = $(this).attr("data-soilMin");
        var soilMax = $(this).attr("data-soilMax");
        if (confirm("确认启用此自动控制参数?")){
            var message = {
                "type" : "autoCtrlParam",
                "tmin":parseFloat(tempMin),
                "tmax" : parseFloat(tempMax),
                "hmin": parseFloat(humMin),
                "hmax" : parseFloat(humMax),
//                "lmin": parseFloat(lightMin),
//                "lmax" : parseFloat(lightMax),
//                "smin": parseFloat(soilMin),
//                "smax" : parseFloat(soilMax)
            }
            var dataSend = {
                "updateState" : "false",
                "initiator" : "REST",
                "initiatorId" : "admin",
                "target" : "Assignment",
                "commandToken" : "e8b9b58e-10ea-4b76-b18b-c57c4a4ebd69",
                "parameterValues" : {
                    "message" : JSON.stringify(message)
                }
            };
            $.ajax({
                type: "POST",
                contentType: "application/json;charset=utf-8",
                url: "http://localhost:8080/sitewhere/api/assignments/83d43843-c4a7-403c-83ac-9dc0d1918aba/invocations",
                data:JSON.stringify(dataSend),
                dataType: "json",
                beforeSend: function(request){
                    request.setRequestHeader("Content-Type","application/json");
                    request.setRequestHeader("X-SiteWhere-Tenant","sitewhere1234567890");
                    request.setRequestHeader("Authorization","Basic YWRtaW46cGFzc3dvcmQ=");
                },
                success: function () {
                    $.ajax({
                        type: "post",
                        url: "/admin/autoCtrlParam/use",
                        dataType: "json",
                        data: {id: id},
                        timeout: 200000,
                        success: function (data) {
                            if (data.state == '00000') {
                                domethod(pop("操作成功"), 1000)
                            }
                            else {
                                notice(data.msg);
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            notice("网络异常");
                        }
                    });
                },
                error: function () {
                    notice("操作失败");
                }
            });
        }

    });

    function gotopage(page) {
        if (parseInt(page) <= 0 || parseInt(page) >=${maxpage+1})
            return;
        location.href = "/admin/log/list?paramName=${paramName}&page=" + page;
    }
</script>


</body>

<!-- END BODY -->

</html>
