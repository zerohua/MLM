<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/6/24
  Time: 上午12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>業務制度設定</title>

    <link rel="stylesheet" type="text/css" href="../components/semantic-ui/dist/semantic.css">
    <link rel="stylesheet" type="text/css" href="../styles/main.css">
    <link rel="stylesheet" type="text/css" href="../styles/framework.css">
    <link rel="stylesheet" type="text/css" href="../styles/business_set.css">

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
                        <a class="teal item" href="system_account.html"><i class="archive icon"></i>管理帳號</a>
                        <a class="teal item" href="system_module.html"><i class="block layout icon"></i>模塊管理</a>
                        <a class="teal item" href="company_set.html"><i class="protect icon"></i>公司基本設定</a>
                        <a class="active teal item" href="business_set.html"><i class="suitcase icon"></i>業務制度設定</a>
                    </div>
                </div>
            </div>
            <div class="thirteen wide column">
                <div class="field">
                    <h3 class="ui header">
                        <i class="suitcase icon"></i>

                        <div class="content">
                            業務制度設定
                        </div>
                    </h3>
                </div>
                <div class="ui top attached tabular menu">
                    <a class="blue active item" data-tab="bonus">獎金制度設定</a>
                    <a class="blue item" data-tab="bump">對碰制度設定</a>
                    <a class="blue item" data-tab="upgrade">階級晉升設定</a>
                </div>
                <div class="ui active tab" data-tab="bonus">
                    <form class="ui form">
                        <div class="three fields">
                            <div class="field">
                                <label>直推獎金：</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="總獎金" readonly="readonly">
                                    <input type="text" name="recommend_bonus" id="recommend_bonus" class="bonus_input">
                                    <i class="dollar icon"></i>

                                    <div class="ui label">
                                        <div class="text">%</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>組織獎金：</label>

                                <div class="ui  small right labeled left icon input">
                                    <input type="text" value="總獎金" readonly="readonly">
                                    <input type="text" name="bump_bonus" id="bump_bonus" class="bonus_input">
                                    <i class="dollar icon"></i>

                                    <div class="ui label">
                                        <div class="text">%</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>對等獎金：</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="總獎金" readonly="readonly">
                                    <input type="text" name="equal_bonus" id="equal_bonus" class="bonus_input">
                                    <i class="dollar icon"></i>

                                    <div class="ui label">
                                        <div class="text">%</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="three fields">
                            <div class="field">
                                <label>處長階級獎金：</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="每月達" readonly="readonly">
                                    <input type="text" name="director_pv" id="director_pv" class="bonus_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">PV</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>相對津貼:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" name="director_bonus" id="director_bouns" class="bonus_input">
                                    <i class="money icon"></i>

                                    <div class="ui label">
                                        <div class="text">元</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>晉升獎金:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" name="up_director_bonus" id="up_director_bonus"
                                           class="bonus_input">
                                    <i class="money icon"></i>

                                    <div class="ui label">
                                        <div class="text">元</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="three fields">
                            <div class="field">
                                <label>區長階級獎金：</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="每月達" readonly="readonly">
                                    <input type="text" name="cdo_pv" id="cdo_pv" class="bonus_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">PV</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>相對津貼:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" name="cdo_bonus" id="cdo_bouns" class="bonus_input">
                                    <i class="money icon"></i>

                                    <div class="ui label">
                                        <div class="text">元</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>晉升獎金:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" name="up_cdo_bonus" id="up_cdo_bonus" class="bonus_input">
                                    <i class="money icon"></i>

                                    <div class="ui label">
                                        <div class="text">元</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="three fields">
                            <div class="field">
                                <label>部長階級獎金：</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="每月達" readonly="readonly">
                                    <input type="text" name="minister_pv" id="minister_pv" class="bonus_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">PV</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>相對津貼:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" name="minister_bonus" id="minister_bonus" class="bonus_input">
                                    <i class="money icon"></i>

                                    <div class="ui label">
                                        <div class="text">元</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>晉升獎金:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" name="up_minister_bonus" id="up_minister_bonus"
                                           class="bonus_input">
                                    <i class="money icon"></i>

                                    <div class="ui label">
                                        <div class="text">元</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="three fields">
                            <div class="field">
                                <label>營運長階級獎金：</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="每月達" readonly="readonly">
                                    <input type="text" name="coo_pv" id="coo_pv" class="bonus_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">PV</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>相對津貼:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" name="coo_bonus" id="coo_bonus" class="bonus_input">
                                    <i class="money icon"></i>

                                    <div class="ui label">
                                        <div class="text">元</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>晉升獎金:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" name="up_coo_bonus" id="up_coo_bonus" class="bonus_input">
                                    <i class="money icon"></i>

                                    <div class="ui label">
                                        <div class="text">元</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="create_name">
                        <input type="hidden" name="create_date">

                        <div class="green ui labeled icon button" id="nbonus_btn">
                            <i class="plus icon"></i>
                            新增
                        </div>
                        <div style="display: none" class="green ui labeled icon button" id="ebonus_btn">
                            <i class="edit icon"></i>
                            修改
                        </div>
                    </form>
                </div>
                <div class="ui tab" data-tab="bump">
                    <form class="ui form">
                        <div class="ui segment">
                            <div class="two fields">
                                <div class="field">
                                    <label>周對碰單邊封頂值:</label>

                                    <div class="ui  right labeled left icon input">
                                        <input type="text" name="week_limit" id="week_limit" class="bump_input">
                                        <i class="minus circle icon"></i>

                                        <div class="ui label">
                                            <div class="text">PV</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>月對碰單邊封頂值:</label>

                                    <div class="ui  right labeled left icon input">
                                        <input type="text" name="month_limit" id="month_limit" class="bump_input">
                                        <i class="minus circle icon"></i>

                                        <div class="ui label">
                                            <div class="text">PV</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="create_name">
                        <input type="hidden" name="create_date">

                        <div class="green ui labeled icon button" id="nbump_btn">
                            <i class="plus icon"></i>
                            新增
                        </div>
                        <div style="display: none" class="green ui labeled icon button" id="ebump_btn">
                            <i class="edit icon"></i>
                            修改
                        </div>
                    </form>
                </div>
                <div class="ui tab" data-tab="upgrade">
                    <form class="ui form">
                        <div class="two fields">
                            <div class="field">
                                <label>晉升處長條件(滑動):</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="八周內小邊不低於" readonly="readonly">
                                    <input type="text" name="director_slide_mini" id="director_slide_mini"
                                           class="upgrade_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">PV</div>
                                    </div>
                                </div>

                            </div>
                            <div class="field">
                                <label>*</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="兩邊總和達" readonly="readonly">
                                    <input type="text" name="director_slide_total" id="director_slide_total"
                                           class="upgrade_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">PV</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>晉升處長條件(不滑動):</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="小邊不低於" readonly="readonly">
                                    <input type="text" name="director_mini" id="director_mini"
                                           class="upgrade_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">PV</div>
                                    </div>
                                </div>

                            </div>
                            <div class="field">
                                <label>*</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="兩邊總和達" readonly="readonly">
                                    <input type="text" name="director_total" id="director_total"
                                           class="upgrade_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">PV</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>晉升區長條件:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="右線必須有" readonly="readonly">
                                    <input type="text" name="cdo_rside_director" id="cdo_rside_director"
                                           class="upgrade_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">處長</div>
                                    </div>
                                </div>

                            </div>
                            <div class="field">
                                <label>*</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="左線必須有" readonly="readonly">
                                    <input type="text" name="cdo_lside_director" id="cdo_lside_director"
                                           class="upgrade_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">處長</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>晉升部長條件:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="右線必須有" readonly="readonly">
                                    <input type="text" name="minister_rside_cdo" id="minister_rside_cdo"
                                           class="upgrade_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">區長</div>
                                    </div>
                                </div>

                            </div>
                            <div class="field">
                                <label>*</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="左線必須有" readonly="readonly">
                                    <input type="text" name="minister_lside_cdo" id="minister_lside_cdo"
                                           class="upgrade_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">區長</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="two fields">
                            <div class="field">
                                <label>晉升營運長條件:</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="右線必須有" readonly="readonly">
                                    <input type="text" name="coo_rside_minister" id="coo_rside_minister"
                                           class="upgrade_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">部長</div>
                                    </div>
                                </div>

                            </div>
                            <div class="field">
                                <label>*</label>

                                <div class="ui small right labeled left icon input">
                                    <input type="text" value="左線必須有" readonly="readonly">
                                    <input type="text" name="coo_lside_minister" id="coo_lside_minister"
                                           class="upgrade_input">
                                    <i class="announcement icon"></i>

                                    <div class="ui label">
                                        <div class="text">部長</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="create_name">
                        <input type="hidden" name="create_date">

                        <div class="green ui labeled icon button" id="nupgrade_btn">
                            <i class="plus icon"></i>
                            新增
                        </div>
                        <div style="display: none" class="green ui labeled icon button" id="eupgrade_btn">
                            <i class="edit icon"></i>
                            修改
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="small ui modal">

    <div class="header">
        確認訊息
    </div>
    <div class="content">
        <div class="image">
        </div>
        <div class="description">
        </div>
    </div>
    <div class="actions">
        <div class="blue ui approve button">OK</div>
    </div>
</div>
</div>
<!--footer begin-->
<footer>
    <%@include file="footer.html" %>
</footer>
</body>
<!--build:js ../scripts/business_set.js-->
<script src="../components/requirejs/require.js" data-main="../scripts/control/business_set"></script>
<!--/build-->
</html>
