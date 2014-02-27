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
    <style type="text/css">
        #tijiao{
            text-align: center;
            width: 100%;
        }
    </style>
    <script type="text/javascript">
        function commit() {
            var mycd_commit = document.getElementById("mycd_commit");
            mycd_commit.submit();
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
        <div id="breadcrumb"><a href="UserInfo_edit" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
            首页</a>
            <a href="UserInfo_get?businessNUM=0008" class="tip-bottom">查询</a>
            <a href="#" class="tip-bottom">查看结果</a>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-table"></i> </span>
                        <h5>您反映问题的详细信息</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <tr>
                                <td class="num1">序号</td>
                                <td class="num2">
                                    <s:property value="map.get('您反映问题的详细信息').get('序号')"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="num1">写信日期</td>
                                <td class="num2">
                                    <s:property value="map.get('您反映问题的详细信息').get('写信日期')"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="num1">问题类别</td>
                                <td class="num2">
                                    <s:property value="map.get('您反映问题的详细信息').get('问题类别')"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="num1">标题</td>
                                <td class="num2">
                                    <s:property value="map.get('您反映问题的详细信息').get('标题')"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="num1">反映问题</td>
                                <td class="num2">
                                    <s:property value="map.get('反映问题').get('反映问题')"/>
                                </td>
                            </tr>
                        </table>

                    </div>
            </div>

                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-table"></i> </span>
                        <h5>办理过程</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <tr>
                                <th>序号</th>
                                <th>日期</th>
                                <th>办理过程</th>

                            </tr>
                            <s:iterator value="map.get('办理过程')" status="status">
                                <tr>
                                    <td ><s:property value="序号"/></td>
                                    <td ><s:property value="日期"/></td>
                                    <td ><s:property value="办理过程"/></td>

                                </tr>
                            </s:iterator>
                        </table>

                    </div>
                </div>
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-table"></i> </span>
                        <h5>办理结果</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                            <tr>
                                <th>序号</th>
                                <th>出具单位</th>
                                <th>出具时间</th>
                                <th>文件类型</th>
                                <th>操 作</th>
                            </tr>
                            <s:iterator value="map.get('办理结果')" status="status">
                                <tr>
                                    <td ><s:property value="序号"/></td>
                                    <td ><s:property value="出具单位"/></td>
                                    <td ><s:property value="出具时间"/></td>
                                    <td ><s:property value="文件类型"/></td>
                                    <td >
                                        <%--<a href="http://172.16.2.202<s:property value="download_url"/>"><s:property value="操作"/></a></td>--%>
                                            <a href="http://172.16.2.202<s:property value="download_url"/>"><s:property value="操作"/></a></td>
                                </tr>
                            </s:iterator>
                        </table>
                    </div>
                </div>

                <s:if test="map.get('答复意见')!=null">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-table"></i> </span>
                        <h5>答复意见</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered table-striped">
                                <tr>
                                    <td class="num1">单位</td>
                                    <td class="num2">
                                        <s:property value="map.get('答复意见').get('单位')"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="num1">日期</td>
                                    <td class="num2">
                                        <s:property value="map.get('答复意见').get('日期')"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="num1">意见</td>
                                    <td class="num2"><s:property value="map.get('答复意见').get('意见')"/></td>
                                </tr>
                            </table>
                    </div>
                </div>
                </s:if>
                <s:if test="map.get('请您选择满意度')!=null">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-table"></i> </span>
                        <h5>信访人满意程度</h5>
                    </div>
                        <div class="widget-content nopadding">
                            <form action="UserInfo_elter?businessNUM=0017" method="post" id="mycd_commit">
                                <div id="tijiao">
                                    <B>请您选择满意度:</B>
                                    <s:select name="mycd" id="mycd" datatype="Require" msg="请选择" list="map.get('请您选择满意度').get('mycd')"
                                              listKey="key" listValue="name">
                                    </s:select>
                                    <span style="color: red; font-size: 12px;">(只能选择一次)</span>
                                    <input type="hidden" name="checkCode" id="checkCode"
                                           value="<s:property value="map.get('请您选择满意度').get('checkCode')"/>" />
                                    <input type="hidden" name="xfjid" id="xfjid" value="<s:property value="map.get('请您选择满意度').get('xfjid')"/>" />
                                    <input type="button" value="提交" onclick="commit()"/>
                                </div>
                            </form>
                        </div>
                </div>
                </s:if>

                <s:if test="map.get('信访人满意程度')!=null">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-table"></i> </span>
                        <h5>信访人满意程度</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table">
                            <tr>
                                <td class="num1">信访人满意程度</td>
                                <td class="num2">
                                    <span color="blue"><s:property value="map.get('信访人满意程度')"/></span>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                </s:if>
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
