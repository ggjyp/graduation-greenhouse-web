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
<jsp:include page="../../tool/top.jsp"></jsp:include>
<div class="page-container row-fluid">
    <jsp:include page="../../tool/sidebar.jsp">
        <jsp:param name="class" value="control-hardware-controller"></jsp:param>
    </jsp:include>
    <div class="page-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        温室硬件控制
                        <small></small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/admin/index">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">温室硬件控制</a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <div class="row-fluid">

                <div class="span6">
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet box green">
                        <div class="portlet-title">
                            <div class="caption">温室硬件控制</div>
                            <div class="tools" style="display:none">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <input type="hidden" id="currentUser" value="${sessionScope.get('admin_name')}">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th class="hidden-480">硬件名称</th>
                                    <th class="hidden-480">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td class="hidden-480">LED</td>
                                    <td class="hidden-480">
                                        <button type="button" class="btn btn-circle green" onclick="openLED()">打开</button>
                                        <button type="button" class="btn btn-circle red" onclick="closeLED()">关闭</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="hidden-480">水阀门</td>
                                    <td class="hidden-480">
                                        <button type="button" class="btn btn-circle green" onclick="">打开</button>
                                        <button type="button" class="btn btn-circle red" onclick="">关闭</button>
                                    </td>
                                </tr>

                                </tbody>
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
<jsp:include page="../../tool/footer.jsp"></jsp:include>
<script type="text/javascript">
    function closeLED() {
        var message = {
            "type" : "switch",
            "operateTo":"LED",
            "behavior" : "close"
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
                //数据库增加一条日志记录
                var log = {
                    "operateFrom":$("#currentUser").val(),
                    "operateTo":"LED",
                    "behavior":"关闭"
                };
                $.ajax({
                    type: "POST",
                    url: "/admin/log/add",
                    data: log,
                    dataType: "json",
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
    function openLED() {
        var message = {
            "type" : "switch",
            "operateTo":"LED",
            "behavior" : "open"
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
                //数据库增加一条日志记录
                var log = {
                    "operateFrom":$("#currentUser").val(),
                    "operateTo":"LED",
                    "behavior":"打开"
                };
                $.ajax({
                    type: "POST",
                    url: "/admin/log/add",
                    data: log,
                    dataType: "json",
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
</script>


</body>

<!-- END BODY -->

</html>
