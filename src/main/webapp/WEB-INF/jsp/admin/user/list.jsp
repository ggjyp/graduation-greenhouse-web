<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <style>

    </style>
</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">


<jsp:include page="../tool/top.jsp"></jsp:include>


<!-- BEGIN CONTAINER -->

<div class="page-container row-fluid">

    <jsp:include page="../tool/sidebar.jsp">
        <jsp:param name="class" value="control-system"></jsp:param>
        <jsp:param name="class-li" value="control-showadmin"></jsp:param>
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
                        管理员管理
                        <small></small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/admin/index">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">管理员管理</a>
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
                            <div class="caption"><i class="icon-bell"></i>管理员管理</div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>

                        <div class="portlet-body">
                            <c:if test="${sessionScope.get('super')=='-1'}">
                                <div class="btn-group">
                                    <button id="toadd" class="btn blue">
                                        新增 <i class="icon-plus"></i>
                                    </button>
                                </div>
                            </c:if>
                            <div class="alert alert-error alert-error-msg hide">
                                <button class="close" data-dismiss="alert"></button>
                                <span class="msg-fail"></span>
                            </div>
                            <table class="table table-striped table-bordered table-advance table-hover">
                                <thead>
                                <tr>
                                    <th><i></i>用户名</th>
                                    <th class="hidden-phone"><i></i> 权限</th>
                                    <c:if test="${sessionScope.get('super')=='-1'}">
                                        <th class="hidden-phone"><i></i> 角色</th>
                                    </c:if>
                                    <th><i></i>上次登陆时间</th>
                                    <th><i></i>上次登陆IP</th>
                                    <th><i></i>本次登陆时间</th>
                                    <th><i></i>本次登陆IP</th>
                                    <c:if test="${sessionScope.get('super')=='-1'}">
                                        <th><i class="icon-edit"></i> 更改</th>
                                    </c:if>
                                    <c:if test="${sessionScope.get('super')=='-1'}">
                                        <th><i class="icon-trash"></i> 删除</th>
                                    </c:if>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="i">
                                    <tr>
                                        <td class="highlight" style="vertical-align: middle">
                                            <div class="success"></div>
                                            <a href="#">${i.adminName}</a>
                                        </td>
                                        <td style="vertical-align: middle">
                                            <c:if test="${i.adminState=='-1'}">超级管理员</c:if>
                                            <c:if test="${i.adminState=='0'}">普通管理员</c:if>
                                            <c:if test="${i.adminState=='1'}">停用</c:if>
                                        </td>
                                        <c:if test="${sessionScope.get('super')=='-1'}">
                                            <td style="vertical-align: middle">
                                                <div id="role-${i.adminId}">
                                                    <c:forEach items="${rolelist}" var="role" varStatus="idx">
                                                        <label class="checkbox rolecheck" style="display: inline;!important;">
                                                                    <span>
                                                                        <input type="checkbox" name="role-${i.adminId}"
                                                                               value="${role}"
                                                                               <c:if test="${fn:contains(i.roles,role)}">checked</c:if>>
                                                                    </span>
                                                                ${role}
                                                        </label>
                                                        <c:if test="${idx.count%3==0}"><br></c:if>
                                                    </c:forEach>
                                                </div>
                                            </td>
                                        </c:if>
                                        <td style="vertical-align: middle">
                                            <ex:date value="${i.adminPrelogintime}" pattern="yyyy-MM-dd HH:mm:ss"></ex:date>
                                        </td>
                                        <td style="vertical-align: middle">
                                                ${i.adminPreloginip}
                                        </td>
                                        <td style="vertical-align: middle">
                                            <ex:date value="${i.adminLastlogintime}" pattern="yyyy-MM-dd HH:mm:ss"></ex:date>
                                        </td>
                                        <td style="vertical-align: middle">
                                                ${i.adminLastloginip}
                                        </td>
                                        <c:if test="${sessionScope.get('super')=='-1'}">
                                            <td style="vertical-align: middle">
                                                <button class="btn mini purple update_btn"
                                                        data-name="${i.adminName}"
                                                        data-id="${i.adminId}" data-oldrole="${i.roles}"><i
                                                        class="icon-trash"></i>更改
                                                </button>
                                            </td>
                                        </c:if>
                                        <c:if test="${sessionScope.get('super')=='-1'}">
                                            <td style="vertical-align: middle">
                                                <button class="btn mini black cancel_btn"
                                                        data-id="${i.adminId}"><i class="icon-trash"></i> 删除
                                                </button>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>

                                </tbody>

                            </table>
                        </div>

                    </div>
                </div>
                <!-- END SAMPLE TABLE PORTLET-->
            </div>

            <c:if test="${sessionScope.get('super')=='-1'}">
                <div class="row-fluid hide" id="add_row">
                    <div class="span6">
                        <!-- BEGIN VALIDATION STATES-->
                        <div class="portlet box blue">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-reorder"></i>添加管理员</div>
                                <div class="tools">
                                    <a href="javascript:;" class="collapse"></a>
                                </div>
                            </div>
                            <div class="portlet-body form" style="display: block;">
                                <!-- BEGIN FORM-->
                                <form action="#" class="form-horizontal add-form">
                                    <div class="control-group">
                                        <label class="control-label">用户名<span
                                                class="required">*</span></label>
                                        <div class="controls">
                                            <input type="text" name="name" id="name" data-required="1"
                                                   class="span6 m-wrap">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">密码<span
                                                class="required">*</span></label>
                                        <div class="controls">
                                            <input name="password" type="password" id="password"
                                                   class="span6 m-wrap">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">角色<span
                                                class="required">*</span></label>
                                        <div class="controls">
                                            <label class="radio line">
                                                <div class="radio"><span class=""><input type="radio" name="role"
                                                                                         value="-1"></span>
                                                </div>
                                                超级管理员
                                            </label>
                                            <label class="radio line">
                                                <div class="radio"><span><input type="radio" name="role"
                                                                                value="0"></span>
                                                </div>
                                                普通管理员
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <button type="button" class="btn green add_admin_btn">确认</button>
                                    </div>
                                </form>
                                <!-- END FORM-->
                            </div>
                        </div>
                        <!-- END VALIDATION STATES-->
                    </div>
                </div>
            </c:if>
            <!-- END PAGE CONTENT-->

        </div>

        <!-- END PAGE CONTAINER-->

    </div>

    <!-- END PAGE -->

