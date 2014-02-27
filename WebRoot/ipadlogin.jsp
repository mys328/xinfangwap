<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>山西省信访局网上信访</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="css/ipad_login.css" type="text/css" rel="stylesheet">
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
<header>
    <img src="images/121.png"/>
</header>

<!-- 提示-->
<div class="ms">

  <span id="message">
	  <s:actionmessage/>
  </span>

</div>
<section class="login">
    <form id="login" name="login" method="post" action="LoginValidator_login?client=pad"
          onSubmit="return fSaveCookie();">

        <div class="ipt" id="iptUsername">

            <input id="yhm" class="ipt-input" type="text" required="required" name="yhm" placeholder="用户名"/>
        </div>
        <div class="ipt ipt-pw" id="iptPassword">
            <input class="ipt-input" name="dlmm" id="password" type="password" required="required" placeholder="密码"/>
        </div>
        <div class="ipt-tick" id="iptTick">
            <input type="text" maxLength=4 size=9
                   name="checkCode1" id="checkCode1" placeholder="验证码"/> <img src="CheckCode_getCheckCode">
        </div>
        <input type="hidden" name="businessNUM" value="0001">
        <a class="reg-link" href="register.jsp">注册帐号</a>
        <a class="forget-link" href="forgetpassword1.jsp">忘记密码？</a>
        <input class="ipt-submit" id="iptSubmit" type="submit" value="登 录"/>
    </form>
</section>

<footer>
		<span class="copyright">
            Copyright 2013-2014 山西省委省政府信访局&nbsp;&nbsp;晋ICP备11002801号
		</span>
    <a class="footerLinkVer footerLinkVer-iPad" href="ipadlogin.jsp">平板智能版</a>
    <a class="footerLinkVer footerLinkVer-smart" href="mobilelogin.jsp">手机智能版</a>
    <a class="footerLinkVer footerLinkVer-pc" href="desktop_login.jsp">电脑网页版</a>
</footer>


</body>
</html>
