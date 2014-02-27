<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>山西省信访局网上信访</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/mobile_login.css" type="text/css" rel="stylesheet">
    <link href="css/tips.css" type="text/css" rel="stylesheet">
    <script language="javascript" src="js/toast.js"></script>
    <script language="javascript" src="js/validator.js"></script>
    <script type="text/javascript">

        function checkUser() {
            var name = document.getElementById("yhm").value;
            var pwd = document.getElementById("dlmm").value;
            if (name == null || name == "") {
                alert("用户名不能为空！");
                return false;
            }
            if (pwd == null || pwd == "") {
                alert("密码不能为空，请输入密码验证！");
                return false;
            }
            if (name != "" && pwd != "") {
                document.getElementById("login").submit();

            }
        }

    </script>
</head>

<body>
<!-- logo-->
<div class="logo">
    <img src="images/121.png"/>
</div>
<!-- 提示-->
<div class="ms">

  <span id="message">
	  <s:actionmessage/>
  </span>

</div>
<!--表单-->
<div class="mod mod-login">
    <form id="login" name="login" method="post" action="LoginValidator_login?client=mobile"
          onSubmit="return fSaveCookie();">

        <div class="iptBox">
            <div class="item">
                <div class="left_div">用户名：</div>
                <div class="right_div">
                    <input name="yhm" id="yhm" maxLength=30 size=20 type="text"/>
                </div>
            </div>
            <div class="item">
                <div class="left_div">密 码：</div>
                <div class="right_div">
                    <input type="password" maxLength=30 size=20
                           name="dlmm" id="dlmm"/>
                </div>
            </div>
            <div class="item">
                <div class="left_div">验证码：</div>
                <div class="right_div">
                    <input type="text" maxLength=4 size=9
                           name="checkCode1" id="checkCode1"> <img src="CheckCode_getCheckCode"/>
                </div>
            </div>
        </div>
        <input type="hidden" name="businessNUM" value="0001">

        <div class="submit">
            <button class="loginBtn" onclick="checkUser()">登 录</button>
        </div>

        <div class="reg">
            <a href="register.jsp">马上注册</a> &nbsp;&nbsp; <a href="forgetpassword1.jsp">忘记密码？</a>
        </div>

        <div class="mod-footer">
            <p><strong>手机智能版（适配iPhone/Android系统手机）</strong></p>

            <p><a href="ipadlogin.jsp">平板智能版</a> | <a href="desktop_login.jsp">电脑网页版</a></p>

            <p><span class="copyright">Copyright 2013-2014 山西省委省政府信访局&nbsp;&nbsp;晋ICP备11002801号</span></p>
        </div>

    </form>
</div>

</body>
</html>
