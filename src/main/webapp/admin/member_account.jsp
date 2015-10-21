<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/6/21
  Time: 下午9:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>會員資料新增</title>

    <link rel="stylesheet" type="text/css" href="../components/semantic-ui/dist/semantic.css">
    <link rel="stylesheet" type="text/css" href="../styles/main.css">
    <link rel="stylesheet" type="text/css" href="../styles/framework.css">
    <link rel="stylesheet" type="text/css" href="../styles/member_account.css">

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
                        <a class="active teal item" href="member_account.html"><i class="add user icon"></i>會員資料新增</a>
                    </div>
                </div>
            </div>
            <div class="thirteen wide column">
                <div class="field">
                    <h3 class="ui header">
                        <i class="add user icon"></i>

                        <div class="content">
                            會員資料新增
                        </div>
                    </h3>
                </div>
                <form class="ui form">
                    <div class="ui raised segment">
                        <div class="ui blue ribbon label">
                            <i class="red asterisk icon"></i>必填區塊
                        </div>
                        <div class="inline field" id="member_date_field">
                            <label>新增日期</label>
                            <i class="calendar icon"></i>
                            <input style="border:0;" type="date" name="create_date" id="create_date">
                        </div>
                        <div class="inline fields">
                            <div class="field">
                                <i class="flag icon"></i>

                                <div class="ui radio checkbox" id="status_normal">
                                    <input type="radio" name="status" value="normal" checked="checked">
                                    <label>一般</label>
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui radio checkbox" id="status_project">
                                    <input type="radio" name="status" value="project">
                                    <label>專案</label>
                                </div>
                            </div>
                        </div>
                        <div class="three fields">
                            <div class="field">
                                <label>滑動八周設定</label>

                                <div class="ui selection dropdown" id="slide_selection">
                                    <input type="hidden" name="slide" value="yes" id="slide">
                                    <i class="refresh icon"></i>

                                    <div class="default text">請選擇...</div>
                                    <i class="dropdown icon"></i>

                                    <div class="menu">
                                        <div class="item" data-value="yes">滑動</div>
                                        <div class="item" data-value="no">不滑動</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>推薦人</label>

                                <div class="ui search selection dropdown" id="recommend_selection">
                                    <input type="hidden" name="recommend" id="recommend">

                                    <div class="default text">請選擇推薦人會員編號</div>
                                    <i class="dropdown icon"></i>

                                    <div class="menu" id="recommend_menu">
                                    </div>
                                </div>

                            </div>
                            <div class="field">
                                <label>安置上線</label>

                                <div class="ui search selection dropdown" id="upper_selection">
                                    <input type="hidden" name="upper" id="upper">

                                    <div class="default text">請選擇上線會會員編號</div>
                                    <i class="dropdown icon"></i>

                                    <div class="menu" id="upper_menu">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="three fields">

                            <div class="field">
                                <label>安置位置</label>

                                <div class="ui selection dropdown" id="position_selection">
                                    <input type="hidden" name="position" id="position">
                                    <i class="users icon"></i>

                                    <div class="default text">請選擇...</div>
                                    <i class="dropdown icon"></i>


                                    <div class="menu" id="position_menu">
                                        <div class="item" data-value="left_lower" id="left_item">左線</div>
                                        <div class="item" data-value="right_lower" id="right_item">右線</div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>會員歸屬地</label>

                                <div class="ui selection dropdown" id="location_selection">
                                    <input type="hidden" id="location">
                                    <i class="location arrow icon"></i>

                                    <div class="default text">請選擇...</div>
                                    <i class="dropdown icon"></i>

                                    <div class="menu">
                                        <div class="item" data-value="TP">台北</div>
                                        <div class="item" data-value="TG">台中</div>
                                        <div class="item" data-value="KA">高雄</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ui raised segment">
                        <div class="three fields">
                            <div class="field">
                                <label>會員編號</label>

                                <div class="ui left icon input">
                                    <input type="text" name="member_no" id="member_no" readonly="readonly">
                                    <i class="cloud icon"></i>

                                    <div class="ui corner label">
                                        <i class="red asterisk icon"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>會員密碼</label>

                                <div class="ui left icon input">
                                    <input type="text" name="member_pwd" placeholder="會員登入密碼" id="member_pwd">
                                    <i class="lock icon"></i>

                                    <div class="ui corner label">
                                        <i class="red asterisk icon"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <label>姓名</label>

                                <div class="ui left icon input">
                                    <input type="text" name="name" placeholder="請輸入完整姓名" id="name">
                                    <i class="user icon"></i>

                                    <div class="ui corner label">
                                        <i class="red asterisk icon"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="three fields">

                            <div class="field">
                                <label>身份證號碼</label>

                                <div class="ui left icon input">
                                    <input type="text" name="id" placeholder="請輸入身份證號碼(10碼)" id="id">
                                    <i class="star icon"></i>


                                </div>
                            </div>
                            <div class="field">
                                <label>生日</label>

                                <div class="ui left icon input">
                                    <input type="date" name="birthday" id="birthday">
                                    <i class="gift icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label>性別</label>

                                <div class="ui selection dropdown">
                                    <input type="hidden" name="sex" id="sex">
                                    <i class="heterosexual icon"></i>

                                    <div class="default text">請選擇...</div>
                                    <i class="dropdown icon"></i>

                                    <div class="menu">
                                        <div class="item" data-value="男">男性</div>
                                        <div class="item" data-value="女">女性</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="three fields">

                            <div class="field">
                                <label>手機號碼</label>

                                <div class="ui left icon input">
                                    <input type="text" name="mobile" placeholder="格式為 09xxxxxxxx" id="mobile">
                                    <i class="call icon"></i>

                                </div>
                            </div>
                            <div class="field">
                                <label>室內電話</label>

                                <div class="ui left icon input">
                                    <input type="text" name="tel" placeholder="格式為 區域碼＋電話號碼" id="tel">
                                    <i class="text telephone icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label>E-MAIL</label>

                                <div class="ui left icon input">
                                    <input type="text" name="email" placeholder="格式為 email賬號@主機名" id="email">
                                    <i class="mail icon"></i>
                                </div>
                            </div>
                        </div>
                        <div class="fields">
                            <div class="five wide field">
                                <label>銀行帳戶名稱</label>

                                <div class="ui left icon input">
                                    <input type="text" name="account_name" placeholder="請輸入銀行戶名" id="account_name">
                                    <i class="terminal icon"></i>
                                </div>
                            </div>
                            <div class="four wide field">
                                <label>銀行代碼選擇</label>

                                <div class="ui selection dropdown">
                                    <input id="bank_code" type="hidden">
                                    <i class="building icon"></i>

                                    <div class="default text">請選擇...</div>
                                    <i class="dropdown icon"></i>

                                    <div class="menu" id="code_item">
                                    </div>
                                </div>
                            </div>
                            <div class="seven wide field">
                                <label>銀行帳號</label>

                                <div class="ui input">
                                    <input id="bank_account" name="bank_account" type="text" placeholder="請輸入帳號">
                                </div>
                            </div>
                        </div>
                        <div class="fields">
                            <div class="four wide field">
                                <label>縣市選擇</label>

                                <div class="ui selection dropdown">
                                    <input id="city" name="city" type="hidden">
                                    <i class="marker icon"></i>

                                    <div class="default text">請選擇...</div>
                                    <i class="dropdown icon"></i>

                                    <div class="menu">
                                        <div class="item" data-value="台北市">台北市</div>
                                        <div class="item" data-value="基隆市">基隆市</div>
                                        <div class="item" data-value="新北市">新北市</div>
                                        <div class="item" data-value="宜蘭縣">宜蘭縣</div>
                                        <div class="item" data-value="新竹縣">新竹縣</div>
                                        <div class="item" data-value="桃園縣">桃園縣</div>
                                        <div class="item" data-value="苗栗縣">苗栗縣</div>
                                        <div class="item" data-value="台中市">台中市</div>
                                        <div class="item" data-value="彰化縣">彰化縣</div>
                                        <div class="item" data-value="南投縣">南投縣</div>
                                        <div class="item" data-value="嘉義縣">嘉義縣</div>
                                        <div class="item" data-value="雲林縣">雲林縣</div>
                                        <div class="item" data-value="台南市">台南市</div>
                                        <div class="item" data-value="高雄市">高雄市</div>
                                        <div class="item" data-value="澎湖縣">澎湖縣</div>
                                        <div class="item" data-value="屏東縣">屏東縣</div>
                                        <div class="item" data-value="台東縣">台東縣</div>
                                        <div class="item" data-value="花蓮縣">花蓮縣</div>
                                        <div class="item" data-value="金門縣">金門縣</div>
                                        <div class="item" data-value="連江縣">連江縣</div>
                                        <div class="item" data-value="南海諸島">南海諸島</div>
                                    </div>
                                </div>
                            </div>
                            <div class="four wide field">
                                <label>區域選擇</label>

                                <div class="ui selection dropdown" id="area_select">
                                    <input id="area" name="area" type="hidden">

                                    <div class="default text">請選擇...</div>
                                    <i class="dropdown icon"></i>

                                    <div class="menu" id="area_item">
                                    </div>
                                </div>
                            </div>
                            <div class="eight wide field">
                                <label>通訊地址</label>

                                <input type="text" id="address" name="address">
                            </div>
                        </div>
                        <div class="field">
                            <label>備註</label>
                            <textarea name="note" placeholder="最多可輸入200個字....."></textarea>
                        </div>
                    </div>
                    <div class="ui raised segment">
                        <div class="fields">
                            <div class="field">
                                <div style="display:none;" class="inline fields" id="installment">
                                    <label>分期付款</label>

                                    <div class="field">
                                        <div class="ui radio checkbox checked" id="stage_yes">
                                            <input type="radio" name="installment" value='yes'>
                                            <label>yes</label>
                                        </div>
                                    </div>
                                    <div class="field">
                                        <div class="ui radio checkbox checked" id="stage_no">
                                            <input type="radio" name="installment" value="no" checked="checked">
                                            <label>no</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="field">
                                <div class="inline fields" id="project_pay_field">
                                    <label>專案費用</label>

                                    <div class="field" id="pay_no_field">
                                        <div class="ui radio checkbox checked" id="checkbox_pay_yes">
                                            <input type="radio" name="pay" value='yes' checked="checked">
                                            <label>已結</label>
                                        </div>
                                    </div>
                                    <div class="field" id="pay_yes_field">
                                        <div class="ui radio checkbox checked" id="checkbox_pay_no">
                                            <input type="radio" name="pay" value="no">
                                            <label>未結</label>
                                        </div>
                                    </div>
                                    <div style="display:none;" class="field" id="pay_stage_field">
                                        <div class="ui radio checkbox checked" id="checkbox_pay_stage">
                                            <input type="radio" name="pay" value="stage">
                                            <label>分期</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="field" id="project_payMethod_field">
                                <div class=" inline fields">
                                    <label>付款方式</label>

                                    <div class="field">
                                        <div class="ui radio checkbox checked">
                                            <input type="radio" name="pay_method" value='credit'>
                                            <label>信用卡</label>
                                        </div>
                                    </div>
                                    <div class="field">
                                        <div class="ui radio checkbox checked">
                                            <input type="radio" name="pay_method" value="cash" checked="checked">
                                            <label>現金</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="four fields">
                            <div class=" field" id="project_no_field">
                                <label>專案號碼：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="project_no" id="project_no" readonly="readonly">
                                    <i class="crop icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label>專案名稱：</label>

                                <div class="ui selection dropdown" id="produce_select">
                                    <input type="hidden" name="pname" id="pname">
                                    <i class="edit icon"></i>

                                    <div class="default text">請選擇..</div>
                                    <i class="dropdown icon"></i>

                                    <div class="menu" id="produce_menu">

                                    </div>
                                </div>
                            </div>
                            <div class="field" id="project_price_field">
                                <label>金額：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="price" id="price" readonly="readonly">
                                    <i class="money icon"></i>
                                </div>
                            </div>

                            <div class="field" id="pv_field">
                                <label>產生PV值：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="pv" id="pv" readonly="readonly">
                                    <i class="dashboard icon"></i>
                                </div>
                            </div>
                        </div>
                        <div style="display:none;" class="four fields" id="stage_field">
                            <div class="field">
                                <label>分期期數：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="stage" id="stage" readonly="readonly">
                                    <i class="codepen icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label>分期起始月：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="stage_star" id="stage_star" readonly="readonly">
                                    <i class="calendar icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label>分期結束月：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="stage_end" id="stage_end" readonly="readonly">
                                    <i class="calendar icon"></i>
                                </div>
                            </div>
                            <div class="field">
                                <label>最後一期PV值：</label>

                                <div class="ui left icon input">
                                    <input type="text" name="last_pv" id="last_pv" readonly="readonly">
                                    <i class="dashboard icon"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="create_name">

                    <div class="ui buttons">
                        <div class="ui animated fade button" id="reset">
                            <div class="visible content">取消</div>
                            <div class="hidden content">
                                <i class="icon undo"></i>
                                清除
                            </div>
                        </div>
                        <div class="or"></div>
                        <div class="ui green animated fade submit button" id="submit">
                            <div class="visible content">註冊</div>
                            <div class="hidden content">
                                <i class="icon write"></i>
                                提交
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="ui small modal">
    <%--<i class="close icon"></i>--%>
    <div class="header">
        確認訊息
    </div>
    <div class="content">
        <div class="image">
            <%--<i class = "green check circle icon"></i>--%>
        </div>
        <div class="description">
            <%--<p>會員帳號新增成功</p>--%>
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
<script src="../components/requirejs/require.js" data-main="../scripts/control/member_account"></script>
</html>
