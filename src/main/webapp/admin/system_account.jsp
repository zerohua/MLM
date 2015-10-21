<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/6/23
  Time: 下午11:08
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
    <link rel="stylesheet" type="text/css" href="../styles/system_account.css">

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
                        <a class="active teal item" href="system_account.html"><i class="archive icon"></i>管理帳號</a>
                        <a class="teal item" href="system_module.html"><i class="block layout icon"></i>模塊管理</a>
                        <a class="teal item" href="company_set.html"><i class="protect icon"></i>公司基本設定</a>
                        <a class="teal item" href="business_set.html"><i class="suitcase icon"></i>業務制度設定</a>
                    </div>
                </div>
            </div>
            <div class="thirteen wide column">
                <div class="field">
                    <h3 class="ui header">
                        <i class="archive icon"></i>

                        <div class="content">
                            管理帳號
                        </div>
                    </h3>
                </div>

                <form class="ui form">
                    <div class="ui segment">
                        <div class="three fields">
                            <div class="field">
                                <label>帳號名稱：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="manager_name" id="manager_name">
                                    <i class="cloud icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label>密碼：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="manager_pwd" id="manager_pwd">
                                    <i class="lock icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label>暱稱：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="nick_name" id="nick_name">
                                    <i class="user icon"></i>
                                </div>
                            </div>
                            <input type="hidden" name="create_name">
                            <input type="hidden" name="create_date">
                        </div>

                    </div>
                    <div class="green ui labeled icon button" id="button">
                        <i class="plus icon"></i>
                        新增
                    </div>
                </form>
                <div class="show manager">
                    <table class="ui table">
                        <thead>
                        <tr>
                            <th>帳號名稱</th>
                            <th>密碼：</th>
                            <th>暱稱：</th>
                            <th>&nbsp;</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="small ui modal">
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
            <div class="blue ui button">OK</div>
        </div>
    </div>
</div>
<!--footer begin-->
<footer>
    <%@include file="footer.html" %>
</footer>
</body>
<script src="../components/requirejs/require.js" data-main="../scripts/control/system_account"></script>

</html>