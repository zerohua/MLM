<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/6/23
  Time: 下午11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>business</title>

    <link rel="stylesheet" type="text/css" href="../components/semantic-ui/dist/semantic.css">
    <link rel="stylesheet" type="text/css" href="../styles/main.css">
    <link rel="stylesheet" type="text/css" href="../styles/framework.css">

</head>
<body>
<div class="page">
    <%@include file="header.html" %>

    <!--the main content begin-->
    <div class="container">
        <div class="ui grid">
            <!--the vertical menu-->
            <div class="three wide column" id="vMenu">
                <div class="verticalMenu">
                    <div class="ui vertical pointing menu fluid">
                        <a class="teal item" href="system_account.html"><i class="archive icon"></i>管理帳號</a>
                        <a class="active teal item" href="system_module.html"><i class="block layout icon"></i>模塊管理</a>
                        <a class="teal item" href="company_set.html"><i class="protect icon"></i>公司基本設定</a>
                        <a class="teal item" href="business_set.html"><i class="suitcase icon"></i>業務制度設定</a>
                    </div>
                </div>
            </div>
            <div class="thirteen wide column">

            </div>
        </div>
    </div>
</div>
<!--footer begin-->
<footer>
    <%@include file="footer.html" %>
</footer>
</body>
<script src="../components/requirejs/require.js" data-main="../scripts/control/system_module"></script>
</html>