<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/6/21
  Time: 下午7:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>index</title>

    <link rel="stylesheet" type="text/css" href="../components/semantic-ui/dist/semantic.css">
    <link rel="stylesheet" type="text/css" href="../styles/main.css">
    <link rel="stylesheet" type="text/css" href="../styles/framework.css">
    <link rel="stylesheet" type="text/css" href="../styles/index.css">
</head>
<body>
<div class="page">

    <%@include file="header.html" %>

    <!--the main content begin-->
    <div class="container">
        <div class="ui warning message">
            <i class="close icon" data-dismiss="alert"></i>

            <div class="header">
                <i class="warning icon"></i> 上周更新事項：
            </div>
            1.資料查詢區塊 ﹣ 獎金查詢選項:為加快查詢速度和減輕server負擔，更改為緩存查詢，由server統一更新資料，查詢到的為前一天資料。<br/>
            2.會員新增和專案新增合併為同一頁面，！！！！新增會員時必須先選擇一般會員或專案會員！！！！！。<br/>


        </div>
        <div class="ui statistics">
            <div class="statistic">
                <div class="value" id="memberCount">
                    <i class="users icon"></i> 0
                </div>
                <div class="label">
                    會員總數
                </div>
            </div>
            <div class="green statistic">
                <div class="value">
                    <i class="users icon"></i> 0
                </div>
                <div class="label">
                    處長總數
                </div>
            </div>
            <div class="blue statistic">
                <div class="value">
                    <i class="users icon"></i> 0
                </div>
                <div class="label">
                    區長總數
                </div>
            </div>
            <div class="orange statistic">
                <div class="value">
                    <i class="users icon"></i> 0
                </div>
                <div class="label">
                    部長總數
                </div>
            </div>
            <div class="purple statistic">
                <div class="value">
                    <i class="users icon"></i> 0
                </div>
                <div class="label">
                    營運長總數
                </div>
            </div>
        </div>
        <div class="ui statistics">
            <div class="statistic">
                <div class="value">
                    <i class="users icon"></i> 0
                </div>
                <div class="label">
                    入會總數
                </div>
            </div>
            <div class="yellow statistic">
                <div class="value">
                    <i class="diamond icon"></i> 0
                </div>
                <div class="label">
                    罐子
                </div>
            </div>
            <div class="pink statistic">
                <div class="value">
                    <i class="diamond icon"></i> 0
                </div>
                <div class="label">
                    56000 方案
                </div>
            </div>
            <div class="red statistic">
                <div class="value">
                    <i class="diamond icon"></i> 0
                </div>
                <div class="label">
                    112000 方案
                </div>
            </div>
            <div class="teal statistic">
                <div class="value">
                    <i class="diamond icon"></i> 0
                </div>
                <div class="label">
                    168000 方案
                </div>
            </div>
        </div>
    </div>
</div>
<!--footer begin-->
<footer>
    <%@include file="footer.html" %>
</footer>
</body>
<script src="../components/requirejs/require.js" data-main="../scripts/control/index"></script>
</html>
