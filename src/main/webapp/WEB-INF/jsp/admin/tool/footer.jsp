<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!-- BEGIN FOOTER -->

<div class="footer">

    <div class="footer-inner">

        2017 &copy; 江依鹏毕业设计. 智能温室原型系统. 客户端网站

    </div>

    <div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

    </div>
    <div id="super" data-super="${sessionScope.get('super')}"></div>
</div>
<%--<script src="/media/foundation-datepicker.js"></script>--%>
<%--<script src="/media/foundation-datepicker.zh-CN.js"></script>--%>
<script src="/media/jquery-ui-datepicker.js"></script>
<script type="text/javascript">
    $('#starttime').datepicker()
    $('#endtime').datepicker({
        format: 'yyyy-mm-dd'
    });
    $("input[name='starttime']").datepicker({
        format: 'yyyy-mm-dd'
    });
    $("input[name='endtime']").datepicker({
        format: 'yyyy-mm-dd'
    });
    $("input[name='startdate']").datepicker({
        format: 'yyyy-mm-dd'
    });
    $("input[name='enddate']").datepicker({
        format: 'yyyy-mm-dd'
    });
    $("input[name='expiretime_start']").datepicker({
        format: 'yyyy-mm-dd'
    });
    $("input[name='expiretime_end']").datepicker({
        format: 'yyyy-mm-dd'
    });
</script>
<!-- END FOOTER -->