<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>首页菜单</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
        <li class="active" id="menu-messages"><a href="UserInfo_edit" title="" target=""><i
                class="icon icon-edit"></i> <span
                class="text">写信</span> </a>
        </li>
        <li class=""><a title="" href="UserInfo_get?businessNUM=0002" target=""><i class="icon icon-user"></i> <span
                class="text">个人信息</span></a>
        </li>
        <li class=""><a title="" href="UserInfo_get?businessNUM=0008" target=""><i class="icon icon-search"></i> <span
                class="text">查询</span></a>
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
        <li class="active"><a href="UserInfo_edit"><i class="icon icon-edit"></i> <span>写信</span></a></li>
        <li><a href="UserInfo_get?businessNUM=0002"><i class="icon icon-user"></i> <span>个人信息</span></a></li>
        <li><a href="UserInfo_get?businessNUM=0008"><i class="icon icon-search"></i> <span>查询</span></a></li>
        <li><a href="UserInfo_password"><i class="icon icon-lock"></i> <span>修改密码</span></a></li>
        <li><a href="UserInfo_logout"><i class="icon icon-share-alt"></i> <span>退出</span></a></li>
    </ul>
</div>
<!--sidebar-menu-->
<!--main-container-part-->
<div id="content">

    <div id="content-header">
        <div id="breadcrumb"><a href="UserInfo_edit" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>
            首页</a> <a href="#" class="tip-bottom">写信</a>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-edit"></i> </span>
                        <h5>写信</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form id="editletter" name="editletter" method="post" action="UserInfo_elter?businessNUM=0007"
                              class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">标题:*</label>

                                <div class="controls">
                                    <input type="text" class="span8" placeholder="" name="bt" maxlength="30" id="bt"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">是否公开:*</label>

                                <div class="controls">
                                    <label>
                                        <input type="radio" name="sfgk" value="1"/>
                                        公开</label>
                                    <label>
                                        <input type="radio" name="sfgk" value="0" checked/>
                                        不公开</label>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">事发地:*</label>

                                <div class="controls">
                                    <input type="text" class="span8" placeholder="请选择事发地" id="sfd" name="sfd"
                                           readonly="readonly"/>
                                    <input type="button" value="选 择" onclick="processProv(1);" class="btn"/>
                                </div>
                            </div>
                            <div class="control-group" id="1classes_key" style="display: none;">
                                <label class="control-label">一级地址:</label>

                                <div class="controls" id="1classes_value" style="display: none;">
                                    <select name="province" onchange="sendSelect(this.value,2,'islogin')" id="province">
                                        <option value="">请选择</option>
                                        <option value=14000000>山西省</option>
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
                                <label class="control-label"></label>

                                <div class="controls" id="4classes_value" style="display: none;">
                                    <input type="button" id="ok" value="确定" onclick="commit_address('sfd');"
                                           class="btn"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">问题类别:*</label>

                                <div class="controls">
                                    <select class="span8" name="nrfl" id="nrfl" maxlength="30">
                                        <option value="">请选择</option>
                                        <option value="15010300">野蛮拆迁</option>
                                        <option value="15020300">小区水电气暖维修</option>
                                        <option value="15040100">住房改革</option>
                                        <option value="15050100">拖欠工程款</option>
                                        <option value="15050200">拖欠农民工工资</option>
                                        <option value="15060300">建筑质量</option>
                                        <option value="14010300">宅基地</option>
                                        <option value="14020200">安置补偿不合理</option>
                                        <option value="12020300">城乡低保</option>
                                        <option value="12050200">复退人员安置</option>
                                        <option value="13010100">村集体资产管理</option>
                                        <option value="13010200">事务公开</option>
                                        <option value="13020100">承包争议</option>
                                        <option value="13020200">土地流转</option>
                                        <option value="13070200">粮食直补</option>
                                        <option value="10020200">辞退</option>
                                        <option value="10040100">在职人员工资待遇</option>
                                        <option value="10040200">离退休人员工资待遇</option>
                                        <option value="10060200">军转干部安置</option>
                                        <option value="10070200">事业单位改制</option>
                                        <option value="11030100">大气污染</option>
                                        <option value="11030500">环境监察</option>
                                        <option value="12010100">村委会选举</option>
                                        <option value="08030100">医疗事故</option>
                                        <option value="08040300">计生执法</option>
                                        <option value="10020100">招聘录用</option>
                                        <option value="06060200">拖欠离退休金</option>
                                        <option value="07040200">工资待遇</option>
                                        <option value="07040400">民办教师</option>
                                        <option value="02040300">破产</option>
                                        <option value="04020100">以权谋私</option>
                                        <option value="04030300">徇私枉法</option>
                                        <option value="05030300">执法不力</option>
                                        <option value="05070100">治安案件</option>
                                        <option value="05080200">交通事故</option>
                                        <option value="05090100">立案侦查</option>
                                        <option value="05090300">侦破不力</option>
                                        <option value="05100100">户口管理</option>
                                        <option value="06010100">养老保险</option>
                                        <option value="06020200">拖欠工资</option>
                                        <option value="99010200">平反落实政策</option>
                                        <option value="99010400">精简下放</option>
                                        <option value="99020000">表扬感谢</option>
                                        <option value="99990000">其他</option>
                                    </select>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">反映问题:*</label>

                                <div class="controls">
                                    <textarea class="span8" rows="8" name="nr" id="nr" min="1" max="1500"></textarea>
                                    <span class="help-block">(最多可输1500字)</span>
                                </div>
                            </div>


                            <div class="control-group">
                                <label class="control-label">当前附件:</label>

                                <div class="controls">
                                    <input type="hidden" name="fileXml" id="fileXml" value="" maxlength="300"/>

                                    <div name="xml" id="xml" style="display: none">
                                        <div id="filelist"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">验证码:*</label>

                                <div class="controls">
                                    <input type="text" class="span4" placeholder="" maxLength=4
                                           name="checkCode" id="checkCode"/>
                                    <img src="CheckCode_getCheckCode">
                                </div>
                            </div>
                            <div class="form-actions">
                                <button type="submit" class="btn btn-success">提交</button>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-edit"></i> </span>
                        <h5>附件上传</h5>
                    </div>
                    <div class="widget-content nopadding">

                        <div class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label">验证码:*</label>

                                <div class="controls">
                                    <s:form id="uploadForm" method="post" enctype="multipart/form-data">
                                        <s:file id="image" name="image"/>
                                        <s:submit type="button" value="上传" onclick="upload()"/>
                                    </s:form>
                                    <span>(附件在2MB内)</span>
                                </div>
                            </div>
                        </div>

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
<script language="javascript" src="js/address.js"></script>
<script language="javascript" src="js/jquery.form.js"></script>
<script language="javascript" src="js/uploadfile.js"></script>

