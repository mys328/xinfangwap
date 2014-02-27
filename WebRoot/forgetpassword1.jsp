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
                        <form method="post" id="forgetpassword" name="forgetpassword" action="UserInfo_nologin?businessNUM=0011"
                              class="form-horizontal">
                            <div class="control-group1">

                                <img src="images/psw_step1.png" alt="" id="psw"/>
                            </div>
                            <div class="control-group">
                                <div class="ms">
                                      <span id="message">
                                          <s:actionmessage/>
                                      </span>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">请输入用户名：</label>

                                <div class="controls">
                                    <input type="text" class="span8" placeholder=""  name="yhm" maxlength="30"/>
                                    <input type="hidden" name="step" value="2"/>
                                </div>
                            </div>

                            <div class="form-actions">
                                <button type="submit" class="btn btn-success">下一步</button>
                                <input type="button" value="返回" onclick="reback()" class="btn">
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
