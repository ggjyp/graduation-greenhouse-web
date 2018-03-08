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
        <jsp:param name="class" value="control-hardware-list"></jsp:param>
    </jsp:include>
    <div class="page-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        温室数据列表
                        <small></small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/admin/index">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">温室数据列表</a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <div class="row-fluid">

                <div class="span12">
                    <!-- BEGIN SAMPLE TABLE PORTLET-->
                    <div class="portlet box green">
                        <div class="portlet-title">
                            <div class="caption">温室数据列表
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
                                <%--<form method="post" action="/admin/log/list">--%>
                                    <%--<tr style="vertical-align: middle">--%>
                                        <%--<td colspan="11" style="vertical-align: middle">--%>
                                            <%--<div class="dataTables_info paging_bootstrap pagination">--%>
                                                <%--<ul>--%>
                                                    <%--<li>--%>
                                                        <%--操作者：--%>
                                                        <%--<input type="text" value="" name="operateFrom"--%>
                                                               <%--style="width: 125px">--%>
                                                    <%--</li>--%>
                                                    <%--<li>--%>
                                                        <%--操作对象：--%>
                                                        <%--<input type="text" value="" name="operateTo"--%>
                                                               <%--style="width: 125px">--%>
                                                    <%--</li>--%>
                                                    <%--<li>--%>
                                                        <%--操作时间段：--%>
                                                        <%--<input type="text" style="width: 130px;!important;" name="starttime" value="${starttime}">--%>
                                                        <%------%>
                                                        <%--<input type="text" style="width: 130px;!important;" name="endtime" value="${endtime}">--%>
                                                    <%--</li>--%>
                                                    <%--<li>--%>
                                                        <%--<input type="hidden" name="page" value="1">--%>
                                                        <%--<button type="submit"--%>
                                                                <%--style="padding: 5px 14px;vertical-align: top;"--%>
                                                                <%--class="btn green">搜索--%>
                                                        <%--</button>--%>
                                                    <%--</li>--%>
                                                <%--</ul>--%>
                                            <%--</div>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                <%--</form>--%>
                                <tr>
                                    <th class="hidden-480">温度</th>
                                    <th class="hidden-480">湿度</th>
                                    <th class="hidden-480">土壤湿度</th>
                                    <th class="hidden-480">光照强度</th>
                                    <th class="hidden-480">数据时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${records}" var="i">
                                    <tr>
                                        <td class="hidden-480">${i.temperature}</td>
                                        <td class="hidden-480">${i.humidity}</td>
                                        <td class="hidden-480">${i.lightIntensity}</td>
                                        <td class="hidden-480">${i.soilMoisture}</td>
                                        <td class="hidden-480">${i.eventDate}</td>
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
<jsp:include page="../../tool/footer.jsp"></jsp:include>
<script>
    function gotopage(page) {
        if (parseInt(page) <= 0 || parseInt(page) >=${maxpage+1})
            return;
        location.href = "/admin/hardware/table/list?page=" + page;
    }
</script>


</body>

<!-- END BODY -->

</html>
