<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>修改个人信息</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="metro/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="metro/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="metro/css/fullcalendar.css"/>
    <link rel="stylesheet" href="metro/css/matrix-style.css"/>
    <link rel="stylesheet" href="metro/css/matrix-media.css"/>
    <link href="metro/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" href="metro/css/jquery.gritter.css"/>

    <script type="text/javascript" src="js/validator.js"></script>

    <script type="text/javascript" src="docs/jquery-1.8.0.js"></script>
    <script type="text/javascript" src="docs/bootstrap.js"></script>
    <script type="text/javascript" src="docs/application.js"></script>
    <script type="text/javascript" src="docs/jquery.validate.js"></script>
    <script type="text/javascript" src="docs/jquery.validate.unobtrusive.js"></script>
    <script type="text/javascript" src="docs/jquery.unobtrusive-ajax.js"></script>
    <script type="text/javascript" src="docs/metro-bootstrap/metro-docs.js"></script>

    <script type="text/javascript">
        window.onload = function () {
            var mzvalue = document.getElementById("mzvalue").innerHTML;
            document.getElementById("mz").value = mzvalue;
            var mzvalue = document.getElementById("zyvalue").innerHTML;
            document.getElementById("zy").value = mzvalue;
        };

        $(document).ready(function () {
            var msg = "";
            if (msg) alert(msg);
        });

        function checkJtzz() {
            var jtzz = document.getElementById("jtzz");
            var gzdw = document.getElementById("gzdw");
            var txdz = document.getElementById("txdz");
            if (jtzz.value.length >= 50) {
                alert("家庭住址字数不能大于60");
                return false;
            }
            if (jtzz.value.length == "") {
                alert("家庭住址必须填写");
                return false;
            }


            var theform = document.getElementById("elteruserinfo");
            if (isSubmit(theform)) {
                //jtzz.value=encodeURI(jtzz.value);
                //gzdw.value=encodeURI(gzdw.value);
                //txdz.value=encodeURI(txdz.value);
                theform.submit();
            }
            ;

        }
        function isSubmit(fo) {

            var isSub = true;
            if (document.getElementById("dzyj").value != "") {
                document.getElementById("dzyj").setAttribute("datatype", "Email");
            } else {
                document.getElementById("dzyj").setAttribute("datatype", "");
            }
            if (document.getElementById("lxdh").value != "") {
                document.getElementById("lxdh").setAttribute("datatype", "Number");
            } else {
                document.getElementById("lxdh").setAttribute("datatype", "");
            }

            isSub = Validator.Validate(fo, 1);

            return isSub;
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
        <li class="active"><a title="" href="UserInfo_get?businessNUM=0002" target=""><i class="icon icon-user"></i> <span class="text">个人信息</span></a>
        </li>
        <li class=""><a title="" href="UserInfo_get?businessNUM=0008" target=""><i class="icon icon-search"></i> <span class="text">查询</span></a>
        </li>
        <li class=""><a title="" href="elterpassword.jsp" target=""><i class="icon icon-lock"></i> <span class="text">修改密码</span></a>
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
        <li class="active"><a href="UserInfo_get?businessNUM=0002"><i class="icon icon-user"></i> <span>个人信息</span></a></li>
        <li class=""><a href="UserInfo_get?businessNUM=0008"><i class="icon icon-search"></i> <span>查询</span></a></li>
        <li><a href="UserInfo_password"><i class="icon icon-lock"></i> <span>修改密码</span></a></li>
        <li><a href="UserInfo_logout"><i class="icon icon-share-alt"></i> <span>退出</span></a></li>
    </ul>
</div>
<!--sidebar-menu-->
<!--main-container-part-->
<div id="content">

    <div id="content-header">
        <div id="breadcrumb"><a href="editletter.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
            首页</a> <a href="#" class="tip-bottom">个人信息</a>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-user"></i> </span>
                        <h5>修改个人信息</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form action="UserInfo_elter" method="post" class="form-horizontal" onsubmit="return isSubmit(this);" id="elteruserinfo">
                            <div class="control-group">
                                <label class="control-label">用户名:</label>

                                <div class="controls">
                                    <s:property value="map.get('用户名')"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">姓名:</label>

                                <div class="controls">
                                    <s:property value="map.get('姓名')"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">证件类型:</label>

                                <div class="controls">
                                    <s:property value="map.get('证件类型')"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">证件编码:</label>

                                <div class="controls">
                                    <s:property value="map.get('证件编码')"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">职业:</label>

                                <div class="controls">
                                    <s:select name="zy" datatype="Require" msg="请选择" list="map.get('zy')"
                                              listKey="key" listValue="name" headerKey="-1" headerValue="请选择"
                                              value="seleced">
                                    </s:select>
                                    <div style="display:none;" id="zyvalue"><s:property
                                            value="map.get('zyseleced')"/></div>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label">名族:</label>

                                <div class="controls">
                                    <s:select name="mz" id="mz" datatype="Require" msg="请选择" list="map.get('mz')"
                                              listKey="key" listValue="name" headerKey="" headerValue="请选择" class="controls">
                                    </s:select>
                                    <div style="display:none;" id="mzvalue"><s:property
                                            value="map.get('mzseleced')"/></div>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">出生日期:</label>

                                <div class="controls">
                                    <s:property value="map.get('出生日期')"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">性别:</label>

                                <div class="controls">
                                    <s:property value="map.get('性别')"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">家庭住址:</label>

                                <div class="controls">
                                    <input type="text" name="jtzz" id="jtzz"
                                           value="<s:property value="map.get('家庭住址')"/>" class="span4"/>
                                    <input type="button" value="选 择" onclick="processProv(1);" class="btn"/>
                                </div>
                            </div>
                            <div class="control-group" id="1classes_key" style="display: none;">
                                <label class="control-label">一级地址:</label>

                                <div class="controls" id="1classes_value" style="display: none;">
                                    <select name="province" onchange="sendSelect(this.value,2,'islogin')" id="province">
                                        <option value="">请选择</option>
                                        <option value=WG000000>外国</option>
                                        <option value=11000000>北京市</option>
                                        <option value=12000000>天津市</option>
                                        <option value=13000000>河北省</option>
                                        <option value=14000000>山西省</option>
                                        <option value=15000000>内蒙古自治区</option>
                                        <option value=21000000>辽宁省</option>
                                        <option value=22000000>吉林省</option>
                                        <option value=23000000>黑龙江省</option>
                                        <option value=31000000>上海市</option>
                                        <option value=32000000>江苏省</option>
                                        <option value=33000000>浙江省</option>
                                        <option value=34000000>安徽省</option>
                                        <option value=35000000>福建省</option>
                                        <option value=36000000>江西省</option>
                                        <option value=37000000>山东省</option>
                                        <option value=41000000>河南省</option>
                                        <option value=42000000>湖北省</option>
                                        <option value=43000000>湖南省</option>
                                        <option value=44000000>广东省</option>
                                        <option value=45000000>广西壮族自治区</option>
                                        <option value=46000000>海南省</option>
                                        <option value=50000000>重庆市</option>
                                        <option value=51000000>四川省</option>
                                        <option value=52000000>贵州省</option>
                                        <option value=53000000>云南省</option>
                                        <option value=54000000>西藏自治区</option>
                                        <option value=61000000>陕西省</option>
                                        <option value=62000000>甘肃省</option>
                                        <option value=63000000>青海省</option>
                                        <option value=64000000>宁夏回族自治区</option>
                                        <option value=65000000>新疆维吾尔自治区</option>
                                        <option value=71000000>台湾省</option>
                                        <option value=81000000>香港特别行政区</option>
                                        <option value=910000>澳门特别行政区</option>
                                        <option value=BT000000>新疆生产建设兵团</option>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group" id="2classes_key" style="display: none;">
                                <label class="control-label">二级地址:</label>

                                <div class="controls" id="2classes_value" style="display: none;">
                                    <select id="city" onchange="sendSelect(this.value,3)" name="city">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group" id="3classes_key" style="display: none;">
                                <label class="control-label">三级地址:</label>

                                <div class="controls" id="3classes_value" style="display: none;">
                                    <select id="county" name="county">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group" id="4classes_key" style="display: none;">
                                <label class="control-label">详细地址</label>

                                <div class="controls" id="4classes_value" style="display: none;">
                                    <input type="text" name="detailaddress" id="detailaddress" maxlength="detailaddress" class="span4"/>
                                    <input type="button" id="ok" value="确定" onclick="commit_address('jtzz')" class="btn">

                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label">工作单位:</label>

                                <div class="controls">
                                    <input type="text" name="gzdw" id="gzdw"
                                           value="<s:property value="map.get('工作单位')"/>" class="span4"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">通讯地址:</label>

                                <div class="controls">
                                    <input type="text" name="txdz" id="txdz"
                                           value="<s:property value="map.get('通讯地址')"/>" class="span4"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">电子邮箱:</label>

                                <div class="controls">
                                    <input type="email" name="dzyj" id="dzyj" class="span4"
                                           value="<s:property value="map.get('电子邮箱')"/>"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">邮政编码:</label>

                                <div class="controls">
                                    <input type="text" name="yzbm" value="<s:property value="map.get('邮政编码')"/>"
                                           class="span4"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">联系电话:</label>

                                <div class="controls">
                                    <input type="text" name="lxdh" id="lxdh"
                                           value="<s:property value="map.get('联系电话')"/>" class="span4"/>
                                </div>
                            </div>
                            <input type="hidden" name="businessNUM" value="0003"/>

                            <div class="form-actions">

                                <input type="button" value="提 交" onclick="return checkJtzz();"  class="btn btn-success"/>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
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
<script language="javascript" src="js/address.js"></script>
</body>
</html>
