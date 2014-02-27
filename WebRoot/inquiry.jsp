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
    <script language="javascript" type="text/javascript">
        window.onload = function () {

            var nowpage = document.getElementById("nowpage").innerHTML;
            document.getElementById("selectpage").options[nowpage - 1].selected = true;

        }
        function goToThePage(obj) {

            window.location.href = obj.options[obj.selectedIndex].value;
        }
    </script>
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
<div id="content">

    <div id="content-header">
        <div id="breadcrumb">
            <a href="UserInfo_edit" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a>
            <a href="UserInfo_get?businessNUM=0008" class="tip-bottom">查询</a>
            <a href="#" class="tip-bottom">查询列表</a>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-table"></i> </span>
                        <h5>查询列表</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <thead>
                            <tr>
                                <th>标题</th>
                                <th>写信日期</th>
                                <th>问题类别</th>
                                <th>状态</th>
                                <th>办理情况</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="map.get('lists')" status="status">
                                <tr>
                                    <td class="titles"><a href="<s:property value="bt_href"/>"><s:property value="bt"/></a></td>
                                    <td class="date"><s:property value="time"/></td>
                                    <td ><s:property value="wtlx"/></td>
                                    <s:if test="blqk_href==null">
                                        <td class="status"><s:property value="zt"/></td>
                                    </s:if>
                                    <s:else>
                                        <td class="status"><a href="<s:property value="blqk_href"/>"><s:property value="zt"/></a></td>
                                    </s:else>
                                    <s:if test="blqk_href==''">
                                        <td ><s:property value="blqk"/></td>
                                    </s:if>
                                    <s:else>
                                        <td class="status"><a href="<s:property value="blqk_href"/>"><s:property value="blqk"/></a></td>
                                    </s:else>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="pagination" style="text-align: center">
        <ul >
            <s:if test="map.get('pages').get('上一页')==null">
                <li class="disabled"><a href="#">&laquo;</a></li>
            </s:if>
            <s:else>
                <li><a href="<s:property value="map.get('pages').get('上一页')"/>">&laquo;</a></li>
            </s:else>
            <s:iterator value="map.get('pages').get('pagelist')" var="ls">
                <s:if test="map.get('pages').get('nowpage')==#ls.key">
                    <li class="active"><a href="<s:property value="value"/>"><s:property value="key"/></a></li>
                </s:if>
                <s:else>
                    <li><a href="<s:property value="value"/>"><s:property value="key"/></a></li>
                </s:else>
            </s:iterator>
            <s:if test="map.get('pages').get('下一页')==null">
                <li class="disabled"><a href="#">&raquo;</a></li>
            </s:if>
            <s:else>
                <li><a href="<s:property value="map.get('pages').get('下一页')"/>">&raquo;</a></li>
            </s:else>
        </ul>
    </div>
</div>
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
