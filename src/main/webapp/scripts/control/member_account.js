/**
 * Created by zero on 15/6/21.
 */
require(['../config/config'], function () {
    require(['jquery', 'ajaxCommit', 'md5', 'area', 'serializeObject', 'semantic',
        'header', 'dateExt', 'common', 'domReady!'
    ], function ($, ajaxCommit, MD5) {

        $('#position_selection').dropdown('destroy');
        $('#location_selection').dropdown('destroy');
        $('#reset').click(function(){
            location.reload();
        });

        var memberCount;
        var produceData;
        var fetch = {
            recommend: function () {
                ajaxCommit.showData('../DataList.do', 'memberservice', function (data) {
                    if (data.success) {
                        $('#recommend_menu').empty();
                        var produceData = $.parseJSON(data.message);
                        $.each(produceData, function (index, obj) {
                            $('#recommend_menu').append('<div class="item" data-value="' + obj.member_no + '">'
                                + obj.name + " " + obj.member_no + '</div>');
                        });
                    }
                });
            },
            upper: function () {
                ajaxCommit.showData('../DataList.do', 'memberservice', function (data) {
                    if (data.success) {
                        $('#upper_menu').empty();
                        var produceData = $.parseJSON(data.message);
                        $.each(produceData, function (index, obj) {
                            $('#upper_menu').append('<div class="item" data-value="' + obj.member_no + '">'
                                + obj.name + " " + obj.member_no + '</div>');
                        });
                    }
                }, "upper");
            },
            projectNo: function (prefix) {
                var month = parseInt($('#create_date').val().substring(5, 7));
                var now = new Date();
                now.setMonth(month - 1);
                var day = now.Format('yyyyMM');
                ajaxCommit.showData('../GetCount.do', 'projectservice', function (data) {
                    if (data.success) {
                        $('#project_no').val(prefix + (parseInt(day + '0001') + parseInt(data.count)));
                    }
                }, day);
            }, //數字前補0
            padLeft: function (str, len) {
                str = '' + str;
                return str.length >= len ? str : new Array(len - str.length + 1).join("0") + str;
            },
            memberCount: function () {
                ajaxCommit.showData("../GetCount.do", "memberservice", function (data) {
                    if (data.success) {
                        memberCount = data.count;
                        if (memberCount == 0) {
                            $('#member_no').val('CTPL0001');
                            $('#recommend_selection').dropdown('destroy');
                            $('#upper_selection').dropdown('destroy');

                        }
                    }
                });
            },
            produceName: function () {
                ajaxCommit.showData('../DataList.do', 'produceservice', function (data) {
                    if (data.success) {
                        produceData = $.parseJSON(data.message);
                        $.each(produceData, function (index, obj) {
                            if (obj.pname != '專案') {
                                $('#produce_menu').append('<div class="item" data-value="' + obj.pname + '">' + obj.pname + '</div>');
                            }
                        });
                    }
                });
            }

        };

        fetch.memberCount();
        fetch.recommend();
        fetch.upper();
        fetch.projectNo('N');
        fetch.produceName();

        $('#status_normal,#status_project').checkbox({
            onChange: function () {
                $('#price').val('');
                $('#pv').val('');
                $('#produce_select').dropdown('clear');
                $('#produce_menu').empty();
                if ($('input[name="status"]:checked').val() == 'normal') {
                    $.each(produceData, function (index, obj) {
                        if (obj.pname != '專案') {
                            $('#produce_menu').append('<div class="item" data-value="' + obj.pname + '">' + obj.pname + '</div>');
                        }
                    });
                } else {
                    if ($('input[name="installment"]:checked').val() == 'yes') {
                        $('#stage_no').checkbox('check');
                    }
                    $.each(produceData, function (index, obj) {
                        if (obj.pname == '專案') {
                            $('#produce_menu').append('<div class="item" data-value="' + obj.pname + '">' + obj.pname + '</div>');
                            $('#price').val('');
                            $('#pv').val('');
                        }
                    });
                }
            }
        });

        $('#stage_yes,#stage_no').checkbox({
            onChange: function () {
                if ($('input[name="installment"]:checked').val() == 'yes') {
                    $('#pay_yes_field').hide();
                    $('#pay_no_field').hide();
                    $('#pay_stage_field').show();
                    $('#checkbox_pay_stage').checkbox('check');
                    $('.dropdown').dropdown('destroy');
                    $('#stage_field').show();
                    $('#price').val($stageMoney);
                    $('#pv').val($stagePV);
                    $('#stage').val($stage);
                    fetch.projectNo('S');
                    var now = new Date();
                    $('#stage_star').val(now.getFullYear() + '/' + ( now.getMonth() + 1));
                    now.setMonth(now.getMonth() + parseInt($stage));
                    $('#stage_end').val(now.getFullYear() + '/' + now.getMonth());
                    $('#last_pv').val(parseInt($stagePV) + parseInt($remainPV));
                } else {
                    $('#pay_yes_field').show();
                    $('#pay_no_field').show();
                    $('#pay_stage_field').hide();
                    $('#checkbox_pay_yes').checkbox('check');
                    fetch.projectNo('N');
                    $('#project_pay_field').show();
                    $('.dropdown').dropdown();
                    $('#stage_field').hide();
                    $('#price').val($producePrice);
                    $('#pv').val($producePV);
                    $('#stage').val('');
                    $('stage_star').val('');
                    $('stage_end').val('');
                    $('#last_pv').val('');
                }
            }
        });

        for (var i = 0; i < bank_code.length; i++) {
            for (var key in bank_code[i]) {
                $('#code_item').append("<div class='item' item data-value='" + key + "'>" + key + "&nbsp;&nbsp;" + bank_code[i][key] + "</div>");
            }
        }

        var validations = {
            recommend: {
                identifier: 'recommend',
                rules: [
                    {
                        type: 'verifyMemberCount',
                        prompt: '推薦人會員編號不能為空'
                    }
                ]
            },
            upper: {
                identifier: 'upper',
                rules: [
                    {
                        type: 'verifyMemberCount',
                        prompt: '上線會員編號不能為空'
                    },
                    {
                        type: 'setLowerPosition',
                        prompt: ''
                    },
                    {
                        type: 'getMemberNO',
                        prompt: ''
                    }
                ]
            },
            position: {
                identifier: 'position',
                rules: [
                    {
                        type: 'getMemberNO',
                        prompt: ''
                    }
                ]
            },
            location: {
                identifier: 'location',
                rules: [
                    {
                        type: 'getMemberNO',
                        prompt: ''
                    }
                ]
            },
            member: {
                identifier: 'member_no',
                rules: [
                    {
                        type: 'empty',
                        prompt: '會員編號不能為空'
                    }
                ]
            },
            password: {
                identifier: 'member_pwd',
                rules: [
                    {
                        type: 'empty',
                        prompt: '密碼不能為空'
                    }
                ]
            },
            name: {
                identifier: 'name',
                rules: [
                    {
                        type: 'empty',
                        prompt: '姓名不能為空'
                    },
                    {
                        type: 'setName',
                        prompt: ''
                    }
                ]
            },
            birthday: {
                identifier: 'birthday',
                rules: [
                    {
                        type: 'verifyBirthday',
                        prompt: '輸入格式錯誤'
                    }
                ]
            },
            mobile1: {
                identifier: 'mobile',
                rules: [
                    {
                        type: 'verifyMobile',
                        prompt: '輸入格式錯誤'
                    }
                ]
            },
            bank_code: {
                identifier: 'bank_code',
                rules: [
                    {
                        type: 'setBankCode',
                        prompt: ''
                    }
                ]
            },
            city: {
                identifier: 'city',
                rules: [
                    {
                        type: 'setArea',
                        prompt: ''
                    }
                ]
            },
            area: {
                identifier: 'area',
                rules: [
                    {
                        type: 'setAddress',
                        prompt: ''
                    }
                ]
            },
            project_name: {
                identifier: 'pname',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請選擇專案名稱'
                    },
                    {
                        type: 'searchAmortization',
                        prompt: ''
                    },
                    {
                        type: 'searchProduce',
                        prompt: ''
                    }
                ]
            }
        };

        $.fn.form.settings.rules.verifyMemberCount = function (value) {
            if (value != '') {
                return true;
            } else {
                if (memberCount == 0) {
                    return true;
                }
            }
            return false;

        };

        $.fn.form.settings.rules.setLowerPosition = function (value) {
            $('#right_item').show();
            $('#left_item').show();
            if (value != '') {

                $('#lead_selection').dropdown();
                $('#location_selection').dropdown();
                $('#position_selection').dropdown('clear');
                $.ajax({
                    type: 'POST',
                    url: '../CheckLowerPosition.do',
                    data: {
                        member_no: value
                    },
                    async: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            switch (data.message) {
                                case 'lr':
                                    break;
                                case 'l':
                                    $('#right_item').hide();
                                    break;
                                case 'r':
                                    $('#left_item').hide();
                                    break;
                            }
                        }
                        else {
                            $('#right_item').hide();
                            $('#left_item').hide();

                        }
                    },
                    error: function (jqXHR) {
                        if (jqXHR.status == '911') {
                            sessionStorage.clear();
                            alert('閒置時間過久，請重新登入..');
                            window.location.href = '../login.html'
                        } else {
                            alert("發生錯誤：" + jqXHR.status);
                        }
                    }
                });
            }
            return true;
        };


        $.fn.form.settings.rules.getMemberNO = function (value) {
            if (value != null) {
                var $position = $('#position_selection').dropdown('get value');
                var $location = $('#location_selection').dropdown('get value');
                var $upper = $('#upper').val();
                var $loc;
                if ($upper != '' && $position != '' && $location != '') {
                    if (memberCount < 4) {
                        $loc = 'C';
                    } else {
                        switch ($upper) {
                            case 'CTPL0002':
                                $loc = 'L';
                                break;
                            case 'CTGL0001':
                                $loc = 'W';
                                break;
                            case 'CTPL0003':
                                $loc = 'H';
                                break;
                            default:
                                $loc = $upper.substring(0, 1).toUpperCase();
                        }
                    }
                    var str = ($loc + $location + $position.substring(0, 1)).toUpperCase();
                    ajaxCommit.showData('../GetCount.do', 'memberservice', function (data) {
                        if (data.success) {
                            $('#member_no').val(str + fetch.padLeft(parseInt(data.count) + 1, 4));
                            $('#project_member_no').val(str + fetch.padLeft(parseInt(data.count) + 1, 4));
                        }
                    }, str);

                }
            }
            return true;
        };

        $.fn.form.settings.rules.setName = function (value) {
            if (value != '') {
                $('#member_name').val(value);
            }
            return true;
        };

