<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>山西信访局WAP用户注册</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="metro/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="metro/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="metro/css/colorpicker.css" />
    <link rel="stylesheet" href="metro/css/datepicker.css" />
    <link rel="stylesheet" href="metro/css/uniform.css" />
    <link rel="stylesheet" href="metro/css/select2.css" />
    <link rel="stylesheet" href="metro/css/matrix-style.css"/>
    <link rel="stylesheet" href="metro/css/matrix-media.css"/>
    <link href="metro/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" href="metro/css/bootstrap-wysihtml5.css" />

    <style type="text/css">
        @media (min-width: 769px) {
            .container-fluid{
                margin-left: 15%;
            }
            .control-group1{
                text-align: center;

            }
        }
        .logo{
            width: 100%;
            text-align: center;
            margin-top: 50px;
        }
        #psw{
            margin-top: 30px;
            margin-bottom:30px ;
        }
        .ms {
            color: #ed0e37;
            width: auto;
            height: 30px;
            margin-left: 50px;
            text-align: center;
            border: 0px solid #000;
        }

        #message ul {
            list-style-type: none;
        }
    </style>
    <script language="javascript">
        function reback() {
            window.history.back(-1);
        }
    </script>

    <script language="javascript">
        function commit() {
            var register = document.getElementById("register");

            register.submit();
            //};
        }
        //验证用户名
        function checkYhm() {
            var yhm = document.getElementById("yhm");
            var checkyhm = document.getElementById("checkyhm");
            var xmlhttp;
            //配置XMLHttpRequest对象
            if (window.XMLHttpRequest) {
                xmlhttp = new XMLHttpRequest();

            } else {
                xmlhttp = new ActiveXObject("microsoft.XMLHTTP");
            }
            //标签
            xmlhttp.open("get", "UserInfo_noLoginAjax?businessNUM=0016&prop=yhm&value=" + yhm.value, true);
            //发送请求
            xmlhttp.send(null);
            xmlhttp.onreadystatechange = function show() {
                if (xmlhttp.readyState == 4) {
                    if (xmlhttp.status == 200) {
                        var doc = xmlhttp.responseText;
                        if (doc.indexOf("验证通过", 0) != -1) {
                            checkyhm.innerHTML = "";
                            checkyhm.innerHTML = "验证通过";
                        } else {
                            checkyhm.innerHTML = "";
                            checkyhm.innerHTML = doc;
                            yhm.focus();
                        }

                    }
                }
            }
        }


        //检查住址
        function checkJtzz() {
            var jtzz = document.getElementById("jtzz");
            if (jtzz.value.length >= 50) {
                alert("家庭住址字数不能大于60!!!");
                return false;
            }
            if (jtzz.value == 0) {
                alert("请输入家庭住址");
                return false;
            }
            return true;
        }
        //检查姓名是否为汉字
        function checkChina() {
            var str = document.getElementById("zsxm").value;

            var reg = /^[\u4e00-\u9fa5]+$/i;
            if (!reg.test(str)) {
                alert("请输入中文名字！");
                return false;
            }
            return true;

        }
        function isSubmit(fo) {
            var isSub = true;

            //验证姓名是否全为汉字
            isSub = checkChina();
            if (isSub == false) {
                return false;
            }
            //验证家庭住址
            isSub = checkJtzz();
            if (isSub == false) {
                return false;
            }

            //验证联系电话
            if (document.getElementById("lxdh").value == "") {
                alert("电话不能为空!");
                return false;
            } else {
                if (document.getElementById("lxdh").value) {
                    document.getElementById("lxdh").setAttribute("datatype", "Number");
                } else {
                    document.getElementById("lxdh").setAttribute("datatype", "");
                }
            }
            isSub = Validator.Validate(fo, 1);
            return isSub;
        }
        function reback() {
            window.history.back(-1);
        }
    </script>

</head>

