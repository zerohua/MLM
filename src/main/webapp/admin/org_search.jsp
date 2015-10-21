<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/6/22
  Time: 上午12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>search</title>

    <link rel="stylesheet" type="text/css" href="../components/semantic-ui/dist/semantic.css">
    <link rel="stylesheet" type="text/css" href="../styles/main.css">
    <link rel="stylesheet" type="text/css" href="../styles/framework.css">
    <link rel="stylesheet" type="text/css" href="../styles/org_search.css">

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
                        <a class="active teal item" href="org_search.html"><i class="sitemap icon"></i>組織查詢</a>
                        <a class="teal item" href="project_search.html"><i class="file icon"></i>專案查詢</a>
                        <a class="teal item" href="bonus_index.html"><i class="money icon"></i>獎金查詢</a>
                    </div>
                </div>
            </div>
            <div class="thirteen wide column">
                <div class="field" id="header_field">
                    <h3 class="ui header">
                        <i class="sitemap icon"></i>

                        <div class="content">
                            組織查詢
                        </div>
                    </h3>
                </div>
                <div class="field" id="list_search_field">
                    <div class="ui icon input">
                        <input type="text" name="list_search" placeholder="編號,姓名,推薦,上線..." id="list_search">
                        <i class="search icon"></i>
                    </div>
                </div>
                <div class="field" id="tree_search_field">
                    <div class="ui action input">
                        <input type="text" name="tree_search" placeholder="編號，姓名..." id="tree_search">
                        <button class="ui icon button" id="tree_search_button">
                            <i class="search icon"></i>
                        </button>
                    </div>
                </div>

                <div class="ui pointing secondary menu" id="tab_menu">
                    <a class="blue active item" data-tab="left_list">左組織列表</a>
                    <a class="blue item" data-tab="right_list">右組織列表</a>
                    <a class="blue item" data-tab="binary_tree">樹狀組織圖</a>
                </div>
                <div class="ui active tab" data-tab="left_list">
                    <div class="show left_list">
                        <table class="ui celled table">
                            <thead>
                            <tr>
                                <th>代數</th>
                                <th>會員編號</th>
                                <th>職位</th>
                                <th>姓名</th>
                                <th>推薦人</th>
                                <th>安置上線</th>
                                <th>左右</th>
                                <th>會員狀態</th>
                                <th>對碰</th>
                                <th>加入日期</th>
                            </tr>
                            </thead>
                        </table>
                        <div class="content"></div>
                    </div>
                    <div class="ui teal inverted borderless pagination menu" id="left_pagination">

                    </div>
                </div>
                <div class="ui active loader"></div>
                <div class="ui tab" data-tab="right_list">

                    <div class="show left_list">
                        <table class="ui celled table">
                            <thead>
                            <tr>
                                <th>代數</th>
                                <th>會員編號</th>
                                <th>職位</th>
                                <th>姓名</th>
                                <th>推薦人</th>
                                <th>安置上線</th>
                                <th>左右</th>
                                <th>會員狀態</th>
                                <th>對碰</th>
                                <th>加入日期</th>
                            </tr>
                            </thead>
                        </table>

                    </div>

                    <div class="ui teal inverted borderless pagination menu" id="right_pagination">

                    </div>

                </div>
                <div class="ui tab" data-tab="binary_tree">
                    <div class="show_binaryTree">

                    </div>
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
<script src="../components/requirejs/require.js" data-main="../scripts/control/org_search"></script>
</html>