//自定義regex8位數字驗證
        $.fn.form.settings.rules.verifyNum = function (value) {
            if (value != '') {
                var regex = /^\d{8}$/;
                return regex.test(value);
            }
        };

//自定義身份號碼檢查
        $.fn.form.settings.rules.verify_id = function (value) {

        };

//自定義生日檢查
        $.fn.form.settings.rules.verifyBirthday = function (value) {
            if (value == "") {
                return true;
            } else {
                var regex = /^\d{4}-\d{2}-\d{2}$/;
                return regex.test(value);
            }
        };

//自定義手機號碼檢查
        $.fn.form.settings.rules.verifyMobile = function (value) {
            if (value == "") {
                return true;
            } else {
                var regex = /^09\d{8}$/;
                return regex.test(value);
            }
        };

//輸入銀行碼
        $.fn.form.settings.rules.setBankCode = function (value) {
            if (value == '') {
                return true;
            } else {
                var bankAccount = $('#bank_account');
                var accValue = bankAccount.val().substring(5);
                bankAccount.val(value + "  " + accValue);
                return true;
            }
        };
//依選擇縣市產生區域選單
        $.fn.form.settings.rules.setArea = function (value) {
            if (value == '') {
                return true;
            } else {
                var areas = _dmap[value];
                $('#area_item').empty();
                $('#area_select').dropdown('clear');
                for (var i = 0; i < areas.length; i++) {
                    for (var key in areas[i]) {
                        $('#area_item').append("<div class='item' item data-value='" + key + "'>" + key + "</div>");
                    }
                }
                return true;
            }
        };
