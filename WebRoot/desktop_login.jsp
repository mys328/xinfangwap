<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML >
<html>
<head>
    <title>山西信访局WAP登陆</title>
    <link href="css/tips.css" type="text/css" rel="stylesheet">
    <link href="css/desktop_login.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="js/toast.js"></script>
    <script type="text/javascript" src="js/validator.js"></script>
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
<header class="header">
    <h1 class="headerLogo"><a href="" target="_blank" title="山西省信访局网上信访"><img src="images/desktop_login_logo.png"
                                                                              alt="山西省信访局网上信访"/></a></h1>
    <nav class="headerNav">
        <a href="desktop_login.jsp">电脑网页版</a>&nbsp;|&nbsp;<a href="mobilelogin.jsp">手机智能版</a>&nbsp;|&nbsp;<a
            href="ipadlogin.jsp">平板智能版</a>
    </nav>
</header>

<section class="main" id="mainBg">
    <!--登录框-->
    <div id="loginBlock" class="login tab-1">
        <div class="loginFunc">
            <div id="lbNormal" class="loginFuncNormal">
                用户登录
            </div>
        </div>
        <div class="loginForm">
            <form id="login" name="login" method="post" action="LoginValidator_login?client=pc"
                  onSubmit="return fSaveCookie();">
                <div class="ms">
                      <span id="message">
                          <s:actionmessage/>
                      </span>
                </div>
                <div id="idInputLine" class="loginFormIpt showPlaceholder">
                    <b class="ico ico-uid"></b>
                    <input class="loginFormTdIpt" tabindex="1" title="请输入帐号" id="yhm" type="text"
                           name="yhm" maxlength="30" value="" placeholder="用户名"/>
                </div>
                <div id="pwdInputLine" class="loginFormIpt showPlaceholder">
                    <b class="ico ico-pwd"></b>
                    <input class="loginFormTdIpt" tabindex="2" title="请输入密码"
                           id="dlmm" name="dlmm" type="password" placeholder="密码"/>
                </div>
                <div class="ddd">
                    <div id="CodeInputLine" class="codecheck">
                        <input class="code_input" tabindex="3" title="请输入验证码"
                               id="checkCode1" name="checkCode1" type="text" placeholder="验证码" size=4 maxlength="4"/>
                    </div>
                    <img src="CheckCode_getCheckCode"/>
                </div>
                <input type="hidden" name="businessNUM" value="0001">

                <div class="loginFormCheck">
                    <div class="forgetPwdLine">
                        <a class="forgetPwd" href="forgetpassword1.jsp"  title="找回密码">忘记密码了?</a>
                    </div>
                </div>
                <div class="loginFormBtn">
                    <button id="loginBtn" class="btn btn-login" tabindex="6" type="submit">登&nbsp;&nbsp;录</button>
                    <a id="lfBtnReg" class="btn btn-reg" href="register.jsp"
                       tabindex="7">注&nbsp;&nbsp;册</a>
                </div>
            </form>

        </div>
    </div>
</section>

<footer id="footer" class="footer">
    <div class="footer-inner" id="footerInner">
        <nav class="footerNav">
            <p><span class="copyright">Copyright 2013-2014 山西省委省政府信访局&nbsp;&nbsp;晋ICP备11002801号</span></p>
        </nav>
    </div>
</footer>

</body>
</html>
