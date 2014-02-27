<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>首页菜单</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="metro/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="metro/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="metro/css/fullcalendar.css"/>
    <link rel="stylesheet" href="metro/css/matrix-style.css"/>
    <link rel="stylesheet" href="metro/css/matrix-media.css"/>
    <link href="metro/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" href="metro/css/jquery.gritter.css"/>

</head>

<body>
<!--Header-part-->
<div id="header">
    <h1><a href="dashboard.html">Matrix Admin</a></h1>
</div>
<!--close-Header-part-->

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
        <li class="" id="menu-messages"><a href="UserInfo_edit" title="" target=""><i class="icon icon-edit"></i> <span
                class="text">写信</span> </a>
        </li>
        <li class=""><a title="" href="UserInfo_get?businessNUM=0002" target=""><i class="icon icon-user"></i> <span class="text">个人信息</span></a>
        </li>
        <li class="active"><a title="" href="UserInfo_get?businessNUM=0008" target=""><i class="icon icon-search"></i> <span class="text">查询</span></a>
        </li>
        <li class=""><a title="" href="UserInfo_password" target=""><i class="icon icon-lock"></i> <span class="text">修改密码</span></a>
        </li>
        <li class=""><a title="" href="UserInfo_logout" target=""><i class="icon icon-share-alt"></i> <span
                class="text">退出</span></a>
        </li>
    </ul>
</div>

<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i>导航菜单</a>
    <ul>
        <li class=""><a href="UserInfo_edit"><i class="icon icon-edit"></i> <span>写信</span></a></li>
        <li><a href="UserInfo_get?businessNUM=0002"><i class="icon icon-user"></i> <span>个人信息</span></a></li>
        <li class="active"><a href="UserInfo_get?businessNUM=0008"><i class="icon icon-search"></i> <span>查询</span></a></li>
        <li><a href="UserInfo_password"><i class="icon icon-lock"></i> <span>修改密码</span></a></li>
        <li><a href="UserInfo_logout"><i class="icon icon-share-alt"></i> <span>退出</span></a></li>
    </ul>
</div>
<!--sidebar-menu-->
<!--main-container-part-->
<div id="content">

    <div id="content-header">
        <div id="breadcrumb">
            <a href="UserInfo_edit" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a>
            <a href="UserInfo_get?businessNUM=0008" class="tip-bottom">查询</a>
            <a href="#" class="tip-bottom">内容详情</a>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-list-alt"></i> </span>
                        <h5>内容详情</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form action="#" method="get" class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">标题:</label>
                                <div class="controls">
                                    <s:property value="map.get('标 题')"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">事发地:</label>
                                <div class="controls">
                                    <s:property value="map.get('事发地')"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">问题类别:</label>
                                <div class="controls">
                                    <s:property value="map.get('问题类别')"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">投诉事项:</label>
                                <div class="controls">
                                    <s:property value="map.get('投诉事项')"/>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>

</div>
<!--end-main-container-part-->
<!--Footer-part-->
<div class="row-fluid">
    <div id="footer" class="span12">Copyright 2013-2014 山西省委省政府信访局 晋ICP备11002801号</div>
</div>
<!--end-Footer-part-->
<script src="metro/js/excanvas.min.js"></script>
<script src="metro/js/jquery.min.js"></script>
<script src="metro/js/jquery.ui.custom.js"></script>
<script src="metro/js/bootstrap.min.js"></script>
<script src="metro/js/jquery.flot.min.js"></script>
<script src="metro/js/jquery.flot.resize.min.js"></script>
<script src="metro/js/jquery.peity.min.js"></script>
<script src="metro/js/fullcalendar.min.js"></script>
<script src="metro/js/matrix.js"></script>
<script src="metro/js/matrix.dashboard.js"></script>
<script src="metro/js/jquery.gritter.min.js"></script>
<script src="metro/js/matrix.interface.js"></script>
<script src="metro/js/matrix.chat.js"></script>
<script src="metro/js/jquery.validate.js"></script>
<script src="metro/js/matrix.form_validation.js"></script>
<script src="metro/js/jquery.wizard.js"></script>
<script src="metro/js/jquery.uniform.js"></script>
<script src="metro/js/select2.min.js"></script>
<script src="metro/js/matrix.popover.js"></script>
<script src="metro/js/jquery.dataTables.min.js"></script>
<script src="metro/js/matrix.tables.js"></script>

</body>
</html>
