<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>页面加载出错</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" media="screen" href="css/screen.core.css"/>
    <link rel="stylesheet" media="only screen and (min-width: 960px)" href="css/screen.960.css"/>
    <link href="css/common.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
        function next() {
            window.location.href = "login.jsp";


        }

    </script>
</head>

<body>

<div id="welcome_bar">页面出错</div>

<form action="loginValidator" method="post" id="login" name="login">


    <div class="key"><s:property value="exception.message"/><br>
    </div>
    <div class="value">
        请点击此处<s:a href="javascript:window.history.back(-1);">返回上页</s:a>
    </div>

</form>

</body>
</html>
