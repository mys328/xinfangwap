<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>修改密码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="metro/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="metro/css/bootstrap-responsive.min.css"/>
    <link rel="stylesheet" href="metro/css/fullcalendar.css"/>
    <link rel="stylesheet" href="metro/css/matrix-style.css"/>
    <link rel="stylesheet" href="metro/css/matrix-media.css"/>
    <link href="metro/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" href="metro/css/jquery.gritter.css"/>
    <style type="text/css">
        @media (min-width: 768px) {
        .container-fluid{
            margin-left: 20%;
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
        function checkMM() {
            var mm = document.getElementById("mm").value;
            var password = document.getElementById("password");

            var mm_ = document.getElementById("mm_").value;

            if (mm == null || mm == "") {
                alert("密码不能为空！");
                return false;
            }
            if (mm_ == null || mm_ == "") {
                alert("密码不能为空！");
                return false;
            }
            if (document.getElementById("mm").value != document.getElementById("mm_").value) {
                alert("对不起，两次密码输入不同，请重新输入！");
                return false;
            } else {
                password.submit();
            }
        }
    </script>
</head>

<body>
    <div class="logo"><img src="images/121.png" alt=""/></div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span8">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"> <i class="icon-edit"></i> </span>
                        <h5>找回密码</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <form action="UserInfo_nologin?businessNUM=0014" method="post" id="password" name="password"
                              class="form-horizontal">
                            <div class="control-group1">

                                <img src="images/psw_step4.png" alt="" id="psw"/>
                            </div>
                            <div class="control-group">
                                <div class="ms">
                                      <span id="message">
                                          <s:actionmessage/>
                                      </span>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">请输入新密码：</label>

                                <div class="controls">
                                    <input type="password" name="mm" id="mm" datatype="Limit" min="6"
                                           max="20" msg="登录密码必须在6到20位之间" class="span8"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">再次输入密码：</label>

                                <div class="controls">
                                    <input type="password" name="mm_" id="mm_" datatype="Repeat"
                                           to="mm" msg="两次输入的密码不相同"  class="span8">
                                    <input type="hidden" name="step" value="5"/>
                                </div>
                            </div>
                            <div class="form-actions">
                                <input type="button"  value="提交" class="btn btn-success" onclick="checkMM()"/>
                                <input type="button" value="返回" onclick="reback()" class="btn"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>

<script src="js/jquery.min.js"></script>
<script src="js/matrix.login.js"></script>
</body>
</html>
