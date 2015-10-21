/**
 * Created by zero on 15/6/21.
 */
require(['../config/config'], function () {
    require(['jquery', 'ajaxCommit', 'md5', 'area', 'serializeObject', 'semantic',
        'header', 'dateExt', 'common', 'domReady!'
    ], function ($, ajaxCommit, MD5) {
        var managerLevel = sessionStorage.getItem('level');
        var now = new Date();
        var minutes = ("0" + now.getMinutes()).slice(-2);
        var hours = ("0" + now.getHours()).slice(-2);
        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);
        var time = now.getFullYear() + "-" + (month) + "-" + (day) + " " + (hours) + ":" + (minutes);

        Object.size = function (obj) {
            var size = 0, key;
            for (key in obj) {
                if (obj.hasOwnProperty(key)) size++;
            }
            return size;
        };

        $('.menu').on('click', '.item', function () {
            $(this)
                .addClass('active')
                .siblings('.item')
                .removeClass('active');
        });

        function memberData() {
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
        }

        var cRecommendField = $('#cRecommendField');
        var sRecommendField = $('#sRecommendField');
        var cUpperField = $('#cUpperField');
        var sUpperField = $('#sUpperField');
        var cPositionField = $('#cPositionField');
        var sPositionField = $('#sPositionField');
        var cLevelField = $('#cLevelField');
        var sLevelField = $('#sLevelField');

        var submitButton = $('#submit_button');
        var cancelButton = $('#cancel_button');
        var changButton = $('#change_button');


        cRecommendField.hide();
        cUpperField.hide();
        cPositionField.hide();
        cLevelField.hide();
        submitButton.hide();
        cancelButton.hide();
        var memberObj = {};
        changButton.on('click', {msg: managerLevel}, function (event) {
            memberData();
            submitButton.show();
            cancelButton.show();
            changButton.hide();
            if (event.data.msg == '1') {
                cRecommendField.show();
                cUpperField.show();
                cPositionField.show();
                sRecommendField.hide();
                sUpperField.hide();
                sPositionField.hide();
                $('#left_item').hide();
                $('#right_item').hide();
            }

            //cLevelField.show();
            //sLevelField.hide();

            $('#unChangeField1').addClass('error');
            $('#unChangeField2').addClass('error');
            $('#unChangeField3').addClass('error');
            $('#memberNoField').addClass('error');

            $('#update_date').val(time);
            $('#update_name').val(sessionStorage.getItem('user'));
            $('#bank_code_field').show();
            $('#city_field').show();
            $('#area_field').show();
            $('input[name=level]').attr('readonly', false);
            $('input[name=name]').attr('readonly', false);
            $('input[name=member_pwd]').attr('readonly', false);
            $('input[name=id]').attr('readonly', false);
            $('input[name=birthday]').attr('readonly', false);
            $('input[name=sex]').attr('readonly', false);
            $('#sex_select').removeClass('disabled');
            $('input[name=mobile]').attr('readonly', false);
            $('input[name=tel]').attr('readonly', false);
            $('input[name=email]').attr('readonly', false);
            $('input[name=account_name]').attr('readonly', false);
            $('input[name=bank_account]').attr('readonly', false);
            $('input[name=address]').attr('readonly', false);
            $('#note').attr('disabled', false);

            memberObj.level = $('#slevel').val();
            memberObj.recommend = $('#sRecommend').val();
            memberObj.upper = $('#sUpper').val();
            memberObj.position = $('#sPosition').val();
            memberObj.name = $('#name').val();
            memberObj.pwd = $('#member_pwd').val();
            memberObj.id = $('#id').val();
            memberObj.birthday = $('#birthday').val();
            memberObj.sex = $('#sex').val();
            memberObj.mobile = $('#mobile').val();
            memberObj.tel = $('#tel').val();
            memberObj.email = $('#email').val();
            memberObj.accountName = $('#account_name').val();
            memberObj.bankAccount = $('#bank_account').val();
            memberObj.addr = $('#address').val();
            memberObj.note = $('#note').val();


        });


        cancelButton.click(function () {

            $('#update_date').val('');
            $('#update_name').val('');
            $('#name').val(memberObj.name);
            $('#member_pwd').val(memberObj.pwd);
            $('#id').val(memberObj.id);
            $('#birthday').val(memberObj.birthday);
            if (memberObj.sex != '') {
                $('#sex_select').dropdown('set selected', memberObj.sex);
            } else {
                $('#sex_select').dropdown('clear');
            }
            $('#mobile').val(memberObj.mobile);
            $('#tel').val(memberObj.tel);
            $('#email').val(memberObj.email);
            $('#account_name').val(memberObj.accountName);
            $('#bank_account').val(memberObj.bankAccount);
            $('#address').val(memberObj.addr);
            $('#note').val(memberObj.note);


            cancelState();
        });

        function cancelState() {
            changButton.show();
            submitButton.hide();
            cancelButton.hide();

            if (managerLevel == '1') {
                sRecommendField.show();
                sUpperField.show();
                sPositionField.show();
                cRecommendField.hide();
                cUpperField.hide();
                cPositionField.hide();
            }
            //sLevelField.show();
            //cLevelField.hide();
            $('#levelSelect').dropdown('clear');
            $('#upperSelect').dropdown('clear');
            $('#recommendSelect').dropdown('clear');
            $('#positionSelect').dropdown('clear');

            $('#unChangeField1').removeClass('error');
            $('#unChangeField2').removeClass('error');
            $('#unChangeField3').removeClass('error');
            $('#memberNoField').removeClass('error');

            $('#bank_code_field').hide();
            $('#city_field').hide();
            $('#area_field').hide();
            $('#note').attr('disabled', true);
            $('input[type=text]').attr('readonly', true);
            $('#sex_select').addClass('disabled');
        }


        $('#search').on("keyup", function () {
            var value = $(this).val().toUpperCase();
            var tmp;
            var num;
            if (value == '') {
                memberList.memberData = memberList.memberDataTemp;
                memberList.total = memberList.memberData.length;
                tmp = memberList.total % memberList.pageSize;
                num = Math.floor(memberList.total / memberList.pageSize);
                memberList.totalPage = tmp == 0 ? num : num + 1;
                memberList.showUI();
            } else {

                memberList.memberData = getObjects(memberList.memberDataTemp, 'member_no', 'name', 'mobile', value);
                memberList.total = memberList.memberData.length;
                tmp = memberList.total % memberList.pageSize;
                num = Math.floor(memberList.total / memberList.pageSize);
                memberList.totalPage = tmp == 0 ? num : num + 1;
                memberList.showUI();
            }
            memberList.showData(1);

        });

        function getObjects(obj, key, key2, key3, val) {
            var objects = [];
            for (var i in obj) {
                if (!obj.hasOwnProperty(i)) continue;
                if (typeof obj[i] == 'object') {
                    objects = objects.concat(getObjects(obj[i], key, key2, key3, val));
                } else if (i == key && obj[key].indexOf(val) != -1) {
                    objects.push(obj);
                } else if (i == key2 && obj[key2].indexOf(val) != -1) {
                    objects.push(obj);
                } else if (i == key3 && obj[key3].indexOf(val) != -1) {
                    objects.push(obj);
                }
            }
            return objects;
        }

        function Pagination(obj) {
            this.id = obj.id;  //div id
            this.url = obj.url;
            this.pageSize = obj.pageSize;
            this.pageNum = 1; //current page number
            this.total = 0; //total count
            this.totalPage = 0;
            this.barSize = obj.barSize;
            this.numPoint = 1;
            this.data = obj.data;
            this.success = obj.success;
            this.error = obj.error;
            this.div = null;
            this.init();
        }

        Pagination.prototype.init = function () {
            if (this.data == undefined) {
                this.data = {}
            }
            this.div = $('#' + this.id);
            this.fetchData(1);
        };

        Pagination.prototype.fetchData = function (pageNum) {
            this.memberData = '';
            this.memberDataTemp = '';
            this.barPos = pageNum;
            var that = this;
            $.ajax({
                url: that.url,
                data: that.data,
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    that.memberData = $.parseJSON(data.message);
                    that.memberDataTemp = that.memberData;
                    that.total = that.memberData.length;
                    var tmp = that.total % that.pageSize;
                    var num = Math.floor(that.total / that.pageSize);
                    that.totalPage = tmp == 0 ? num : num + 1;
                    that.success(that.memberData);
                    that.showUI();
                },
                error: function (msg) {
                    that.error(msg);
                }
            })
        };

        Pagination.prototype.showUI = function () {
            var that = this;
            this.div.empty();
            var currentBarSize = this.totalPage - (this.numPoint - 1) * this.barSize;
            currentBarSize = currentBarSize > this.barSize ? this.barSize : currentBarSize;
            if (that.pageNum == 1) {
                this.div.append('<a class="disabled item"><i class="left arrow icon"></i> 上 ' + that.barSize + ' 页</a>');
            } else {
                this.div.append('<a class="item"><i class="left arrow icon"></i> 上 ' + that.barSize + ' 页</a>');
            }
            for (var i = 0; i < currentBarSize; i++) {
                var pos = (this.barPos - 1).toString();
                pos = pos.substr(pos.length - 1);
                if (parseInt(pos) == i) {
                    this.div.append('<a class="active item">' + (that.pageNum + i) + '</a>');
                } else {
                    this.div.append('<a class=" item">' + (that.pageNum + i) + '</a>');
                }
            }
            if (that.numPoint >= Math.ceil(that.totalPage / that.barSize)) {
                this.div.append('<a class="disabled item"> 下 ' + that.barSize + ' 页 <i class="icon right arrow"></i></a>');
            } else {
                this.div.append('<a class=" item"> 下 ' + that.barSize + ' 页 <i class="icon right arrow"></i></a>');
            }
            var array = this.div.find('a');
            for (var j = 0; j < array.length; j++) {
                var current = $(array[j]);
                if (j == 0) {
                    current.click({param: that}, that.previewPage);
                } else if (j == array.length - 1) {
                    current.click({param: that}, that.nextPage)
                } else {
                    current.click({param: that}, function (event) {
                        var p = event.data.param;
                        var n = $(this).text().trim();
                        p.showData(parseInt(n));

                    })
                }
            }
        };

        Pagination.prototype.nextPage = function (event) {
            var that = event.data.param;
            if (that.numPoint > 1) {
                that.div.find('a').first().attr('class', 'item');
            }
            if (that.numPoint >= Math.ceil(that.totalPage / that.barSize)) {
                that.div.find('a').last().attr('class', 'disabled item');
            } else {
                that.pageNum = that.numPoint * that.barSize + 1;
                that.numPoint++;
                that.showData(that.pageNum);
                that.showUI();
            }
        };

        Pagination.prototype.previewPage = function (event) {
            var that = event.data.param;
            if (that.numPoint < Math.ceil(that.totalPage / that.barSize)) {
                that.div.find('a').last().attr('class', 'item');
            }
            if (that.numPoint == 1) {
                that.div.find('a').first().attr('class', 'disabled item');
            } else {
                that.numPoint--;
                that.pageNum = (that.numPoint - 1) * that.barSize + 1;
                that.showData(that.pageNum);
                that.showUI();
            }
        };

        Pagination.prototype.showData = function (pageNum) {
            $('tbody').remove();
            var $tbody = $("<tbody/>");
            var startData;
            var endData;
            if (pageNum == 1) {
                startData = pageNum - 1;
            } else {
                startData = (pageNum - 1) * this.pageSize;
            }
            endData = startData + this.pageSize;
            if (endData > this.memberData.length) {
                endData = this.memberData.length;
            }
            $.each(this.memberData, function (index, obj) {
                if (index >= startData && index < endData) {
                    var status;
                    if (obj.status == 'normal') {
                        status = '有效';
                    }
                    var arr = [];
                    arr.push(obj.member_no, obj.name, obj.sex, obj.mobile, obj.address, status);

                    var $tr = $("<tr/>");
                    for (var j = 0; j < arr.length; j++) {
                        var $td = $("<td/>");
                        $td.text(arr[j]);
                        $tr.append($td);
                    }
                    $tr.click(function () {
                        $('.fullscreen.modal').modal({
                            closable: false,
                            onHide: function () {
                                $('#change_button').show();
                                $('#submit_button').hide();
                                $('#cancel_button').hide();
                                cancelState();
                                $('input[name=search]').attr('readonly', false);
                            }
                        }).modal('show');
                        $('input[type=text]').attr('readonly', true);
                        $('#project_no').val(obj.project_no);
                        $('#pname').val(obj.pname);
                        var levelTemp;
                        switch (obj.level) {
                            case 1:
                                levelTemp = '會員';
                                break;
                            case 2:
                                levelTemp = '處長';
                                break;
                            case 3:
                                levelTemp = '區長';
                                break;
                            case 4:
                                levelTemp = '部長';
                                break;
                            case 5:
                                levelTemp = '營運長';
                                break;
                        }
                        $('#slevel').val(levelTemp);
                        var statusTemp;
                        switch (obj.status) {
                            case 'normal':
                                statusTemp = '有效會員';
                                break;
                            case 'project':
                                statusTemp = '專案會員';
                                break;
                            case 'destroy':
                                statusTemp = '申退會員';
                                break;
                        }
                        $('#status').val(statusTemp);
                        $('#slide').val((obj.slide == 'yes') ? '滑動' : '不滑動');
                        $('#create_name').val(obj.create_name);
                        $('#create_date').val(obj.create_date);
                        $('#sRecommend').val(getNameById(obj.recommend));
                        $('#sUpper').val(getNameById(obj.upper));
                        $('#sPosition').val(getPosition(obj.upper, obj._id));
                        $('#member_no').val(obj.member_no);
                        $('#name').val(obj.name);
                        $('#id').val(obj.id);
                        $('#birthday').val(obj.birthday);
                        $('#sex_select').dropdown('set selected', obj.sex);
                        $('#sex_select').addClass('disabled');
                        $('#mobile').val(obj.mobile);
                        $('#tel').val(obj.tel);
                        $('#email').val(obj.email);
                        $('#account_name').val(obj.account_name);
                        $('#bank_account').val(obj.bank_account);
                        $('#bank_code_field').hide();
                        $('#city_field').hide();
                        $('#area_field').hide();
                        $('#address').val(obj.address);
                        $('#note').attr('disabled', true);
                        $('#note').val(obj.note);
                    });
                    $tbody.append($tr);
                }
            });
            $('table').append($tbody);
            $('.ui.active.loader').hide();
        };
        var memberList;

        function memberSearch() {
            memberList = new Pagination({
                id: 'pagination',
                url: '../DataList.do',
                pageSize: 14,//每页显示的记录数
                barSize: 10,//分页工具条上展现的页码数
                data: {flag: 'memberservice', param: 'multiTable'},
                success: function (message) { //回调函数
                    this.showData(this.pageNum);
                },
                error: function (jqXHR) {//回调函数
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

        memberSearch();


        function getNameById(upperId) {
            var result;
            $.ajax({
                type: 'POST',
                url: '../CommonSearch.do',
                data: {
                    flag: 'memberservice',
                    upperId: upperId
                },
                async: false,
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        result = data.message;
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
            return result;
        }

        function getPosition(upperId, memberId) {
            var result;
            $.ajax({
                type: 'POST',
                url: '../CommonSearch.do',
                data: {
                    flag: 'memberextservice',
                    upperId: upperId,
                    memberId: memberId
                },
                async: false,
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        result = data.message;
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
            return result;
        }


        var validations = {
            upper: {
                identifier: 'upper',
                rules: [
                    {
                        type: 'setLowerPosition',
                        prompt: ''
                    }
                ]
            },
            member_pwd: {
                identifier: 'member_pwd',
                rules: [

                    {
                        type: 'verifyPasswd',
                        prompt: '密碼六位英文或數字組成'

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
            }
        };

        $.fn.form.settings.rules.setLowerPosition = function (value) {
            $('#right_item').show();
            $('#left_item').show();
            if (value != '') {
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

        $.fn.form.settings.rules.verifyPasswd = function (value) {
            if (value != '') {
                var regex = /^\w{6}$/;
                return regex.test(value);
            }
            return true;
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

        var changeRecommend = '';
        var changeUpper = '';
        var changePositon = '';
        $('form,#submit_button').api({
            action: 'update member',
            method: 'POST',
            beforeSend: function (settings) {
                $('form').form('validate form');
                settings.data = {
                    flag: 'memberservice',
                    member_no: $('#member_no').val(),
                    update_name: $('#update_name').val(),
                    update_date: $('#update_date').val()
                };

                var levelTemp;
                switch (memberObj.level) {
                    case '會員':
                        levelTemp = 1;
                        break;
                    case '處長':
                        levelTemp = 2;
                        break;
                    case '區長':
                        levelTemp = 3;
                        break;
                    case '部長':
                        levelTemp = 4;
                        break;
                    case '營運長':
                        levelTemp = 5;
                        break;
                }

                var level = $('#level');
                if (level.val() != '' && levelTemp != level.val()) {
                    settings.data.level = level.val();
                    $('#sLevel').val(level.val());
                }
                var recommend = $('#recommend');
                if (recommend.val() != '' && memberObj.recommend.substr(-8) != recommend.val()) {
                    settings.data.recommend = recommend.val();
                    changeRecommend = $('#recommendSelect').dropdown('get text');
                }
                var position = $('#position');
                if (position.val() != '') {
                    settings.data.position = position.val();
                    settings.data.currentPosition = memberObj.position;
                    changePositon = $('#positionSelect').dropdown('get text');
                }
                var upper = $('#upper');
                var currentUpper = memberObj.upper.substr(-8);
                if (upper.val() != '' && (currentUpper != upper.val()) || position.val() != '') {
                    settings.data.upper = upper.val();
                    settings.data.currentUpper = currentUpper;
                    changeUpper = $('#upperSelect').dropdown('get text');
                }
                var memberPassword = $('#member_pwd');
                if (memberObj.pwd != memberPassword.val())
                    settings.data.member_pwd = MD5($.trim(memberPassword.val()));
                var name = $('#name');
                if (memberObj.name != name.val())
                    settings.data.name = name.val();
                var id = $('#id');
                if (memberObj.id != id.val())
                    settings.data.id = id.val();
                var birthday = $('#birthday');
                if (memberObj.birthday != birthday.val())
                    settings.data.birthday = birthday.val();
                var sex = $('#sex');
                if (memberObj.sex != sex.val())
                    settings.data.sex = sex.val();
                var mobile = $('#mobile');
                if (memberObj.mobile != mobile.val())
                    settings.data.mobile = mobile.val();
                var tel = $('#tel');
                if (memberObj.tel != tel.val())
                    settings.data.tel = tel.val();
                var email = $('#email');
                if (memberObj.email != email.val())
                    settings.data.email = email.val();
                var account_name = $('#account_name');
                if (memberObj.accountName != account_name.val())
                    settings.data.account_name = account_name.val();
                var bank_account = $('#bank_account');
                if (memberObj.bankAccount != bank_account.val())
                    settings.data.bank_account = bank_account.val();
                var address = $('#address');
                if (memberObj.addr != address.val())
                    settings.data.address = address.val();
                var note = $('#note');
                if (memberObj.note != note.val())
                    settings.data.note = note.val();

                if (Object.size(settings.data) != 4) {
                    return settings;
                } else {
                    alert('無更新資料,請確認...');
                    return false;
                }

            },
            successTest: function (response) {
                if (response && response.success) {
                    return response.success;
                }
                return false;
            },
            onSuccess: function (response) {
                cancelState();
                memberSearch();
                if (changeRecommend != '') {
                    $('#sRecommend').val(changeRecommend);
                    memberObj.recommend = changeRecommend;
                    changeRecommend = '';
                }
                if (changeUpper != '') {
                    $('#sUpper').val(changeUpper);
                    memberObj.upper = changeUpper;
                    changeUpper = '';
                }
                if (changePositon != '') {
                    $('#sPosition').val(changePositon);
                    memberObj.position = changePositon;
                    changePositon = '';
                }
                alert(response.message);
                //$('.image').html(' <i class = " green check circle icon"></i>');
                //$('.description').html(response.message);
                //$('.small.ui.modal').modal('setting', 'transition', 'horizontal flip').modal('show');

            },
            onFailure: function (response) {
                alert(response.message);

                //$('.resp_image').html(' <i class = " red remove circle icon"></i>');
                //$('.resp_description').html(response.message);
                //$('.small.ui.modal').modal('setting', 'transition', 'horizontal flip').modal('show');

            },
            onError: function () {
                // invalid response

                alert('error');
            }

        }).form({
            keyboardShortcuts: false,
            fields: validations,
            inline: true,
            on: 'blur',
            transition: 'slide down'
        });
    });
});