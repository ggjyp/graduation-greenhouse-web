<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tld/datetag.tld" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

<link href="/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

<link href="/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

<link href="/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

<link href="/media/css/style.css" rel="stylesheet" type="text/css"/>

<link href="/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

<link href="/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

<link href="/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

<!-- END GLOBAL MANDATORY STYLES -->
<link href="/media/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" type="text/css" href="/media/css/select2_metro.css"/>
<link rel="stylesheet" href="/media/css/DT_bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="/media/css/bootstrap-fileupload.css"/>
<link rel="stylesheet" type="text/css" href="/media/css/style.css"/>
<link href="/media/css/halflings.css" rel="stylesheet">
<link href="/media/css/glyphicons.css" rel="stylesheet">
<link href="/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
<link href="/media/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" href="/media/image/favicon.ico"/>
<style>
    /*popbox的样式*/
    .popbox {
        text-align: center;
        z-index: 9999;
        display: inline-block;
        position: fixed;
        color: #fff;
        background-color: rgba(0, 0, 0, 0.5);
        font-size: 15px;
        line-height: 20px;
        padding: 20px 40px;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        display: none;
    }
</style>
<script src="/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<div class="header navbar navbar-inverse navbar-fixed-top">
    <!-- BEGIN HEADER -->
    <!-- BEGIN TOP NAVIGATION BAR -->

    <div class="navbar-inner">

        <div class="container-fluid">

            <!-- BEGIN LOGO -->

            <a class="brand" href="/admin/index">

                <img src="/media/image/logo.png" alt="logo"/>
            </a>

            <a href="javascript:" onclick="self.location=document.referrer;" class="btn black"><i
                    class="m-icon-swapleft m-icon-white"></i>返回</a>
            <!-- END LOGO -->

            <!-- BEGIN RESPONSIVE MENU TOGGLER -->

            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

                <img src="/media/image/menu-toggler.png" alt=""/>

            </a>

            <!-- END RESPONSIVE MENU TOGGLER -->

            <!-- BEGIN TOP NAVIGATION MENU -->

            <ul class="nav pull-right">

                <!-- BEGIN USER LOGIN DROPDOWN -->
                <li class="dropdown user">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <img alt="" src="${sessionScope.getOrDefault('adminimage','/media/image/avatar1_small.jpg')}"
                             style="height: 28.99px;width: 28.99px"/>
                        <span class="username">${sessionScope.admin_name}
                        </span>
                        <i class="icon-angle-down"></i>

                    </a>

                    <ul class="dropdown-menu">


                        <li><a href="#change_adminimage" data-toggle="modal">
                            <i class="icon-picture"></i>修改头像</a>
                        </li>
                        <li><a href="#change_name" data-toggle="modal">
                            <i class="icon-user-md"></i>修改用户名</a>
                        </li>
                        <li><a href="#change_pass" data-toggle="modal">
                            <i class="icon-key"></i>修改密码</a>
                        </li>
                        <li><a href="/admin/logout"><i class="icon-signout"></i>登出</a></li>

                    </ul>

                </li>
                <!-- END USER LOGIN DROPDOWN -->
            </ul>

            <!-- END TOP NAVIGATION MENU -->
            <a href="#static" data-toggle="modal" id="tostatic"></a>
            <a href="#notlong" data-toggle="modal" id="tonotlong"></a>
            <div id="change_name" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true"></button>
                    <h3>修改昵称</h3>
                </div>
                <div class="modal-body">
                    <input type="text" class="m-wrap" id="new_adminname" placeholder="请在此处输入新的昵称">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn red" id="tochangename">确认</button>
                    <button type="button" data-dismiss="modal" class="btn">取消</button>
                </div>
            </div>
            <div id="change_pass" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true"></button>
                    <h3>修改密码</h3>
                </div>
                <div class="modal-body">
                    <input type="password" class="m-wrap" id="new_adminpass" placeholder="请在此处输入新的密码">
                    <input type="password" class="m-wrap" id="new_readminpass" placeholder="请再次输入">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn red" id="tochangepass">确认</button>
                    <button type="button" data-dismiss="modal" class="btn">取消</button>
                </div>
            </div>
            <div id="change_adminimage" class="modal hide fade" tabindex="-1" data-focus-on="input:first">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true"></button>
                    <h3>修改头像</h3>
                </div>
                <div class="modal-body">
                    <form action="#" class="form-horizontal" id="adminimage-form" method="post"
                          enctype="multipart/form-data">
                        <input type="hidden" name="type" value="2">
                        <div class="control-group">
                            <label class="control-label">头像<span
                                    class="required">*</span>
                            </label>
                            <div class="controls">
                                <div class="fileupload fileupload-new" data-provides="fileupload">
                                    <div class="fileupload-new thumbnail"
                                         style="width: 200px; height: 150px;">
                                        <img src="" alt=""/>
                                    </div>
                                    <div class="fileupload-preview fileupload-exists thumbnail"
                                         style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                                    <div>
													<span class="btn btn-file"><span class="fileupload-new">选择文件</span>
													<span class="fileupload-exists">更改</span>
													<input type="file" class="default" name="image"/></span>
                                        <a href="#" class="btn fileupload-exists"
                                           data-dismiss="fileupload">移除</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn red" id="tochangeimage">确认</button>
                    <button type="button" data-dismiss="modal" class="btn">取消</button>
                </div>
            </div>
            <div id="static" class="modal hide fade" tabindex="-1" data-backdrop="static"
                 data-keyboard="false">

                <div class="modal-body">

                    <p class="confirm_text">确认继续该操作吗?</p>

                </div>

                <div class="modal-footer">

                    <button type="button" data-dismiss="modal" class="btn" id="cancel-model">取消</button>

                    <button type="button" data-dismiss="modal" class="btn green" id="confirm-model">确认</button>

                </div>

            </div>
            <div id="notlong" class="modal hide fade" tabindex="-1" data-replace="true">

                <div class="modal-header">
                    <h3>提示框</h3>
                </div>
                <div class="modal-body" id="notice-text">

                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn">关闭</button>
                </div>
            </div>

            <div id="ajax-modal" class="modal hide fade" tabindex="-1"></div>
        </div>

    </div>

    <!-- END TOP NAVIGATION BAR -->