<body>
<div class="logo"><img src="images/121.png" alt=""/></div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span10">
            <div class="widget-box">
                <div class="widget-title"><span class="icon"> <i class="icon-edit"></i> </span>
                    <h5>用户注册</h5>
                </div>
                <div class="widget-content nopadding">
                    <form action="UserInfo_nologin?businessNUM=0015" method="post" onsubmit="return isSubmit(this)"
                          id="register" class="form-horizontal">

                        <div class="control-group">
                            <div class="ms">
                                      <span id="message">
                                          <s:actionmessage/>
                                      </span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">用户名：</label>

                            <div class="controls">
                                <input type="text" name="yhm" id="yhm" value="" maxlength="20" onblur="checkYhm()"
                                class="span8" placeholder="3-15位并且只能由[字母]和[数字]组成"/>
                                <span id="checkyhm" class="tips"></span>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">登录密码：</label>

                            <div class="controls">
                                <input type="password" name="dlmm" id="dlmm" value=""
                                       maxlength="21" datatype="Limit" min="6" max="20"
                                       placeholder="登录密码必须在6到20位之间" class="span8"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">确认密码：</label>

                            <div class="controls">
                                <input type="password" name="qrdlmm" id="qrdlmm" value="" maxlength="21" datatype="Repeat" to="dlmm"
                                       placeholder="请重新输入您的密码" class="span8"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">证件类型：</label>

                            <div class="controls">
                                <select id="zjlx" name="zjlx" datatype="Require" msg="请选择" class="span8">
                                    <option value="1">居民身份证</option>
                                    <option value="2">军官证</option>
                                    <option value="3">士兵证</option>
                                    <option value="4">武警证</option>
                                    <option value="5">港澳台居民身份证</option>
                                    <option value="6">有效护照</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">证件编号：</label>

                            <div class="controls">
                                <input type="text" id="zjbh" name="zjbh" value="" maxlength="20"
                                       datatype="Require" placeholder="证件编号必须填写" class="span8"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">姓名：</label>

                            <div class="controls">
                                <input type="text" name="zsxm" id="zsxm" value="" maxlength="30" datatype="Require"
                                       placeholder="真实姓名必须认真填写" class="span8"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">性别：</label>

                            <div class="controls">
                                <select id="xb" name="xb" datatype="Require" msg="请选择" class="span8">
                                    <option value="0">男</option>
                                    <option value="1">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">出生日期：</label>

                            <div class="controls">
                                <input type="text" placeholder="2014-02-04" data-date-format="yyyy-mm-dd" value=""
                                       class="datepicker span8">

                                <%--<input type="date" data-date-format="yyyy-mm-dd" value=""  size="20" class="span8"/>--%>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">民族：</label>

                            <div class="controls">
                                <select name="mz" datatype="Require" msg="请选择" class="span8">

                                    <option value="01">汉族</option>

                                    <option value="02">蒙古族</option>

                                    <option value="03">回族</option>

                                    <option value="04">藏族</option>

                                    <option value="05">唯吾尔族</option>

                                    <option value="06">苗族</option>

                                    <option value="07">彝族</option>

                                    <option value="08">壮族</option>

                                    <option value="09">布依族</option>

                                    <option value="10">朝鲜族</option>

                                    <option value="11">满族</option>

                                    <option value="12">侗族</option>

                                    <option value="13">瑶族</option>

                                    <option value="14">白族</option>

                                    <option value="15">土家族</option>

                                    <option value="16">哈尼族</option>

                                    <option value="17">哈萨克族</option>

                                    <option value="18">傣族</option>

                                    <option value="19">黎族</option>

                                    <option value="20">傈僳族</option>

                                    <option value="21">佤族</option>

                                    <option value="22">畬族</option>

                                    <option value="23">高山族</option>

                                    <option value="24">拉祜族</option>

                                    <option value="25">水族</option>

                                    <option value="26">东乡族</option>

                                    <option value="27">纳西族</option>

                                    <option value="28">景颇族</option>

                                    <option value="29">柯尔克孜族</option>

                                    <option value="30">土族</option>

                                    <option value="31">达幹尔族</option>

                                    <option value="32">仫佬族</option>

                                    <option value="33">羌族</option>

                                    <option value="34">布朗族</option>

                                    <option value="35">撒拉族</option>

                                    <option value="36">毛南族</option>

                                    <option value="37">仡佬族</option>

                                    <option value="38">锡伯族</option>

                                    <option value="39">阿昌族</option>

                                    <option value="40">普米族</option>

                                    <option value="41">塔吉克族</option>

                                    <option value="42">怒族</option>

                                    <option value="43">乌孜别克族</option>

                                    <option value="44">俄罗斯族</option>

                                    <option value="45">鄂温克族</option>

                                    <option value="46">德昂族</option>

                                    <option value="47">保安族</option>

                                    <option value="48">裕固族</option>

                                    <option value="49">京族</option>

                                    <option value="50">塔塔尔族</option>

                                    <option value="51">独龙族</option>

                                    <option value="52">鄂伦春族</option>

                                    <option value="53">赫哲族</option>

                                    <option value="54">门巴族</option>

                                    <option value="55">珞巴族</option>

                                    <option value="56">基诺族</option>

                                    <option value="97">其他</option>

                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">家庭住址:</label>

                            <div class="controls">

                                <input type="text" name="jtzz" id="jtzz"
                                       value="<s:property value="map.get('家庭住址')"/>" class="span8"/>
                                <input type="button" value="选 择" onclick="processProv(1);" class="btn"/>
                            </div>
                        </div>
                        <div class="control-group" id="1classes_key" style="display: none;">
                            <label class="control-label">一级地址:</label>

                            <div class="controls" id="1classes_value" style="display: none;">
                                <select name="province" onchange="sendSelect(this.value,2,'islogin')" id="province">
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
                            <label class="control-label">联系电话：</label>

                            <div class="controls">
                                <input type="text" name="lxdh" id="lxdh" maxlength="15" value="" placeholder="电话号码须符合常规电话号码规则"
                                        class="span8"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">密码问题：</label>

                            <div class="controls">
                                <select NAME="tsmmwt" id="tsmmwt" maxlength="30" datatype="Require" class="span8">

                                    <option value="我就读的第一所学校名称？">我就读的第一所学校名称？</option>

                                    <option value="我最喜欢的休闲运动是什么？">我最喜欢的休闲运动是什么？</option>

                                    <option value="我最喜欢的运动员是谁？">我最喜欢的运动员是谁？</option>

                                    <option value="我最喜欢的物品名称？">我最喜欢的物品名称？</option>

                                    <option value="我最喜欢的歌曲？">我最喜欢的歌曲？</option>

                                    <option value="我最喜欢的食物？">我最喜欢的食物？</option>

                                    <option value="我最爱的人的名字?">我最爱的人的名字?</option>

                                    <option value="我最重要的日子？">我最重要的日子？</option>

                                    <option value="我妈妈的生日？">我妈妈的生日？</option>

                                </select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">密码答案：</label>

                            <div class="controls">
                                <input type="text" name="tsmmda" id="tsmmda" maxlength="30" value="" datatype="Require" placeholder="密码答案必须填写"
                                        class="span8"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">验证码：</label>

                            <div class="controls">
                                <input type="text" maxLength=4 class="span2"
                                       name="checkCode" id="checkCode"><img src="CheckCode_getCheckCode">
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-success">注册</button>
                            <input type="button" value="返回" onclick="reback()" class="btn">
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<script src="metro/js/matrix.login.js"></script>
<script  src="js/address.js"></script>
<script src="metro/js/jquery.min.js"></script>
<script src="metro/js/jquery.ui.custom.js"></script>
<script src="metro/js/bootstrap.min.js"></script>
<script src="metro/js/bootstrap-colorpicker.js"></script>
<script src="metro/js/bootstrap-datepicker.js"></script>
<script src="metro/js/jquery.toggle.buttons.html"></script>
<script src="js/jquery.toggle.buttons.html"></script>

<script src="metro/js/jquery.uniform.js"></script>
<script src="metro/js/select2.min.js"></script>
<script src="metro/js/masked.js"></script>
<script src="metro/js/jquery.uniform.js"></script>
<script src="metro/js/matrix.js"></script>
<script src="metro/js/matrix.form_common.js"></script>
<script src="metro/js/wysihtml5-0.3.0.js"></script>
<script src="metro/js/jquery.peity.min.js"></script>
<script src="metro/js/bootstrap-wysihtml5.js"></script>

</html>
