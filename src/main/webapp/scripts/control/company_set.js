/**
 * Created by zero on 15/6/23.
 */
require(['../config/config'], function () {

    require(['jquery', 'ajaxCommit', 'serializeObject', 'semantic', 'header',
        'dateExt', 'common', 'domReady!'
    ], function ($, ajaxCommit) {

        $('.menu .item').tab({
            history: false
        });

        $('.accordion').accordion();

        $('#year_dropdown')
            .dropdown({
                onChange: function (text) {
                    show.cycle(text);
                }
            });

        var tempData;
        ajaxCommit.showData('../DataList.do', 'produceservice', function (data) {
            if (data.success) {
                tempData = $.parseJSON(data.message);
                $('#produce_menu').empty();
                $.each(tempData, function (index, obj) {
                    $('#produce_menu').append("<div class='item' item data-value='" + obj.pname + "'>" + obj.pname + "</div>");
                });
            }
        });
        //$('#update_date').val(now.getFullYear() + '/' + (now.getMonth() + 1) + '/' + now.getDate());

        function hiddenWorkMonth() {
            for (var i = 1; i <= 13; i++) {
                $("#w" + i).hide();
            }
        }

        var show = {
            pv: function () {
                ajaxCommit.showData('../DataList.do', 'pvservice', function (data) {
                    if (data.success) {
                        var produceData = $.parseJSON(data.message);
                        var $tbody = $("<tbody/>");
                        $.each(produceData, function (index, obj) {
                            var arr = [];
                            arr.push(obj.pv_value + ' PV', obj.pv_sum + ' 元', obj.create_name, obj.create_date);
                            var $tr = $("<tr/>");
                            for (var j = 0; j <= arr.length; j++) {
                                if (j < arr.length) {
                                    var $td = $("<td/>");
                                    $td.text(arr[j]);
                                    $tr.append($td);
                                } else {
                                    var $td = $("<td/>");
                                    var delbtn = $('<div class="red ui compact icon button"><i class="remove icon"></div>')
                                        .click(function () {
                                            $(this).parents('tr:first').remove();
                                        });
                                    var editbtn = $('<div class="green ui compact icon button"><i class="write icon"></div>')
                                        .click(function () {
                                            alert('ok');
                                        });
                                    $td.append(editbtn).append(delbtn);
                                    $tr.append($td);
                                }
                            }
                            $tbody.append($tr);
                        });
                        $('table').append($tbody);
                    }
                });
            },
            consume: function () {
                ajaxCommit.showData('../DataList.do', 'consumeservice', function (data) {
                    if (data.success) {
                        var produceData = $.parseJSON(data.message);
                        var $tbody = $("<tbody/>");
                        $.each(produceData, function (index, obj) {
                            switch (obj.mode) {
                                case 'common':
                                    obj.mode = '一般消費';
                                    break;
                                case 'admission':
                                    obj.mode = '會員入會費';
                                    break;
                                case 'jar':
                                    obj.mode = '罐子費用';
                                    break;
                            }
                            var arr = [];
                            arr.push(obj.mode, obj.money + ' 元', obj.generation_pv + ' PV', obj.create_name, obj.create_date);
                            var $tr = $("<tr/>");
                            for (var j = 0; j <= arr.length; j++) {
                                if (j < arr.length) {
                                    var $td = $("<td/>");
                                    $td.text(arr[j]);
                                    $tr.append($td);
                                } else {
                                    var $td = $("<td/>");
                                    var delbtn = $('<div class="red ui compact icon button"><i class="remove icon"></div>')
                                        .click(function () {
                                            $(this).parents('tr:first').remove();
                                        });
                                    var editbtn = $('<div class="green ui compact icon button"><i class="write icon"></div>')
                                        .click(function () {
                                            alert('ok');
                                        });
                                    $td.append(editbtn).append(delbtn);
                                    $tr.append($td);
                                }
                            }
                            $tbody.append($tr);
                        });
                        $('table').append($tbody);
                    }
                });
            },
            produce: function () {
                ajaxCommit.showData('../DataList.do', 'produceservice', function (data) {
                    if (data.success) {
                        var produceData = $.parseJSON(data.message);
                        var $tbody = $("<tbody/>");
                        $.each(produceData, function (index, obj) {
                            var arr = [];
                            arr.push(obj.pname, obj.price + ' 元', obj.pv + ' PV', obj.create_name, obj.create_date);
                            var $tr = $("<tr/>");
                            for (var j = 0; j <= arr.length; j++) {
                                if (j < arr.length) {
                                    var $td = $("<td/>");
                                    $td.text(arr[j]);
                                    $tr.append($td);
                                } else {
                                    var $td = $("<td/>");
                                    var delbtn = $('<div class="red ui compact icon button"><i class="remove icon"></div>')
                                        .click(function () {
                                            $(this).parents('tr:first').remove();
                                        });
                                    var editbtn = $('<div class="green ui compact icon button"><i class="write icon"></div>')
                                        .click(function () {
                                            alert('ok');
                                        });
                                    $td.append(editbtn).append(delbtn);
                                    $tr.append($td);
                                }
                            }
                            $tbody.append($tr);
                        });
                        $('table').append($tbody);
                    }
                });
            },

            amortization: function () {
                ajaxCommit.showData('../DataList.do', 'amortizationservice', function (data) {
                    if (data.success) {
                        var produceData = $.parseJSON(data.message);
                        var $tbody = $("<tbody/>");
                        $.each(produceData, function (index, obj) {
                            var arr = [];
                            arr.push(obj.produce, obj.stage + ' 期', obj.stage_money + ' 元', obj.stage_pv + ' PV',
                                obj.total_pv + ' PV', obj.remain_pv + ' PV', obj.complement);
                            var $tr = $("<tr/>");
                            for (var j = 0; j <= arr.length; j++) {
                                if (j < arr.length) {
                                    var $td = $("<td/>");
                                    $td.text(arr[j]);
                                    $tr.append($td);
                                } else {
                                    var $td = $("<td/>");
                                    var delbtn = $('<div class="red ui compact icon button"><i class="remove icon"></div>')
                                        .click(function () {
                                            $(this).parents('tr:first').remove();
                                        });
                                    var editbtn = $('<div class="green ui compact icon button"><i class="write icon"></div>')
                                        .click(function () {
                                            alert('ok');
                                        });
                                    $td.append(editbtn).append(delbtn);
                                    $tr.append($td);
                                }
                            }
                            $tbody.append($tr);
                        });
                        $('table').append($tbody);
                    }
                });
            },

            level: function () {
                ajaxCommit.showData('../DataList.do', 'memberlevelservice', function (data) {
                    if (data.success) {
                        var produceData = $.parseJSON(data.message);
                        var $tbody = $("<tbody/>");
                        $.each(produceData, function (index, obj) {
                            var arr = [];
                            arr.push(obj.mem_level, obj.create_name, obj.create_date);
                            var $tr = $("<tr/>");
                            for (var j = 0; j <= arr.length; j++) {
                                if (j < arr.length) {
                                    var $td = $("<td/>");
                                    $td.text(arr[j]);
                                    $tr.append($td);
                                } else {
                                    var $td = $("<td/>");
                                    var editbtn = $('<div class="green ui compact icon button"><i class="write icon"></div>')
                                        .click(function () {
                                            alert('ok');
                                        });
                                    $td.append(editbtn);
                                    $tr.append($td);
                                }
                            }
                            $tbody.append($tr);
                        });
                        $('table').append($tbody);
                    }
                });
            },

            cycle: function (year) {
                ajaxCommit.showData('../DataList.do', 'cycleservice', function (data) {
                    if (data.success) {
                        var produceData = $.parseJSON(data.message);
                        $('.show.cycle').show();
                        $('tbody').remove();
                        $('#year_menu').children().remove();
                        var $tbody = $("<tbody/>");
                        if (data.message != '[]') {
                            if (year == undefined) {
                                year = new Date().Format('yyyy');
                                $('#year_dropdown').dropdown('set text', year);
                            }
                        } else {

                            $('.show.cycle').hide();
                        }
                        $.each(produceData, function (index, obj) {
                            $selectItem = $('<div class="item" id="menuItem">' + obj.work_year + '</div>')
                            if (year == obj.work_year) {
                                var arr = [];
                                arr.push(obj.first_month, obj.second_month, obj.third_month, obj.fourth_month,
                                    obj.fifth_month, obj.sixth_month, obj.seventh_month, obj.eighth_month,
                                    obj.nineth_month, obj.tenth_month, obj.eleventh_month, obj.twelfth_month,
                                    obj.thirteenth_month);
                                var x = 0;

                                for (var j = 0; j < (arr.length / 3); j++) {
                                    var $tr = $("<tr/>");
                                    for (var i = 0; i < 3; i++) {
                                        var $td = $("<td/>");
                                        if (arr[x] != undefined) {
                                            $td.text('第' + (x + 1) + '個工作月' + arr[x]);
                                            $tr.append($td);
                                            x++;
                                        } else {
                                            $tr.append($td.text(''));
                                        }

                                    }
                                    $tbody.append($tr);
                                }
                            }
                            $('#year_menu').append($selectItem);
                        });
                        $('table').append($tbody);
                    }
                });
            },

            slide: function () {
                ajaxCommit.showData('../DataList.do', 'slideservice', function (data) {
                    if (data.success) {
                        var produceData = $.parseJSON(data.message);
                        var $tbody = $("<tbody/>");
                        $.each(produceData, function (index, obj) {
                            var arr = [];
                            if (obj.cycle_start == 'week') {
                                obj.cycle_start = '入會周'
                            } else {
                                obj.cycle_start = '入會日'
                            }
                            arr.push(obj.slide_cycle + ' 周', obj.cycle_start, obj.create_name, obj.create_date);
                            var $tr = $("<tr/>");
                            for (var j = 0; j <= arr.length; j++) {
                                if (j < arr.length) {
                                    var $td = $("<td/>");
                                    $td.text(arr[j]);
                                    $tr.append($td);
                                } else {
                                    var $td = $("<td/>");
                                    var delbtn = $('<div class="red ui compact icon button"><i class="remove icon"></div>')
                                        .click(function () {
                                            $(this).parents('tr:first').remove();
                                        });
                                    var editbtn = $('<div class="green ui compact icon button"><i class="write icon"></div>')
                                        .click(function () {
                                            alert('ok');
                                        });
                                    $td.append(editbtn).append(delbtn);
                                    $tr.append($td);
                                }
                            }
                            $tbody.append($tr);
                        });
                        $('table').append($tbody);
                    }
                });
            }
        };


        var pv_validations = {
            pv_sum: {
                identifier: 'pv_sum',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請填入金額'
                    },
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            }
        };

        var consume_validations = {
            mode: {
                identifier: 'mode',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請選擇消費模式'
                    }
                ]
            },
            money: {
                identifier: 'money',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請填入金額'
                    },
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            generation_pv: {
                identifier: 'generation_pv',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請填入金額'
                    },
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            }
        };

        var produce_validations = {
            produce_name: {
                identifier: 'pname',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請輸入方案名稱'
                    },
                    {
                        type: 'verifyProduce',
                        prompt: '方案名稱重覆'
                    }
                ]
            },
            produce_price: {
                identifier: 'price',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請輸入方案售價'
                    },
                    {
                        type: 'searchConsume',
                        prompt: ''
                    }
                ]
            }
        };

        $.fn.form.settings.rules.verifyProduce = function (value) {
            if (value != '') {
                var result;
                $.ajax({
                    type: 'POST',
                    url: '../Verify.do',
                    data: {
                        flag: 'produceservice',
                        pname: value
                    },
                    async: false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.success)
                            result = false;
                        else
                            result = true;
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
        };

        $.fn.form.settings.rules.searchConsume = function (value) {
            if (value != '') {
                $.ajax({
                    type: 'POST',
                    url: '../CommonSearch.do',
                    data: {
                        flag: 'consumeservice',
                        price: value
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            if (data.message) {
                                $('#pv').val(data.pv_value);
                            } else {
                                $('#pv').val(parseInt(value) / parseInt(data.money));
                            }
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

        var amortization_validations = {
            produce_select: {
                identifier: 'produce_select',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請選則產品'
                    }
                ]
            },
            stage: {
                identifier: 'stage',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請填入分期期數'
                    },
                    {
                        type: 'integer',
                        prompt: '分期期數必須為整數數字'
                    },
                    {
                        type: 'parseStage',
                        prompt: ''
                    }
                ]
            }
        };

        $.fn.form.settings.rules.parseStage = function (value) {
            if (value != '') {
                $.each(tempData, function (index, obj) {
                    if (obj.pname == $('#produce').val()) {
                        var priceOfProduce = parseInt(obj.price);
                        var stage = parseInt(value);
                        var moneyOfStage = priceOfProduce / stage;

                        $.ajax({
                            type: 'POST',
                            url: '../CommonSearch.do',
                            data: {
                                flag: 'consumeservice',
                                price: moneyOfStage
                            },
                            dataType: 'json',
                            success: function (data) {
                                if (data.success) {
                                    if (!data.message) {
                                        var priceOf1PV = parseInt(data.money);
                                        $('#stage_money').val(moneyOfStage);
                                        $('#stage_pv').val(Math.round(moneyOfStage / priceOf1PV));
                                        $('#total_pv').val(Math.round(moneyOfStage / priceOf1PV) * stage);
                                        $('#remain_pv').val(priceOfProduce / priceOf1PV
                                            - Math.round(moneyOfStage / priceOf1PV) * stage);
                                    }
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
                })
            }
            return true;

        };

        var level_validations = {
            mem_level: {
                identifier: 'mem_level',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請輸入階級名稱'
                    },
                    {
                        type: 'verifyLevel',
                        prompt: '階級名稱重覆'
                    }
                ]
            }
        };

        $.fn.form.settings.rules.verifyLevel = function (value) {
            if (value != '') {
                var result;

                $.ajax({
                    type: 'POST',
                    url: '../Verify.do',
                    data: {
                        flag: 'member_levelservice',
                        mem_level: value
                    },
                    async: false,
                    dataType: 'json',
                    success: function (data) {
                        result = !data.success;
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
        };

        var cycle_validations = {
            work_year: {
                identifier: 'work_year',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請選擇工作年'
                    },
                    {
                        type: 'getWorkCycle',
                        prompt: ''
                    }
                ]
            },
            weekofmonth: {
                identifier: 'weekofmonth',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請選擇月周期'
                    },
                    {
                        type: 'getWorkCycle',
                        prompt: ''
                    }
                ]
            }, closedayofweek: {
                identifier: 'closedayofweek',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請選擇結帳日'
                    },
                    {
                        type: 'getWorkCycle',
                        prompt: ''
                    }
                ]
            }
        };

        $.fn.form.settings.rules.getWorkCycle = function (value) {
            hiddenWorkMonth();
            var year = $('#work_year').val();
            var weekOfMonth = $('#weekofmonth').val();
            var closeDayOfWeek = $('#closedayofweek').val();
            if (year != '' && weekOfMonth != '' && closeDayOfWeek != '') {
                var workCycle = ["first_month", "second_month", "third_month", "fourth_month",
                    "fifth_month", "sixth_month", "seventh_month", "eighth_month", "nineth_month",
                    "tenth_month", "eleventh_month", "twelfth_month", "thirteenth_month"];
                for (var i = 0; i < 365 / (weekOfMonth * 7); i++) {
                    $('#w' + (i + 1)).show();
                    $('#' + workCycle[i]).val(getWorkDayOfMonth(year, i + 1, weekOfMonth, closeDayOfWeek));
                }
            }
            return true;
        };

        var slide_validations = {
            slide_cycle: {
                identifier: 'slide_cycle',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請輸入滑動週期'
                    },
                    {
                        type: 'integer',
                        prompt: '滑動週期必須為整數數字'
                    }
                ]
            },
            cycle_start: {
                identifier: 'cycle_start',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請選擇週期起始'
                    }
                ]
            }
        };


        if ($('.ui.top.attached.tabular.menu .item').attr('data-tab') == 'pv') {
            show.pv();
            ajaxCommit.commit('create pv', "pvservice", pv_validations, '#pvbutton', show.pv);
        }
        $('.ui.top.attached.tabular.menu').on('click', '.item', function () {
            $('tbody').remove();
            switch ($(this).attr('data-tab')) {
                case 'pv':
                    show.pv();
                    ajaxCommit.commit('create pv', 'pvservice', pv_validations, '#pvbutton', show.pv);
                    break;
                case 'consume':
                    show.consume();
                    ajaxCommit.commit('create consume', 'consumeservice', consume_validations, '#csbutton', show.consume);
                    break;
                case 'produce':
                    show.produce();
                    ajaxCommit.commit('create produce', 'produceservice', produce_validations, '#pbutton', show.produce);
                    break;
                case 'amortization':
                    show.amortization();
                    ajaxCommit.commit('create amortization', 'amortizationservice', amortization_validations, '#sbutton', show.amortization);
                    break;
                case 'level':
                    show.level();
                    ajaxCommit.commit('create member_level', 'memberlevelservice', level_validations, '#lbutton', show.level);
                    break;
                case 'cycle':
                    show.cycle();
                    ajaxCommit.commit('create work_cycle', 'cycleservice', cycle_validations, '#cbutton', show.cycle);
                    hiddenWorkMonth();
                    break;
                case 'slide':
                    show.slide();
                    ajaxCommit.commit('create slide', 'slideservice', slide_validations, '#sdbutton', show.slide);
                    break;
            }
        });
    });
});