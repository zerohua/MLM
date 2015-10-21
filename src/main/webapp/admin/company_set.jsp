<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/6/23
  Time: 下午11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>公司基本設定</title>

    <link rel="stylesheet" type="text/css" href="../components/semantic-ui/dist/semantic.css">
    <link rel="stylesheet" type="text/css" href="../styles/main.css">
    <link rel="stylesheet" type="text/css" href="../styles/framework.css">
    <link rel="stylesheet" type="text/css" href="../styles/company_set.css">

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
                        <a class="active teal item" href="company_set.html"><i class="protect icon"></i>公司基本設定</a>
                        <a class="teal item" href="business_set.html"><i class="suitcase icon"></i>業務制度設定</a>
                    </div>
                </div>
            </div>
            <div class="thirteen wide column">
                <div class="field">
                    <h3 class="ui header">
                        <i class="protect icon"></i>

                        <div class="content">
                            公司基本設定
                        </div>
                    </h3>
                </div>

                <div class="ui top attached tabular menu">
                    <a class="blue active item" data-tab="pv">PV值設定</a>
                    <a class="blue item" data-tab="consume">消費參數設定</a>
                    <a class="blue item" data-tab="produce">產品設定</a>
                    <a class="blue item" data-tab="amortization">產品分期設定</a>
                    <a class="blue item" data-tab="level">階級設定</a>
                    <a class="blue item" data-tab="cycle">周期設定</a>
                    <a class="blue item" data-tab="slide">滑動周期設定</a>
                </div>
                <div class="ui active tab" data-tab="pv">
                    <form class="ui form">
                        <div class="ui segment">
                            <div class="two fields">
                                <div class="field">
                                    <label>PV 值：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="pv_value" id="pv_value" value='1' readonly="readonly">
                                        <i class="dashboard icon"></i>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>產生總獎金：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="pv_sum" id="pv_sum">
                                        <i class="money icon"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="create_name">
                        <input type="hidden" name="create_date">

                        <div class="green ui labeled icon button" id="pvbutton">
                            <i class="plus icon"></i>
                            新增
                        </div>
                    </form>

                    <div class="show pv">
                        <table class="ui table">
                            <thead>
                            <tr>
                                <th>PV 值</th>
                                <th>產生總獎金</th>
                                <th>操作人員</th>
                                <th>操作日期</th>
                                <th>&nbsp;</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <div class="ui tab" data-tab="consume">
                    <form class="ui form">
                        <div class="ui segment">
                            <div class="three fields">
                                <div class="field">
                                    <label>消費模式：</label>

                                    <div class="ui selection dropdown">
                                        <input type="hidden" name="mode" id="mode">
                                        <i class="filter icon"></i>

                                        <div class="default text">請選擇...</div>
                                        <i class="dropdown icon"></i>

                                        <div class="menu">
                                            <div class="item" data-value="common">一般消費</div>
                                            <div class="item" data-value="admission">會員入會費</div>
                                            <div class="item" data-value="jar">罐子費用</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>金額：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="money" id="money">
                                        <i class="money icon"></i>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>產生PV值：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="generation_pv" id="generation_pv">
                                        <i class="dashboard icon"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="create_name">
                        <input type="hidden" name="create_date">

                        <div class="green ui labeled icon button" id="csbutton">
                            <i class="plus icon"></i>
                            新增
                        </div>
                    </form>
                    <div class="show consume">
                        <table class="ui table">
                            <thead>
                            <tr>
                                <th>消費行為</th>
                                <th>金額</th>
                                <th>產生PV值</th>
                                <th>操作人員</th>
                                <th>操作日期</th>
                                <th>&nbsp;</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <div class="ui tab" data-tab="produce">
                    <form class="ui form">
                        <div class="ui segment">
                            <div class="three fields">
                                <div class="field">
                                    <label>方案名稱：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="pname" id="pname">
                                        <i class="edit icon"></i>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>方案售價：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="price" id="price">
                                        <i class="money icon"></i>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>PV 值：</label>

                                    <div class="ui left icon right labeled input">
                                        <input type="text" name="pv" id="pv" readonly="readonly">
                                        <i class="dashboard icon"></i>

                                        <div class="ui right label">PV</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="create_name">
                        <input type="hidden" name="create_date">

                        <div class="green ui labeled icon button" id="pbutton">
                            <i class="plus icon"></i>
                            新增
                        </div>
                    </form>
                    <div class="show produce">
                        <table class="ui table">
                            <thead>
                            <tr>
                                <th>方案名稱</th>
                                <th>方案售價</th>
                                <th>PV值</th>
                                <th>操作人員</th>
                                <th>操作日期</th>
                                <th>&nbsp;</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <div class="ui tab" data-tab="amortization">
                    <form class="ui form">
                        <div class="ui segment">
                            <div class="inline fields">
                                <label>繳清補足PV值</label>

                                <div class="field">
                                    <div class="ui radio checkbox checked">
                                        <input type="radio" name="complement" value='yes' checked="checked">
                                        <label>yes</label>
                                    </div>
                                </div>
                                <div class="field">
                                    <div class="ui radio checkbox checked">
                                        <input type="radio" name="complement" value="no">
                                        <label>no</label>
                                    </div>
                                </div>
                            </div>

                            <div class="three fields">
                                <div class=" field">
                                    <label>產品選擇：</label>

                                    <div class="ui selection dropdown" id="produce_select">
                                        <input id="produce" name="produce" type="hidden">
                                        <i class="edit icon"></i>

                                        <div class="default text">請選擇...</div>
                                        <i class="dropdown icon"></i>

                                        <div class="menu" id="produce_menu">
                                        </div>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>分期期數：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="stage" id="stage">
                                        <i class="codepen icon"></i>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>分期金額：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="stage_money" id="stage_money" readonly="readonly">
                                        <i class="money icon"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="three fields">
                                <div class="field">
                                    <label>每期產生PV值：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="stage_pv" id="stage_pv" readonly="readonly">
                                        <i class="dashboard icon"></i>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>總PV值：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="total_pv" id="total_pv" readonly="readonly">
                                        <i class="dashboard icon"></i>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>剩餘PV值：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="remain_pv" id="remain_pv" readonly="readonly">
                                        <i class="dashboard icon"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="create_name">
                        <input type="hidden" name="create_date">

                        <div class="green ui labeled icon button" id="sbutton">
                            <i class="plus icon"></i>
                            新增
                        </div>
                    </form>
                    <div class="show produce_stage">
                        <table class="ui table">
                            <thead>
                            <tr>
                                <th>分期產品</th>
                                <th>分期期數</th>
                                <th>分期金額</th>
                                <th>產生PV值</th>
                                <th>總PV值</th>
                                <th>剩餘PV值</th>
                                <th>補足PV值</th>
                                <th>&nbsp;</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <div class="ui tab" data-tab="level">
                    <form class="ui form">
                        <div class="ui segment">
                            <div class="field">
                                <label>階級名稱：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="mem_level" id="mem_level">
                                    <i class="diamond icon"></i>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="create_name">
                        <input type="hidden" name="create_date">

                        <div class="green ui labeled icon button" id="lbutton">
                            <i class="plus icon"></i>
                            新增
                        </div>
                    </form>
                    <div class="show level">
                        <table class="ui table">
                            <thead>
                            <tr>
                                <th>階級名稱</th>
                                <th>操作人員</th>
                                <th>操作日期</th>
                                <th>&nbsp;</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <div class="ui tab" data-tab="cycle">
                    <form class="ui form">
                        <div class="ui segment">
                            <div class="three fields">
                                <div class="field">
                                    <label>工作年：</label>

                                    <div class="ui selection dropdown">
                                        <input type="hidden" name="work_year" id="work_year">
                                        <i class="bar chart icon"></i>

                                        <div class="default text">請選擇...</div>
                                        <i class="dropdown icon"></i>

                                        <div class="menu">
                                            <div class="item" data-value="2015">2015 工作年</div>
                                            <div class="item" data-value="2016">2016 工作年</div>
                                            <div class="item" data-value="2017">2017 工作年</div>
                                            <div class="item" data-value="2018">2018 工作年</div>
                                            <div class="item" data-value="2019">2019 工作年</div>
                                            <div class="item" data-value="2020">2020 工作年</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="field">
                                    <label>月周期：</label>

                                    <div class="ui selection dropdown">
                                        <input type="hidden" name="weekofmonth" id="weekofmonth">
                                        <i class="angle double right icon"></i>

                                        <div class="default text">請選擇...</div>
                                        <i class="dropdown icon"></i>

                                        <div class="menu">
                                            <div class="item" data-value="4">四周</div>
                                            <div class="item" data-value="5">五周</div>
                                        </div>
                                    </div>
                                </div>

                                <div class="field">
                                    <label>周結帳日：</label>

                                    <div class="ui selection dropdown">
                                        <input type="hidden" name="closedayofweek" id="closedayofweek">
                                        <i class="calculator icon"></i>

                                        <div class="default text">請選擇...</div>
                                        <i class="dropdown icon"></i>

                                        <div class="menu">
                                            <div class="item" data-value="0">星期日</div>
                                            <div class="item" data-value="1">星期一</div>
                                            <div class="item" data-value="2">星期二</div>
                                            <div class="item" data-value="3">星期三</div>
                                            <div class="item" data-value="4">星期四</div>
                                            <div class="item" data-value="5">星期五</div>
                                            <div class="item" data-value="6">星期六</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="ui accordion field">
                            <div class="title">
                                <i class="icon dropdown"></i>
                                查看全部工作月
                            </div>
                            <div class="content fields">
                                <div class="three fields">
                                    <div class="field" id="w1">
                                        <label>第一個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="first_month" id="first_month" readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                    <div class="field" id="w2">
                                        <label>第二個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="second_month" id="second_month"
                                                   readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                    <div class="field" id="w3">
                                        <label>第三個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="third_month" id="third_month" readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="three  fields">
                                    <div class="field" id="w4">
                                        <label>第四個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="fourth_month" id="fourth_month"
                                                   readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                    <div class="field" id="w5">
                                        <label>第五個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="fifth_month" id="fifth_month" readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                    <div class="field" id="w6">
                                        <label>第六個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="sixth_month" id="sixth_month" readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="three  fields">
                                    <div class="field" id="w7">
                                        <label>第七個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="seventh_month" id="seventh_month"
                                                   readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                    <div class="field" id="w8">
                                        <label>第八個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="eighth_month" id="eighth_month"
                                                   readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                    <div class="field" id="w9">
                                        <label>第九個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="nineth_month" id="nineth_month"
                                                   readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="three  fields">
                                    <div class="field" id="w10">
                                        <label>第十個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="tenth_month" id="tenth_month" readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                    <div class="field" id="w11">
                                        <label>第十一個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="eleventh_month" id="eleventh_month"
                                                   readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                    <div class="field" id="w12">
                                        <label>第十二個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="twelfth_month" id="twelfth_month"
                                                   readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                </div>
                                <div class="three  fields">
                                    <div class="field" id="w13">
                                        <label>第十三個工作月</label>

                                        <div class="ui left icon input">
                                            <input type="text" name="thirteenth_month" id="thirteenth_month"
                                                   readonly="readonly">
                                            <i class="calendar icon"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="ui divider"></div>
                        </div>
                        <input type="hidden" name="create_name">
                        <input type="hidden" name="create_date">

                        <div class="green ui labeled icon button" id="cbutton">
                            <i class="plus icon"></i>
                            新增
                        </div>
                    </form>
                    <div class="show cycle">
                        <table class="ui celled striped table">
                            <thead>
                            <tr>
                                <th colspan="3" id="cycleth">
                                    <div class="ui dropdown" id="year_dropdown">
                                        <div class="text"></div>
                                        <i class="dropdown icon"></i>

                                        <div class="menu" id="year_menu">

                                        </div>
                                    </div>
                                </th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <div class="ui  tab" data-tab="slide">
                    <form class="ui form">
                        <div class="ui segment">
                            <div class="two fields">
                                <div class="field">
                                    <label>滑動週期：</label>

                                    <div class="ui left icon input">
                                        <input type="text" name="slide_cycle" id="slide_cycle">
                                        <i class="refresh icon"></i>
                                    </div>
                                </div>
                                <div class=" field">
                                    <label>週期起始：</label>

                                    <div class="ui selection dropdown" id="cycle_start_select">
                                        <input id="cycle_start" name="cycle_start" type="hidden">
                                        <i class="calendar icon"></i>

                                        <div class="default text">請選擇...</div>
                                        <i class="dropdown icon"></i>

                                        <div class="menu">
                                            <div class="item" data-value="date">入會日</div>
                                            <div class="item" data-value="week">入會周</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="create_name">
                        <input type="hidden" name="create_date">

                        <div class="green ui labeled icon button" id="sdbutton">
                            <i class="plus icon"></i>
                            新增
                        </div>
                    </form>
                    <div class="show slide_cycle">
                        <table class="ui table">
                            <thead>
                            <tr>
                                <th>滑動週期</th>
                                <th>週期起始</th>
                                <th>操作人員</th>
                                <th>操作日期</th>
                                <th>&nbsp;</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
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

<script src="../components/requirejs/require.js" data-main="../scripts/control/company_set"></script>

</html>
