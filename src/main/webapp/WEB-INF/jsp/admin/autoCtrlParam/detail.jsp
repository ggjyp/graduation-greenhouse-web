<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tld/datetag.tld" %>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

    <meta charset="utf-8"/>

    <title>智能温室原型系统</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>

    <meta content="" name="description"/>

    <meta content="" name="author"/>

    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="/media/css/select2_metro.css"/>

    <link rel="stylesheet" href="/media/css/DT_bootstrap.css"/>
    <link href="/media/css/glyphicons.css" rel="stylesheet">
    <link href="/media/css/halflings.css" rel="stylesheet">
</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">


<jsp:include page="../tool/top.jsp"></jsp:include>


<!-- BEGIN CONTAINER -->

<div class="page-container row-fluid">

    <jsp:include page="../tool/sidebar.jsp">
        <jsp:param name="class" value="control-sign"></jsp:param>
        <jsp:param name="class-li" value="control-signrule-list"></jsp:param>
    </jsp:include>


    <!-- BEGIN PAGE -->

    <div class="page-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        ${record.paramName}
                        <small></small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/admin/index">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/admin/autoCtrlParam/list">自动控制参数管理</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">${record.paramName}</a>
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
                            <div class="caption">
                                >${record.paramName}
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="expand"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <form action="/admin/autoCtrlParam/add" class="form-horizontal update-form" method="post"
                                  enctype="multipart/form-data">
                                <div class="control-group">
                                    <label class="control-label">自动控制参数名称<span
                                            class="required">*</span>
                                    </label>
                                    <div class="controls">
                                        <input type="text" name="paramName" data-required="1" value="${record.paramName}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">温度下限<span
                                            class="required">*</span>
                                    </label>
                                    <div class="controls">
                                        <input type="number" name="temperatureMin" data-required="1" step="0.1" value="${record.temperatureMin}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">温度上限<span
                                            class="required">*</span>
                                    </label>
                                    <div class="controls">
                                        <input type="number" name="temperatureMax" data-required="1" step="0.1" value="${record.temperatureMax}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">湿度下限<span
                                            class="required">*</span>
                                    </label>
                                    <div class="controls">
                                        <input type="number" name="humidityMin" data-required="1" step="0.1" value="${record.humidityMin}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">湿度上限<span
                                            class="required">*</span>
                                    </label>
                                    <div class="controls">
                                        <input type="number" name="humidityMax" data-required="1" step="0.1" value="${record.humidityMax}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">光照强度下限<span
                                            class="required">*</span>
                                    </label>
                                    <div class="controls">
                                        <input type="number" name="lightIntensityMin" data-required="1" step="0.1" value="${record.lightIntensityMin}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">光照强度上限<span
                                            class="required">*</span>
                                    </label>
                                    <div class="controls">
                                        <input type="number" name="lightIntensityMax" data-required="1" step="0.1" value="${record.lightIntensityMax}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">土壤湿度下限<span
                                            class="required">*</span>
                                    </label>
                                    <div class="controls">
                                        <input type="number" name="soilMoistureMin" data-required="1" step="0.1" value="${record.soilMoistureMin}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">土壤湿度上限<span
                                            class="required">*</span>
                                    </label>
                                    <div class="controls">
                                        <input type="number" name="soilMoistureMax" data-required="1" step="0.1" value="${record.soilMoistureMax}">
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">通风时间<span
                                            class="required">*</span>
                                    </label>
                                    <div class="controls">
                                        <input type="text"  value="22:00--24:00">
                                    </div>
                                </div>

                                <div class="form-actions">
                                    <button type="button" class="btn green" onclick="confirmadd()">确认</button>
                                </div>
                            </form>
                        </div>
                    </div>

                    <!-- END EXAMPLE TABLE PORTLET-->
                </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->
        </div>


        <!-- END PAGE CONTENT-->

    </div>

    <!-- END PAGE CONTAINER-->

</div>

<!-- END PAGE -->


<!-- END CONTAINER -->


<jsp:include page="../tool/footer.jsp"></jsp:include>


<script type="text/javascript" src="/media/js/bootstrap-fileupload.js"></script>
<script src="/media/jquery-form.js"></script>
<script>

    $(".update_btn").click(function () {
        if (confirm("确认修改?")) {
            var form = $(".update-form");
            var options = {
                url: '/admin/autoCtrlParam/update',
                type: 'post',
                success: function (data) {
                    if (data.state == "00000") {
                        pop("修改成功");
                    } else {
                        notice(data.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    notice("网络异常");
                }

            };
            form.ajaxSubmit(options);
        }

    });

</script>


</body>

<!-- END BODY -->

</html>
