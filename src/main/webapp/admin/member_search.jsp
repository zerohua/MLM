<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 15/6/21
  Time: 下午11:52
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
    <link rel="stylesheet" type="text/css" href="../styles/member_search.css">

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
                        <a class="active teal item" href="member_search.html"><i class="user icon"></i>會員查詢</a>
                        <a class="teal item" href="org_search.html"><i class="sitemap icon"></i>組織查詢</a>
                        <a class="teal item" href="project_search.html"><i class="file icon"></i>專案查詢</a>
                        <a class="teal item" href="bonus_index.html"><i class="money icon"></i>獎金查詢</a>
                    </div>
                </div>
            </div>
            <div class="thirteen wide column">
                <div class="field" id="header_field">
                    <h3 class="ui header">
                        <i class="user icon"></i>

                        <div class="content">
                            會員查詢
                        </div>
                    </h3>
                </div>
                <div class="field" id="search_field">
                    <div class="ui icon input">
                        <input type="text" name="search" placeholder="編號，姓名，手機..." id="search">
                        <i class="search icon"></i>
                    </div>
                </div>
                <div class="show member_list">
                    <table class="ui celled table">
                        <thead>
                        <tr>
                            <th>會員編號</th>
                            <th>姓名</th>
                            <th>性別</th>
                            <th>手機號碼</th>
                            <th>通訊地址</th>
                            <th>會員狀態</th>
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
<div class="ui fullscreen modal">
    <i class="close icon"></i>

    <h3 class="ui header">
        <i class="user icon"></i>

        <div class="content">
            會員詳細資料
        </div>
    </h3>
    <div class="content">
        <form class="ui form ">
            <div class="ui segment">
                <div class="four fields" id="unChangeField1">

                    <div class="field ">
                        <label>建檔日期：</label>

                        <div class="ui left icon input ">
                            <input type="text" name="create_date" id="create_date" readonly="readonly">
                            <i class="calendar icon"></i>
                        </div>
                    </div>
                    <div class="field ">
                        <label>建檔人員：</label>

                        <div class="ui left icon input">
                            <input type="text" id="create_name" name="create_name" readonly="readonly">
                            <i class="keyboard icon"></i>
                        </div>
                    </div>
                    <div class="field ">
                        <label>修改日期：</label>

                        <div class="ui left icon input">
                            <input type="datetime" name="update_date" id="update_date" readonly="readonly">
                            <i class="calendar icon"></i>
                        </div>
                    </div>
                    <div class="field ">
                        <label>修改人員</label>

                        <div class="ui  left icon input">
                            <input type="text" id="update_name" name="update_name" readonly="readonly">
                            <i class="keyboard icon"></i>
                        </div>
                    </div>

                </div>
                <div class="four fields" id="unChangeField2">
                    <div class="field">
                        <label>專案編號：</label>

                        <div class="ui left icon input">
                            <input type="text" name="project_no" id="project_no" readonly="readonly">
                            <i class="crop icon"></i>
                        </div>
                    </div>
                    <div class="field">
                        <label>專案名稱：</label>

                        <div class="ui left icon input">
                            <input type="text" name="pname" id="pname" readonly="readonly">
                            <i class="edit icon"></i>
                        </div>
                    </div>
                    <div class="field" id="sLevelField">
                        <label>會員職位：</label>

                        <div class="ui left icon input">
                            <input type="text" name="slevel" id="slevel" readonly="readonly">
                            <i class="trophy icon"></i>
                        </div>
                    </div>
                    <div class="field" id="cLevelField">
                        <label>會員職位</label>

                        <div class="ui selection dropdown" id="levelSelect">
                            <input type="hidden" name="level" id="level">
                            <i class="users icon"></i>

                            <div class="default text">請選擇...</div>
                            <i class="dropdown icon"></i>


                            <div class="menu" id="level_menu">
                                <div class="item" data-value="1">會員</div>
                                <div class="item" data-value="2">處長</div>
                                <div class="item" data-value="3">區長</div>
                                <div class="item" data-value="4">部長</div>
                                <div class="item" data-value="5">營運長</div>
                            </div>
                        </div>
                    </div>
                    <div class="field " id="statusField">
                        <label>會員狀態：</label>

                        <div class="ui left icon input ">
                            <input type="text" name="status" id="status" readonly="readonly">
                            <i class="flag icon"></i>
                        </div>
                    </div>
                </div>
                <div class="four fields" id="unChangeField3">
                    <div class="field " id="sildeField">
                        <label>滑動八周</label>

                        <div class="ui  left icon input">
                            <input type="text" id="slide" name="slide" readonly="readonly">
                            <i class="refresh icon"></i>
                        </div>

                    </div>
                    <div class="field" id="sRecommendField">
                        <label>推薦人</label>

                        <div class="ui  left icon input">
                            <input type="text" name="sRecommend" id="sRecommend">
                            <i class="rocket icon"></i>

                        </div>
                    </div>
                    <div class="field" id="cRecommendField">
                        <label>推薦人</label>

                        <div class="ui search selection dropdown" id="recommendSelect">
                            <input type="hidden" name="recommend" id="recommend">

                            <div class="default text">請選擇推薦人</div>
                            <i class="dropdown icon"></i>

                            <div class="menu" id="recommend_menu">
                            </div>
                        </div>

                    </div>
                    <div class="field " id="sUpperField">
                        <label>安置上線</label>

                        <div class="ui left icon input">
                            <input type="text" name="sUpper" id="sUpper">
                            <i class="circle icon"></i>

                        </div>
                    </div>
                    <div class="field" id="cUpperField">
                        <label>安置上線</label>

                        <div class="ui search selection dropdown" id="upperSelect">
                            <input type="hidden" name="upper" id="upper">

                            <div class="default text">請選擇上線</div>
                            <i class="dropdown icon"></i>

                            <div class="menu" id="upper_menu">
                            </div>
                        </div>
                    </div>
                    <div class="field" id="sPositionField">
                        <label>安置位置</label>

                        <div class="ui left icon input">
                            <input type="text" name="sPosition" id="sPosition">
                            <i class="users icon"></i>

                        </div>
                    </div>
                    <div class="field" id="cPositionField">
                        <label>安置位置</label>

                        <div class="ui selection dropdown" id="positionSelect">
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
                </div>
            </div>
            <div class="ui segment">
                <div class="three fields">
                    <div class="field" id="memberNoField">
                        <label>會員編號</label>

                        <div class="ui left icon input">
                            <input type="text" name="member_no" placeholder="會員登入賬號" id="member_no">
                            <i class="cloud icon"></i>

                        </div>
                    </div>
                    <div class="field">
                        <label>會員密碼</label>

                        <div class="ui left icon input">
                            <input type="text" name="member_pwd" placeholder="********" id="member_pwd">
                            <i class="lock icon"></i>

                        </div>
                    </div>
                    <div class="field" id="memberNameField">
                        <label>姓名</label>

                        <div class="ui left icon input">
                            <input type="text" name="name" placeholder="請輸入完整姓名" id="name">
                            <i class="user icon"></i>

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

                        <div class="ui selection dropdown" id="sex_select">
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
                    <div class="four wide field" id="bank_code_field">
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
                    <div class="four wide field" id="city_field">
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
                    <div class="four wide field" id="area_field">
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

                        <div class="ui input">
                            <input id="address" name="address" type="text">
                        </div>
                    </div>
                </div>
                <div class="field">
                    <label>備註</label>
                    <textarea name="note" placeholder="最多可輸入200個字....." id="note"></textarea>
                </div>
            </div>
            <div class="blue ui button" id="change_button">
                修改
            </div>
            <div class="green ui button" id="submit_button">
                提交
            </div>
            <div class="red ui button" id="cancel_button">
                取消
            </div>
        </form>
    </div>
</div>
</body>
<script src="../components/requirejs/require.js" data-main="../scripts/control/member_search"></script>
</html>