</div>


<!-- BEGIN CORE PLUGINS -->
<div class="popbox"></div>


<script src="/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="/media/js/bootstrap.min.js" type="text/javascript"></script>

<!--[if lt IE 9]>

<script src="/media/js/excanvas.min.js"></script>

<script src="/media/js/respond.min.js"></script>

<![endif]-->

<script src="/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="/media/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="/media/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="/media/js/jquery.uniform.min.js" type="text/javascript"></script>


<script src="/media/js/jquery.gritter.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->


<!-- BEGIN PAGE LEVEL PLUGINS -->

<script src="/media/js/bootstrap-modal.js" type="text/javascript"></script>

<script src="/media/js/bootstrap-modalmanager.js" type="text/javascript"></script>

<!-- END PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="/media/js/bootstrap-fileupload.js"></script>
<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="/media/js/app.js"></script>

<script src="/media/js/ui-modals.js"></script>
<script src="/media/md5.js"></script>
<script src="/media/jquery-form.js"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script>

    jQuery(document).ready(function () {
        App.init();
        UIModals.init();
        overflow();
    });
    /*超出部分省略号显示*/
    function overflow() {
        $(".message").each(function () {
            var text = $(this).text();
            if (16 < text.length) {
                text = text.substring(0, 16 - 1) + "...";
            }
            $(this).html(text);
        });
    }
    (function($) {
        $.extend({
            myTime: {
                /**
                 * 当前时间戳
                 * @return <int>        unix时间戳(秒)
                 */
                CurTime: function(){
                    return Date.parse(new Date())/1000;
                },
                /**
                 * 日期 转换为 Unix时间戳
                 * @param <string> 2014-01-01 20:20:20  日期格式
                 * @return <int>        unix时间戳(秒)
                 */
                DateToUnix: function(string) {
                    var f = string.split(' ', 2);
                    var d = (f[0] ? f[0] : '').split('-', 3);
                    var t = (f[1] ? f[1] : '').split(':', 3);
                    return (new Date(
                            parseInt(d[0], 10) || null,
                            (parseInt(d[1], 10) || 1) - 1,
                            parseInt(d[2], 10) || null,
                            parseInt(t[0], 10) || null,
                            parseInt(t[1], 10) || null,
                            parseInt(t[2], 10) || null
                        )).getTime() / 1000;
                },
                /**
                 * 时间戳转换日期
                 * @param <int> unixTime    待时间戳(秒)
                 * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)
                 * @param <int>  timeZone   时区
                 */
                UnixToDate: function(unixTime, isFull, timeZone) {
                    if (typeof (timeZone) == 'number')
                    {
                        unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
                    }
                    var time = new Date(unixTime * 1000);
                    var ymdhis = "";
                    ymdhis += time.getUTCFullYear() + "-";
                    ymdhis += (time.getUTCMonth()+1) + "-";
                    ymdhis += time.getUTCDate();
                    if (isFull === true)
                    {
                        ymdhis += " " + time.getUTCHours() + ":";
                        ymdhis += time.getUTCMinutes() + ":";
                        ymdhis += time.getUTCSeconds();
                    }
                    return ymdhis;
                }
            }
        });
    })(jQuery);