</div>

<!-- END CONTAINER -->


<jsp:include page="../tool/footer.jsp"></jsp:include>

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="/media/js/select2.min.js"></script>
<script type="text/javascript" src="/media/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="/media/js/DT_bootstrap.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script src="/media/js/jquery.validate.min.js" type="text/javascript"></script>


<script src="/media/js/table-advanced.js"></script>
<script src="/media/md5.js"></script>
<script src="/media/layer/layer.js"></script>

<script>
    jQuery(document).ready(function () {
        TableAdvanced.init();
    });
    $(".add_admin_btn").click(function () {
        var name = $("#name").val();
        var pass = $("#password").val();
        if (name == null || name == "") {
            notice("请填写用户名");
            return;
        }
        if (pass == null || pass == "") {
            notice("请填写密码");
            return;
        }
        var role = $('input[name="role"]:checked ').val();
        if (role == null || role == "") {
            notice("请选择角色");
            return;
        }
        $.ajax({
            type: "post",
            url: "/admin/add",
            dataType: "json",
            timeout: 200000,
            data: {
                name: name,
                password: hex_md5(pass),
                state: role
            },
            success: function (data) {
                if (data.state == '00000') {
                    domethod(pop("添加成功"), 1000)
                }
                else {
                    notice(data.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                notice("网络异常");
            }
        });
    });

    $(".cancel_btn").click(function () {
        if (confirm("确认删除？")) {
            var id = $(this).attr("data-id");
            $.ajax({
                type: "post",
                url: "/admin/del",
                dataType: "json",
                timeout: 200000,
                data: {
                    id: id
                },
                success: function (data) {
                    if (data.state == '00000') {
                        domethod(pop("删除成功"), 1000);
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

    $(".update_btn").click(function () {

        var id = $(this).attr("data-id");
        var oldrole = $(this).attr("data-oldrole");
        var name = $(this).attr("data-name");
        var rolename = "input[name=role-" + id + "]";

        var newrole = "";
        $(rolename).each(function () {
            if (this.checked) {
                newrole += $(this).val() + ",";
            }
        });
        if (newrole.length > 0)
            newrole = newrole.substr(0, newrole.length - 1);
        if (newrole == oldrole) {
            notice("没有做任何修改");
            return;
        }
        if (confirm("确认修改该管理员:" + name + "的角色？")) {
            $.ajax({
                type: "post",
                url: "/admin/role/set",
                dataType: "json",
                timeout: 200000,
                data: {
                    adminname: name,
                    adminid: id,
                    oldrole: oldrole,
                    newrole: newrole
                },
                success: function (data) {
                    if (data.state == '00000') {
                        domethod(pop("更改成功"), 1000);
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

    $("#toadd").click(function () {
        $("#add_row").show();
    });
</script>


</body>

<!-- END BODY -->

</html>