<script language="javascript">
    function commitLetter() {
        var fileXml = document.getElementById("fileXml");

        if (fileXml.value == "") {
            fileXml.value = "<Files Domain='jscx' Path=''></Files>";
        }
        //fileXml.value=encodeURI(fileXml.value);
        var bt = document.getElementById("bt");
        bt.value = trim(bt.value);
        //bt.value=encodeURI(bt.value);
        var sfd = document.getElementById("sfd");
        sfd.value = trim(sfd.value);
        //sfd.value=encodeURI(sfd.value);
        var nr = document.getElementById("nr");
        nr.value = trim(nr.value);
        //nr.value=encodeURI(nr.value);
        var editletter = document.getElementById("editletter");
        if (isSubmit(editletter)) {
            document.getElementById("editletter").submit();
        }
    }
    function isSubmit(fo) {
        var isSub = true;
        isSub = Validator.Validate(fo, 3);
        if (isSub) {
            if (!confirm("是否确定提交，提交后将不能修改！")) {
                isSub = false;
            }
        }
        return isSub;

    }
    //去做空格
    function ltrim(s) {
        return s.replace(/^\s*/, "");
    }
    //去右空格
    function rtrim(s) {
        return s.replace(/\s*$/, "");
    }
    //去左右空格
    function trim(s) {
        return rtrim(ltrim(s));
    }
    $(document).ready(function () {
        var msg = "";
        if (msg) alert(msg);
    });
</script>
</body>

</html>
