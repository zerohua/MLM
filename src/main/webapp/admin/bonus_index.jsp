<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/7/11
  Time: 下午2:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>獎金查詢</title>

    <link rel="stylesheet" type="text/css" href="../components/semantic-ui/dist/semantic.css">
    <link rel="stylesheet" type="text/css" href="../styles/main.css">
    <link rel="stylesheet" type="text/css" href="../styles/framework.css">
</head>
<body>
<div class="page">
    <%@include file="header.html" %>

    <!--the main content begin-->
    <div class="container">
        <!--the content-->
        <div class="ui grid">
            <!--the vertical menu-->
            <div class="three wide column" id="vMenu">
                <div class="verticalMenu">
                    <div class="ui vertical pointing menu fluid">
                        <a class="teal item" href="member_search.html"><i class="user icon"></i>會員查詢</a>
                        <a class="teal item" href="org_search.html"><i class="sitemap icon"></i>組織查詢</a>
                        <a class="teal item" href="project_search.html"><i class="file icon"></i>專案查詢</a>
                        <a class="active teal item" href="bonus_index.html"><i class="money icon"></i>獎金查詢</a>
                    </div>
                </div>
            </div>
            <div class="thirteen wide column">
                <div class="field">
                    <h3 class="ui header" id="header_field">
                        <i class="money icon"></i>

                        <div class="content">
                            獎金查詢
                        </div>
                    </h3>
                </div>

            </div>
        </div>
    </div>
</div>
<footer>
    <%@include file="footer.html" %>
</footer>
</body>
d
<script src="../components/requirejs/require.js" data-main="../scripts/control/bonus_index"></script>
</html>
