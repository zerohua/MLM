/**
 * Created by zero on 15/7/15.
 */
require(['../config/config'], function () {
    require(['jquery', 'ajaxCommit', 'semantic', 'header', 'dateExt', 'domReady!'], function ($, ajaxCommit) {

        function addZero(str, len) {
            str = '' + str;
            return str.length >= len ? str : new Array(len - str.length + 1).join("0") + str;
        }

        ajaxCommit.showData('../DataList.do', 'cycleservice', function (data) {
            if (data.message != '[]') {
                var cycleData = $.parseJSON(data.message);
                $.each(cycleData, function (index, obj) {
                        var $content = $(".thirteen.wide.column");
                        $content.append('<h4 class="red ui horizontal divider header">' +
                            '<i class="tag icon"></i>' + obj.work_year + '年</h4>');

                        var workMonth = ['一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二', '十三'];
                        var dateCycle = [obj.first_month, obj.second_month, obj.third_month, obj.fourth_month,
                            obj.fifth_month, obj.sixth_month, obj.seventh_month, obj.eighth_month,
                            obj.nineth_month, obj.tenth_month, obj.eleventh_month, obj.twelfth_month,
                            obj.thirteenth_month];

                        var currentDate = new Date().Format('yyyy/MM/dd');
                        for (var i = 0; i < dateCycle.length; i++) {

                            var dateStr = dateCycle[i].substr(0, 10);
                            if ((Date.parse(currentDate)).valueOf() > ((Date.parse(dateStr)).valueOf())) {
                                var date = new Date(dateStr);
                                var starOfFirst = new Date(dateStr);
                                var endOfFirst = new Date(date.setDate(date.getDate() + 6));
                                var starOfSecond = new Date(date.setDate(date.getDate() + 1));
                                var endOfTSecond = new Date(date.setDate(date.getDate() + 6));
                                var startOfThird = new Date(date.setDate(date.getDate() + 1));
                                var endOfThird = new Date(date.setDate(date.getDate() + 6));
                                var startOfFourth = new Date(date.setDate(date.getDate() + 1));
                                var endOfFourth = new Date(date.setDate(date.getDate() + 6));

                                var $segment = $('<div class="ui account vertical segment"></div>');
                                var $grid = $('<div class="ui account two column middle aligned grid "></div>');
                                var $uiSegment = $('<div class="ui info segment"></div>');
                                var $col1 = $('<div class="column"></div>');
                                var $workMonth = $('<h5 class="ui header">' + obj.work_year + ' 第' + workMonth[i] + '個工作月 ' +
                                    '<span class="ui type label">' + dateCycle[i] + '</span></h5>');
                                var $week1 = $('<p>第 ' + addZero((4 * i + 1), 2) + ' 周：' + starOfFirst.Format("yyyy/MM/dd") + '~' +
                                    endOfFirst.Format('yyyy/MM/dd') + '</p>');
                                var $weekBtn1 = '';
                                ajaxCommit.showData('../CommonSearch.do', 'weekaccountservice', function (data) {
                                    if (data.success) {
                                        $weekBtn1 = $('<span class="ui disabled tiny blue button">' +
                                            '<i class="checkmark box icon"></i>已 結 算</span>');
                                    } else if (!data.success) {
                                        $weekBtn1 = $('<span class="ui tiny green button">' +
                                            '<i class="calculator icon"></i>結 算</span>');
                                    }
                                }, obj.work_year + ',' + (i * 4 + 1) + ',ctpl0001', false);

                                $weekBtn1.bind('click', {
                                        msg: obj.work_year + '~' + (i * 4 + 1) + '~' + starOfFirst.Format("yyyy/MM/dd") + '~' +
                                        endOfFirst.Format('yyyy/MM/dd'),
                                        btn: $weekBtn1
                                    }, function (event) {
                                        event.data.btn.addClass('loading');
                                        ajaxCommit.showData('../CloseAccount.do', 'memberservice', function (data) {
                                            if (data.success) {
                                                event.data.btn.removeClass('loading green');
                                                event.data.btn.addClass('disabled blue');
                                                event.data.btn.html('<i class="checkmark box icon"></i>已 結 算');
                                            } else if (!data.success) {
                                                event.data.btn.removeClass('loading');
                                            }
                                        }, event.data.msg);

                                    }
                                );
                                var $week2 = $('<p>第 ' + addZero((4 * i + 2), 2) + ' 周：' + starOfSecond.Format("yyyy/MM/dd") + '~' +
                                    endOfTSecond.Format('yyyy/MM/dd') + '</p>');
                                var $weekBtn2 = '';
                                ajaxCommit.showData('../CommonSearch.do', 'weekaccountservice', function (data) {
                                    if (data.success) {
                                        $weekBtn2 = $('<span class="ui disabled tiny blue button">' +
                                            '<i class="checkmark box icon"></i>已 結 算</span>');
                                    } else if (!data.success) {
                                        $weekBtn2 = $('<span class="ui tiny green button">' +
                                            '<i class="calculator icon"></i>結 算</span>');
                                    }
                                }, obj.work_year + ',' + (i * 4 + 2) + ',ctpl0001', false);
                                $weekBtn2.bind('click', {
                                    msg: obj.work_year + '~' + (i * 4 + 2) + '~' + starOfSecond.Format("yyyy/MM/dd") + '~' +
                                    endOfTSecond.Format('yyyy/MM/dd'),
                                    btn: $weekBtn2
                                }, function (event) {
                                    event.data.btn.addClass('loading');
                                    ajaxCommit.showData('../CloseAccount.do', 'memberservice', function (data) {
                                        if (data.success) {
                                            event.data.btn.removeClass('loading green');
                                            event.data.btn.addClass('disabled blue');
                                            event.data.btn.html('<i class="checkmark box icon"></i>已 結 算');
                                        } else if (!data.success) {
                                            event.data.btn.removeClass('loading');
                                        }
                                    }, event.data.msg);
                                });
                                var $week3 = $('<p>第 ' + addZero((4 * i + 3), 2) + ' 周：' + startOfThird.Format("yyyy/MM/dd") + '~' +
                                    endOfThird.Format('yyyy/MM/dd') + '</p>');
                                var $weekBtn3 = '';
                                ajaxCommit.showData('../CommonSearch.do', 'weekaccountservice', function (data) {
                                    if (data.success) {
                                        $weekBtn3 = $('<span class="ui disabled tiny blue button">' +
                                            '<i class="checkmark box icon"></i>已 結 算</span>');
                                    } else if (!data.success) {
                                        $weekBtn3 = $('<span class="ui tiny green button">' +
                                            '<i class="calculator icon"></i>結 算</span>');
                                    }
                                }, obj.work_year + ',' + (i * 4 + 3) + ',ctpl0001', false);
                                $weekBtn3.bind('click', {
                                    msg: obj.work_year + '~' + (i * 4 + 3) + '~' + starOfSecond.Format("yyyy/MM/dd") + '~' +
                                    endOfTSecond.Format('yyyy/MM/dd'),
                                    btn: $weekBtn3
                                }, function (event) {
                                    event.data.btn.addClass('loading');
                                    ajaxCommit.showData('../CloseAccount.do', 'memberservice', function (data) {
                                        if (data.success) {
                                            event.data.btn.removeClass('loading green');
                                            event.data.btn.addClass('disabled blue');
                                            event.data.btn.html('<i class="checkmark box icon"></i>已 結 算');
                                        } else if (!data.success) {
                                            event.data.btn.removeClass('loading');
                                        }
                                    }, event.data.msg);
                                });
                                var $week4 = $('<p>第 ' + addZero((4 * i + 4), 2) + ' 周：' + startOfFourth.Format("yyyy/MM/dd") + '~' +
                                    endOfFourth.Format('yyyy/MM/dd') + '</p>');
                                var $weekBtn4 = '';
                                ajaxCommit.showData('../CommonSearch.do', 'weekaccountservice', function (data) {
                                    if (data.success) {
                                        $weekBtn4 = $('<span class="ui disabled tiny blue button">' +
                                            '<i class="checkmark box icon"></i>已 結 算</span>');
                                    } else if (!data.success) {
                                        $weekBtn4 = $('<span class="ui tiny green button">' +
                                            '<i class="calculator icon"></i>結 算</span>');
                                    }
                                }, obj.work_year + ',' + (i * 4 + 4) + ',ctpl0001', false);
                                $weekBtn4.bind('click', {
                                    msg: obj.work_year + '~' + (i * 4 + 4) + '~' + starOfSecond.Format("yyyy/MM/dd") + '~' +
                                    endOfTSecond.Format('yyyy/MM/dd'),
                                    btn: $weekBtn4
                                }, function (event) {
                                    event.data.btn.addClass('loading');
                                    ajaxCommit.showData('../CloseAccount.do', 'memberservice', function (data) {
                                        if (data.success) {
                                            event.data.btn.removeClass('loading green');
                                            event.data.btn.addClass('disabled blue');
                                            event.data.btn.html('<i class="checkmark box icon"></i>已 結 算');
                                        } else if (!data.success) {
                                            event.data.btn.removeClass('loading');
                                        }
                                    }, event.data.msg);
                                });

                                var $col2 = $('<div class="center aligned column"></div>')
                                var $monthBtn = $('<div class="ui big circular green button">' +
                                    '<i class="calculator icon"></i>月結算</div>');
                                $monthBtn.bind('click', function () {
                                    alert('month button');
                                });

                                $week1.append($weekBtn1);
                                $week2.append($weekBtn2);
                                $week3.append($weekBtn3);
                                $week4.append($weekBtn4);
                                $uiSegment.append($workMonth).append($week1).append($week2).append($week3).append($week4);
                                $col1.append($uiSegment);
                                $col2.append($monthBtn);
                                $grid.append($col1).append($col2);
                                $segment.append($grid);
                                $content.append($segment);
                            }
                        }
                    }
                )
            }
        })
    })
})
;