</script>

<!-- END HEADER -->
<script type="text/javascript">


    function notice(msg) {
        $("#notice-text").text(msg);
        $("#tonotlong").click();
    }
    function domethod(fun, time) {
        setTimeout(function () {
            fun, location.reload(true);
        }, time);
    }
    function pop(a) {
        var b = 1000;
        var c = 600;
        $('.popbox').text(a);
        $(".popbox").show();
        $(".popbox").css("z-index", "99999999");
        setTimeout(hidepop, c);
    }
    function hidepop() {
        $(".popbox").fadeOut(' + b + ');
    }
    $.extend({
        StandardPost: function (url, args) {
            var form = $("<form method='post'></form>"),
                input;
            form.attr({"action": url});
            $.each(args, function (key, value) {
                input = $("<input type='hidden'>");
                input.attr({"name": key});
                input.val(value);
                form.append(input);
            });
            form.submit();
        }
    });
    $("#tochangeimage").click(function () {
        if (confirm("确认修改?")) {
            var form = $("#adminimage-form");
            var options = {
                url: '/admin/update',
                type: 'post',
                success: function (data) {
                    if (data.state == "00000") {
                        domethod(pop("修改成功"), 1000)
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

    $("#tochangename").click(function () {
        var newname = $("#new_adminname").val();
        if (newname == null || newname == "") {
            notice("昵称不能为空");
            return;
        }

        if (confirm("确认修改?")) {
            $.ajax({
                type: "post",
                url: "/admin/update",
                dataType: "json",
                timeout: 200000,
                data: {
                    type: "1",
                    data: newname
                },
                success: function (data) {
                    if (data.state == '00000') {
                        location.href = "/admin/logout";
//                        pop("修改成功")
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
        $("#change_name").hide();
        $(".modal-backdrop").hide();
    });

    $("#tochangepass").click(function () {
        var pass = $("#new_adminpass").val();
        var repass = $("#new_readminpass").val();
        if (pass == null || pass == "") {
            notice("密码不合法");
            return;
        }
        if (pass != repass) {
            notice("两次输入密码不一致");
            return;
        }
        if (confirm("确认修改?"))
            $.ajax({
                type: "post",
                url: "/admin/update",
                dataType: "json",
                timeout: 200000,
                data: {
                    type: "0",
                    data: hex_md5(pass)
                },
                success: function (data) {
                    if (data.state == '00000') {
                        location.href = "/admin/logout";
                    }
                    else {
                        notice(data.msg);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    notice("网络异常");
                }
            });
        $("#change_pass").hide();
        $(".modal-backdrop").hide();
    });

</script>