//輸入縣市和區域
        $.fn.form.settings.rules.setAddress = function (value) {
            if (value == '') {
                return true;
            } else {
                var city = $('#city').val();
                var code;
                var areas = _dmap[city];
                for (var i = 0; i < areas.length; i++) {
                    for (var key in areas[i]) {
                        if (key == value) {
                            code = areas[i][key];
                        }
                    }
                }
                $('#address').val(code + " " + city + value);
                return true;
            }
        };

        $.fn.form.settings.rules.checkDate = function (value) {
            if (value != '') {
                if ($('input[name="installment"]:checked').val() == 'yes') {
                    fetch.projectNo('S');
                } else {
                    fetch.projectNo('N');
                }
            }
            return true;
        };


        var $stage, $stageMoney, $stagePV, $remainPV;
        $.fn.form.settings.rules.searchAmortization = function (value) {
            if (value != '') {
                $.ajax({
                    type: 'POST',
                    url: '../CommonSearch.do',
                    data: {
                        flag: 'amortizationservice',
                        project: value
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $('#installment').show();
                            $stage = data.stage;
                            $stageMoney = data.stage_money;
                            $stagePV = data.stage_pv;
                            $remainPV = data.remain_pv;
                        } else {
                            $('#installment').hide();
                        }
                    },
                    error: function (jqXHR) {
                        if (jqXHR.status == '911') {
                            sessionStorage.clear();
                            alert('閒置時間過久，請重新登入..');
                            window.location.href = '../login.html'
                        } else {
                            alert("發生錯誤：" + jqXHR.status);
                        }
                    }
                });
            }
            return true;
        };

        var $producePrice, $producePV;
        $.fn.form.settings.rules.searchProduce = function (value) {
            if (value != '') {
                $.ajax({
                    type: 'POST',
                    url: '../CommonSearch.do',
                    data: {
                        flag: 'produceservice',
                        project: value
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $producePrice = data.price;
                            $producePV = data.pv;
                            $('#price').val($producePrice);
                            $('#pv').val($producePV);
                        }
                    },
                    error: function (jqXHR) {
                        if (jqXHR.status == '911') {
                            sessionStorage.clear();
                            alert('閒置時間過久，請重新登入..');
                            window.location.href = '../login.html'
                        } else {
                            alert("發生錯誤：" + jqXHR.status);
                        }
                    }
                });
            }
            return true
        };


        $('.form').api({
            action: 'create member',
            method: 'POST',
            serializeForm: true,
            beforeSend: function (settings) {
                $('.form').form('validate form');
                settings.data.member_pwd = MD5($.trim($('#member_pwd').val()));
                return settings;
            },
            successTest: function (response) {
                if (response && response.success) {
                    return response.success;
                }
                return false;
            },
            onSuccess: function (response) {
                $('.image').html(' <i class = "green check circle icon"></i>');
                $('.description').html('<div class="ui header"> '+ response.message + '</div>');
                $('.small.modal').modal({
                    transition: 'horizontal flip',
                    closable  : false,
                    onApprove : function() {
                        location.reload();
                    }
                }).modal('show');


            },
            onFailure: function (response) {
                $('.image').html('<i class = " red remove circle icon"></i>');
                $('.description').html('<div class="ui header"> '+ response.message + '</div>');
                $('.small.modal').modal('setting', 'transition', 'horizontal flip').modal('show');

            },
            onError: function (jqXHR) {
                if (jqXHR.status == '911') {
                    sessionStorage.clear();
                    alert('閒置時間過久，請重新登入..');
                    window.location.href = '../login.html'
                } else {
                    alert("發生錯誤：" + jqXHR.status);
                }
            }

        }).form({
            keyboardShortcuts: false,
            fields: validations,
            inline: true,
            on: 'change',
            revalidate: true,
            transition: 'slide down'
        });
    });
});
