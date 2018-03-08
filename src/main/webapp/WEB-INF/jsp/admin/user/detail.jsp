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
        <jsp:param name="class" value="control-content"></jsp:param>
        <jsp:param name="class-li" value="control-information-list"></jsp:param>
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
                        ${record.nickname}
                        <small></small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="/admin/index">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="/admin/user/list">用户列表</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li>
                            <a href="#">${record.nickname}</a>
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
                                ${record.nickname}
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#portlet-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <form action="#" class="form-horizontal update-form" method="post"
                                  enctype="multipart/form-data">
                                <div class="control-group">
                                    <label class="control-label">用户名</label>
                                    <div class="controls">
                                        <input type="text" name="nickname" data-required="1" value="${record.nickname}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">手机</label>
                                    <div class="controls">
                                        <input type="text" name="nickname" data-required="1" value="${record.phone}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">生日</label>
                                    <div class="controls">
                                        <input type="text" readonly  id="birthday" style="width:130px!important;" value="${record.birthday}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">年龄</label>
                                    <div class="controls">
                                        <input type="number" name="age" data-required="1" value="${record.age}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">性别</label>
                                    <div class="controls">
                                        <input type="text" name="gender" data-required="1" value="${record.gender}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">宝宝真实姓名</label>
                                    <div class="controls">
                                        <input type="text" name="realname" data-required="1" value="${record.realname}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">注册时间</label>
                                    <div class="controls">
                                        <input type="text" readonly  id="registertime" style="width:130px!important;" value="${record.registertime}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">家长学历</label>
                                    <div class="controls">
                                        <input type="text" data-required="1" value="${record.parent_education}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">家长毕业院校</label>
                                    <div class="controls">
                                        <input type="text" data-required="1" value="${record.parent_school}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">家长毕业时间</label>
                                    <div class="controls">
                                        <input type="text" readonly  id="parent_finishtime" style="width:130px!important;" value="${record.parent_finishtime}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">微信绑定状态</label>
                                    <div class="controls">
                                        <select name="wxisbind">
                                            <option value="0" <c:if test="${record.wxisbind=='0'}">selected</c:if>>未绑定</option>
                                            <option value="1" <c:if test="${record.wxisbind=='1'}">selected</c:if>>已绑定</option>
                                        </select>
                                    </div>
                                </div>
                                <c:if test="${record.wxisbind=='1'}">
                                    <div class="control-group">
                                        <label class="control-label">微信绑定时间</label>
                                        <div class="controls">
                                            <input type="text" readonly  id="wxbindtime" style="width:130px!important;" value="${record.wxbindtime}">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">微信openid</label>
                                        <div class="controls">
                                            <input type="text" data-required="1" value="${record.wxopenid}"
                                                   class="span6 m-wrap" readonly>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="control-group">
                                    <label class="control-label">等级</label>
                                    <div class="controls">
                                        <input type="number" name="level" data-required="1" value="${record.level}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">哈蓓学院经验</label>
                                    <div class="controls">
                                        <input type="text" data-required="1" value="${record.expCollege}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">哈蓓世界经验</label>
                                    <div class="controls">
                                        <input type="text" data-required="1" value="${record.expWorld}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">金币</label>
                                    <div class="controls">
                                        <input type="text" data-required="1" value="${record.coin}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">上次登录时间</label>
                                    <div class="controls">
                                        <input type="text" readonly  id="prelogintime" style="width:130px!important;" value="${record.prelogintime}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">上次登录ip</label>
                                    <div class="controls">
                                        <input type="text" data-required="1" value="${record.preloginip}"
                                               class="span6 m-wrap" readonly>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">最近一次登录时间</label>
                                    <div class="controls">
                                        <input type="text" readonly  id="lastlogintime" style="width:130px!important;" value="${record.lastlogintime}">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">最近一次登录ip</label>
                                    <div class="controls">
                                        <input type="text" data-required="1" value="${record.lastlogintime}"
                                               class="span6 m-wrap" readonly>
                                    </div>
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

</body>

<!-- END BODY -->

</html>
