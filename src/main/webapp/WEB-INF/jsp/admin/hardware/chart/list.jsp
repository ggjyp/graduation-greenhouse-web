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
    <script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
    <script src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>
    <script src="/media/js/greenhouse-chart.js"></script>

</head>
<body class="page-header-fixed">
<jsp:include page="../../tool/top.jsp"></jsp:include>
<div class="page-container row-fluid">
    <jsp:include page="../../tool/sidebar.jsp">
        <jsp:param name="class" value="control-hardware-chart"></jsp:param>
    </jsp:include>
    <div class="page-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        温室数据图表
                        <small></small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/admin/index">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">温室数据图表</a>
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
                            <div class="caption">温度数据图表</div>
                            <div class="tools" style="display:none">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div id="temperatureContainer" style="min-width:400px;height:400px"></div>
                        </div>
                    </div>
                    <div class="portlet box green">
                        <div class="portlet-title">
                            <div class="caption">湿度数据图表</div>
                            <div class="tools" style="display:none">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div id="humidityContainer" style="min-width:400px;height:400px"></div>
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
    showChart();
    //每5秒刷新一次图表，实际应用中，适当延长时间，例如每分钟刷新一次图表
    window.setInterval(showChart, 5*1000);
</script>


</body>

<!-- END BODY -->

</html>
