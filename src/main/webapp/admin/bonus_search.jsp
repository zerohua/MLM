<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/6/22
  Time: 下午8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>獎金查詢</title>

  <link rel="stylesheet" type="text/css" href="../components/semantic-ui/dist/semantic.css">
  <link rel="stylesheet" type="text/css" href="../styles/main.css">
  <link rel="stylesheet" type="text/css" href="../styles/framework.css">
  <link rel="stylesheet" type="text/css" href="../styles/bonus_search.css">

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
            <a class="active teal item" href="bonus_search.html"><i class="money icon"></i>獎金查詢</a>
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
        <div class="field" id="search_field">
          <div class="ui icon input">
            <input type="text" name="search" placeholder="會員編號，姓名..." id="search">
            <i class="search icon"></i>
          </div>
        </div>
        <div class="show bonus_list">
          <table class="ui celled table">
            <thead>
            <tr>
              <th>會員編號</th>
              <th>姓名</th>
              <th>直推獎金</th>
              <th>組織獎金</th>
              <th>對等獎金</th>
            </tr>
            </thead>

          </table>
        </div>

        <div class="ui teal inverted borderless pagination menu" id="pagination">

        </div>
        <div class="ui active loader"></div>
      </div>


    </div>
  </div>
</div>
<!--footer begin-->
<footer>
    <%@include file="footer.html" %>
</footer>
</body>
<!--build:js ../scripts/bonus_search.js-->
<script src="../components/requirejs/require.js" data-main="../scripts/control/bonus_search"></script>
<!--/build -->

</html>