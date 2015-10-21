<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/6/23
  Time: 下午9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>分期專案管理</title>

  <link rel="stylesheet" type="text/css" href="../components/semantic-ui/dist/semantic.css">
  <link rel="stylesheet" type="text/css" href="../styles/main.css">
  <link rel="stylesheet" type="text/css" href="../styles/framework.css">
  <link rel="stylesheet" type="text/css" href="../styles/account_stage.css">

</head>
<body>
<div class="page">

    <%@include file="header.html" %>

  <div class="container">
    <!--the content-->
    <div class="ui grid">
      <!--the vertical menu-->
      <div class="three wide column" id="vMenu">
        <div class="verticalMenu">
          <div class="ui vertical pointing menu fluid">
            <a class="teal item" href="account_manger.html"><i class="calculator icon"></i>帳務結帳管理</a>
            <a class="active teal item" href="account_stage.html"><i class="calendar icon"></i>分期帳務管理</a>
          </div>
        </div>
      </div>
      <div class="thirteen wide column">
        <div class="field">
          <h3 class="ui header">
            <i class="calculator icon"></i>

            <div class="content">
              分期帳務管理
            </div>
          </h3>
          <form class="ui form">
            <div class="three fields">
              <div class="field">
                <label>搜尋未結帳專案：</label>

                <div class="ui search selection dropdown" id="project_search">
                  <input type="hidden" name="project_no" id="project_no">
                  <!--<i class="user icon"></i>-->

                  <div class="default text">請選擇會員或專案編號</div>
                  <i class="dropdown icon"></i>

                  <div class="menu" id="project_menu">

                  </div>
                </div>
              </div>
            </div>
            <div class="green ui labeled icon button" id="button">
              <i class="search icon"></i>
              搜尋
            </div>
          </form>
          <div  class="show project">
            <table style='display:none' class="ui table">
              <thead>
              <tr>
                <th>專案號碼</th>
                <th>會員編號</th>
                <th>專案名稱</th>
                <th>專案金額</th>
                <th>專案分期</th>
                <th>專案帳務</th>
              </tr>
              </thead>
            </table>
          </div>
          <div style='display:none' class="green  ui labeled icon button" id="update">
            <i class="upload icon"></i>
            更新
          </div>
          <div style='display:none' class="green  ui labeled icon button" id="supdate">
            <i class="upload icon"></i>
            更新
          </div>
        </div>

      </div>
    </div>
  </div>
</div>
<div class="ui small modal">
  <i class="close icon"></i>

  <div class="header">
    確認訊息
  </div>
  <div class="content">
    <div class="image">
      <!--<i class = " green check circle icon"></i>-->
    </div>
    <div class="description">
      <!--會員帳號新增成功-->
    </div>
  </div>
  <div class="actions">
    <div class="blue ui approve button">OK</div>
  </div>
</div>
<!--footer begin-->
<footer>
    <%@include file="footer.html" %>
</footer>
</body>
<script src="../components/requirejs/require.js" data-main="../scripts/control/account_stage"></script>
</html>
