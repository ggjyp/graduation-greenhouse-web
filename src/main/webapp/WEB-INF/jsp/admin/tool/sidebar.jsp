<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="color-panel hidden-phone" style="margin-right: 20px">
    <div class="color-mode-icons icon-color"></div>

    <div class="color-mode-icons icon-color-close" style="display: none;"></div>

    <div class="color-mode" style="display: none;">

        <p>主题颜色</p>

        <ul class="inline">

            <li class="color-black current color-default" data-style="default"></li>

            <li class="color-blue" data-style="blue"></li>

            <li class="color-brown" data-style="brown"></li>

            <li class="color-purple" data-style="purple"></li>

            <li class="color-grey" data-style="grey"></li>

            <li class="color-white color-light" data-style="light"></li>

        </ul>

        <label>

            <span>布局</span>

            <select class="layout-option m-wrap small">

                <option value="fluid" selected="">Fluid</option>

                <option value="boxed">Boxed</option>

            </select>

        </label>

        <label>

            <span>头部</span>

            <select class="header-option m-wrap small">

                <option value="fixed" selected="">Fixed</option>

                <option value="default">Default</option>

            </select>

        </label>

        <label>

            <span>导航栏</span>

            <select class="sidebar-option m-wrap small">

                <option value="fixed">Fixed</option>

                <option value="default" selected="">Default</option>

            </select>

        </label>

        <label>

            <span>底部</span>

            <select class="footer-option m-wrap small">

                <option value="fixed">Fixed</option>

                <option value="default" selected="">Default</option>

            </select>

        </label>

    </div>

</div>

<div class="page-sidebar nav-collapse collapse">

    <!-- BEGIN SIDEBAR MENU -->
    <ul class="page-sidebar-menu">
        <li>
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            <div class="sidebar-toggler hidden-phone"></div>
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
        </li>
        <li>
            <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
            <form class="sidebar-search" style="display:none">
                <div class="input-box">
                    <a href="javascript:;" class="remove"></a>
                    <input type="text" placeholder="Search..."/>
                    <input type="button" class="submit" value=" "/>
                </div>
            </form>

            <!-- END RESPONSIVE QUICK SEARCH FORM -->
        </li>
        <li class="start control-dashboard">
            <a href="/admin/index">
                <i class="icon-home"></i>
                <span class="title">首页</span>
                <span class="selected"></span>
            </a>
        </li>

        <c:if test="${sessionScope.get('super')=='-1'}">
            <li class="control-user">
                <a href="/admin/list">
                    <i class="icon-user"></i>
                    <span class="title">管理员管理</span>
                </a>
            </li>
        </c:if>

        <c:if test="${sessionScope.get('super')=='-1'||fn:contains(sessionScope.get('roles'),'温室硬件管理' )}">
            <li class="control-hardware">
                <a href="javascript:;">
                    <i class="icon-cogs"></i>
                    <span class="title">温室硬件管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li class="control-hardware-list">
                        <a href="/admin/hardware/table/list">
                            温室数据列表</a>
                    </li>
                    <li class="control-hardware-chart">
                        <a href="/admin/hardware/chart/list">
                            温室数据图表</a>
                    </li>
                    <li class="control-hardware-controller">
                        <a href="/admin/hardware/control/list">
                            温室硬件控制</a>
                    </li>

                </ul>
            </li>
        </c:if>

        <c:if test="${sessionScope.get('super')=='-1'||fn:contains(sessionScope.get('roles'),'日志管理' )}">
        <li class="control-log">
            <a href="/admin/log/list">
                <i class="icon-book"></i>
                <span class="title">日志管理</span>
                <span class="selected"></span>
            </a>
        </li>
        </c:if>
        <c:if test="${sessionScope.get('super')=='-1'||fn:contains(sessionScope.get('roles'),'自动控制参数管理' )}">
            <li class="control-auto-ctrl-param">
                <a href="/admin/autoCtrlParam/list">
                    <i class="icon-tasks"></i>
                    <span class="title">自动控制参数管理</span>
                    <span class="selected"></span>
                </a>
            </li>
        </c:if>
    </ul>
    <!-- END SIDEBAR MENU -->

</div>

<!-- END SIDEBAR -->
<!-- END SIDEBAR -->
<script>
    $(function () {
        $(".<%=request.getParameter("class") %>").each(function () {
            $(this).addClass("active");
        });
        $(".<%=request.getParameter("class-li") %>").each(function () {
            $(this).addClass("active");
        });
        $(".<%=request.getParameter("class-open") %>").each(function () {
            $(this).addClass("active");
        });
    });

</script>