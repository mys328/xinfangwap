<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>山西信访局统一网管入口</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <![endif]-->

    <style type="text/css">
        body {
            margin: 0 auto;
            min-height: 665px;
            height: auto;
        }

        div {
            text-align: center;
            border: 0px solid #000;
        }

        #header {
            /*background-image:url("images/hd_bg.png");*/
            font-family: "微软雅黑";
            font-size: small;
            font-weight: 200;
            border: 0px solid #000;
            padding-top: 10px;
        }

        a:LINK {
            color: #000;
        }

        #titles {
            width: 100%;
            height: auto;
        }
        @media screen and (min-width: 768px) {
            #content {
                width: 100%;
                height: auto;
                border: 0px solid #000;
                background-color: #eee;
                padding-top: 100px;

            }

        }
        @media screen and (mam-width: 768px) {
            #content {
                width: 100%;
                height: auto;
                border: 0px solid #000;
                background-color: #eee;

            }

        }

        .span6 {
            border: 0px solid #000;

        }

        img {
            width: 80%;

        }

        #erweima {
            width: 50%;
            margin-top: 10px;
        }

        #erweima_div {
            margin-top: 10px;

        }

        .buttons {
            border: 0px solid #000;
            margin-top: 4%;
            width: 100%;
        }

        #buttons_left {
            margin-top: 10px;
        }

        #coperight {
            font-size: x-small;
            color: #cfccc9;
            border: 0px solid #000;
            float: left;
            width: 100%;
            height: auto;
            margin-top: 5%;
            background-color: #5e5e5e;
        }
        #logo{
            width: 100%;
            border: 1px solid #000;
        }
        #titles {
            background-image: url("images/logo_bg.png");
        }

        h1 {
            color: #FFF;
        }

        #bottom {
            text-align: center;
            margin-top: 30px;
            border: 0px solid #000;

        }

        #ms {
            font-size: large;
            margin-bottom: 10px;
            text-align: left;
        }

        .download {
            margin-top: 10px;

        }

        #download_img {
            width: 50%;
        }
    </style>

    <script type="text/javascript">

        window.onload = function () {
            var shebei = document.getElementById("shebei");
            if (window.innerWidth >= 1080) {
                shebei.innerHTML = "电脑版";
            } else {
                shebei.innerHTML = "手机智能版";
            }
        };
    </script>
<body>

<!-- 版本切换 -->
<div id="header">
    <div class="row-fluid">
        <div class="span12" id="header_div">
            <div class="hidden-phone span3" id="header_1"></div>
            <div class="span2" id="header_2"><a href="mobilelogin.jsp">手机智能版</a></div>
            <div class="span2" id="header_3"><a href="desktop_login.jsp">电脑网页版</a></div>
            <div class="span2" id="header_4"><a href="ipadlogin.jsp">平板智能版</a></div>
            <div class="hidden-phone span3" id="header_5"></div>
        </div>
    </div>
</div>

<!-- xingfang logo -->

    <div id="titles" class="row-fluid">
        <div class="hidden-phone span2" ></div>
        <div class="span3"><h1><img src="images/121.png"/></h1></div>
        <div class="hidden-phone span7"></div>
    </div>

<!-- 内容  客户端下载 -->
<div id="content">
    <div class="row-fluid">
        <div class="span12">
            <div class="span2"></div>
            <div class="span4"><img src="images/1.png"/></div>
            <div class="span4">
                <div id="ms">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎来到山西省信访局网上信访，您可以根据下面提示来选择入口，如果您是android手机用户，也可以通过二维码扫描或直接下载安装登录。</div>
                <div id="ms2" class="row-fluid">
                    <div id="buttons_left" class="span6">
                        <div class="buttons"><a href="mobilelogin.jsp"><img src="images/mobilephone.png"/></a></div>
                        <div class="buttons"><a href="desktop_login.jsp"><img src="images/wp.png"/></a></div>
                        <div class="buttons"><a href="ipadlogin.jsp"><img src="images/pingban.png"/></a></div>

                    </div>
                    <div class="span6" id="erweima_div">
                        <div class="qrcode"><img id="erweima" src="images/qrcode.png"/></div>
                        <div class="download"><a href="http://172.16.6.80/mag/util/download.jsp?path=android_info_service_portal.apk,140117"><img id="download_img" src="images/download.png"></a></div>
                    </div>


                </div>
            </div>
            <div class="span2"></div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div id="bottom" class="row-fluid">
        <div class="span12">
            根据你的使用设备分辨率，建议您使用<span id="shebei" style="color: #f29e19"></span>登录
        </div>
    </div>
</div>

<div align="center" id="coperight" class="row-fluid">
    <div class="span12">Copyright 2013-2014 山西省委省政府信访局 晋ICP备11002801号</div>
</div>

</body>
</html>