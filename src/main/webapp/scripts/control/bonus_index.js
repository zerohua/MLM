/**
 * Created by zero on 15/7/11.
 */
require(['../config/config'], function () {
    require(['jquery', 'ajaxCommit', 'semantic', 'header', 'dateExt', 'domReady!'], function ($, ajaxCommit) {
        ajaxCommit.showData('../DataList.do', 'cycleservice', function (data) {
            if (data.success) {
                if (data.message != '[]') {
                    var cycleData = $.parseJSON(data.message);
                    $.each(cycleData, function (index, obj) {
                        $(".thirteen.wide.column").append('<h4 class="red ui horizontal divider header"> <i' +
                            ' class="tag icon"></i>'
                            + obj.work_year + '年</h4>');
                        var workMonth = ['一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '十一', '十二', '十三'];
                        var columnArray = ['one', 'two', 'three', 'four'];
                        var date = [[obj.first_month, obj.second_month, obj.third_month, obj.fourth_month],
                            [obj.fifth_month, obj.sixth_month, obj.seventh_month, obj.eighth_month],
                            [obj.nineth_month, obj.tenth_month, obj.seventh_month, obj.twelfth_month],
                            [obj.thirteenth_month]
                        ];
                        var columns = 4;
                        var monthsOfYear = Math.floor(365 / (parseInt(obj.weekofmonth) * 7));
                        var rows = Math.ceil(monthsOfYear / columns);
                        var count = 0;
                        var $grid = $('<div class="ui ' + columnArray[columns - 1] + ' column grid"></div>');
                        var currentDate = new Date().Format('yyyy/MM/dd');
                        for (var i = 0; i < rows; i++) {
                            var $row = $('<div class="row"></div>');
                            for (var j = 0; j < columns; j++) {
                                if (date[i][j] != undefined) {
                                    var dateStr = date[i][j].substr(0, 10);
                                    if ((Date.parse(currentDate)).valueOf() > ((Date.parse(dateStr)).valueOf())) {
                                        var $col = $('<div class="column"><div class="blue ui disabled button" >'
                                            + obj.work_year + ' 第' + workMonth[count] + '工作月<br>' + date[i][j] + '</div></div>');
                                        count++;
                                        $row.append($col);
                                    }
                                }
                            }
                            $grid.append($row);
                        }
                        $('.thirteen.wide.column').append($grid);
                    })
                }
            }
        })

    })
});
