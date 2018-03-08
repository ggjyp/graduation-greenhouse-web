<%--
  Created by IntelliJ IDEA.
  User: oplsu
  Date: 2016/12/27
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/daterangepicker.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/showInfoIndex.css" rel="stylesheet" type="text/css"/>

    <link href="/media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>

    <link href="/media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>

    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="/media/image/favicon.ico"/>

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<!-- BEGIN HEADER -->

<jsp:include page="tool/top.jsp"></jsp:include>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container row-fluid">

    <!-- BEGIN SIDEBAR -->

    <jsp:include page="tool/sidebar.jsp"></jsp:include>

    <!-- END SIDEBAR -->

    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        首页
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="#">首页</a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>

            <!-- END PAGE HEADER-->

            <div id="dashboard">
                <!-- BEGIN DASHBOARD STATS -->
                <div class="row-fluid">
                    <div class="span6">
                        <!-- BEGIN PORTLET-->
                        <div class="portlet solid bordered light-grey">

                            <div class="portlet-title">
                                <div class="caption"><h2>智能温室原型</h2></div>
                            </div>
                            <div class="portlet-body">
                                <p class="lead">
                                    开发者：江依鹏<br>
                                    联系手机：15659320031<br>
                                    联系邮箱：jypsdly@126.com<br>
                                    联系QQ：450006497<br>
                                </p>
                            </div>
                        </div>
                        <!-- END PORTLET-->
                    </div>


                    <div class="span6">

                        <div class="counter col_fourth">
                            <p class="count-unit"> ℃ </p>
                            <h2 class="timer count-title" id="count-tem">0</h2>
                            <p class="count-text"> 温度 </p>
                        </div>

                        <div class="counter col_fourth">
                            <p class="count-unit"> % </p>
                            <h2 class="timer count-title" id="count-hum">0</h2>
                            <p class="count-text"> 湿度 </p>
                        </div>

                        <div class="counter col_fourth">
                            <p class="count-unit"> lux </p>
                            <h2 class="timer count-title" id="count-light">0</h2>
                            <p class="count-text "> 光照强度 </p>
                        </div>

                        <div class="counter col_fourth end">
                            <p class="count-unit"> % </p>
                            <h2 class="timer count-title" id="count-soil">0</h2>
                            <p class="count-text "> 土壤湿度 </p>
                        </div>
                        <br>
                        <h4 id="lastUpdateTime"></h4>
                    </div>
                </div>

                <!-- END DASHBOARD STATS -->

                <div class="clearfix"></div>


            </div>

        </div>

        <!-- END PAGE CONTAINER-->

    </div>
    <!-- END PAGE -->
</div>

<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->

<jsp:include page="tool/footer.jsp"></jsp:include>

<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="/media/js/jquery.flot.js" type="text/javascript"></script>

<script src="/media/js/jquery.flot.resize.js" type="text/javascript"></script>

<script src="/media/js/countUp.js" type="text/javascript"></script>


<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->


<script src="/media/js/index.js" type="text/javascript"></script>


<!-- END PAGE LEVEL SCRIPTS -->

<script>


    jQuery(document).ready(function () {
        Index.init();
        Index.initCharts(); // init index page's custom scripts
    });
    $(".setfields_btn").click(function () {
        if (confirm("确认修改?")) {
            var form = $("#fields_form");
            var options = {
                url: '/admin/setting/set/fileds',
                type: 'post',
                success: function (data) {
                    if (data.state == "00000") {
                        pop("设置成功");
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

    //获取最新数据并在首页中显示
    function getLastData() {
        //显示最新数据
        var tem;
        var hum;
        var light;
        var soil;
        $.ajax({
            //参数page=1,pageSize=5，表示显示最新的5个数据
            url:"http://localhost:8080/sitewhere/api/assignments/83d43843-c4a7-403c-83ac-9dc0d1918aba/measurements?page=1&pageSize=1",
            dataType: 'html',
            method: 'GET',
            async : false,//设置为同步操作就可以给全局变量赋值成功
            beforeSend: function(request){
                request.setRequestHeader("Content-Type","application/json");
                request.setRequestHeader("X-SiteWhere-Tenant","sitewhere1234567890");
                request.setRequestHeader("Authorization","Basic YWRtaW46cGFzc3dvcmQ=");
            },
            success: function(data) {
                data = eval('(' + data + ')');
                var results = data.results
                for (var i = 0; i < results.length; i++) {
                    var measurements = results[i].measurements;
                    var eventDate = results[i].eventDate;
                    $("#lastUpdateTime").text("最近一次更新时间："+ eventDate.substring(0,10)+" "+eventDate.substring(11,19));
                    tem=measurements.temperature;
                    hum=measurements.humidity;
                }
            },
            error: function(xhr) {
                console.log("error");
            }
        })
        //使用countUp插件进行数据的动画效果显示
        var options = {
            useEasing : false,
            useGrouping : true,
            separator : '',
            decimal : '.',
        };
        var countTem = new CountUp("count-tem", 0, tem, 1, 0.5, options);
        var countHum = new CountUp("count-hum", 0, hum, 1, 0.5, options);
        var countLight = new CountUp("count-light", 0, light, 1, 0.5, options);
        var countSoil = new CountUp("count-soil", 0, soil, 1, 0.5, options);
        countTem.start();
        countHum.start();
        countLight.start();
        countSoil.start();
    }
    //每分钟获取一次最新数据
    getLastData();
    window.setInterval(getLastData, 5*1000);

</script>

</body>
<!-- END BODY -->
</html>
