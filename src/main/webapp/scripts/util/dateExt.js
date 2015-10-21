/**
 * Created by zero on 15/6/21.
 */
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

var now = new Date();
var day = ("0" + now.getDate()).slice(-2);
var month = ("0" + (now.getMonth() + 1)).slice(-2);
var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
$('input[name="create_date"]').val(today);
$('input[name="create_name"]').val(sessionStorage.getItem('user'));


/**
 * 得到設定周的第一天是星期幾？
 * if currentDay < closeDay --> firstDayOfWeek = currentDay + ((closeDay - currentDay) -6)
 * if currentDay > closeDay --> firstDayOfWeek = currentDay -((currentDay -closeDay)-1)
 * 0:sunday 1:monday 2:tuesday 3:wednesday 4:thursday 5:friday 6:saturday
 * @param closeDay 結帳日
 * @param currentDay 當前日
 */
function getFirstDayOfWeek(closeDay, currentDay) {

    var firstDayOfWeek;
    if (currentDay < closeDay) {
        firstDayOfWeek = currentDay + ((closeDay - currentDay) - 6);
    } else {
        firstDayOfWeek = currentDay - ((currentDay - closeDay) - 1);
    }
    return firstDayOfWeek;
}
/**
 * 找出輸入年第1周的第1天
 * @param year
 * @param closeDayOfWeek 周結帳日（0 ﹣ 6）0:sunday 1:monday 2:tuesday 3:wednesday 4:thursday 5:friday 6:saturday
 *
 */
function getFirstDate(year, closeDayOfWeek) {
    var date = new Date(year, 0, 1);
    var weekNum = date.getDay();
    if (weekNum <= closeDayOfWeek) {
        date.setDate(date.getDate() + ((closeDayOfWeek - weekNum) - 6));
    } else {

        date.setDate(date.getDate() - ((weekNum - closeDayOfWeek) - 1));
    }
    return date.Format("yyyy-MM-dd");
}
/**
 * 輸入年和1 - 13 個工作月，得到每月的第一天和最後一天
 * @param year
 * @param month
 * @param weekOfMonth 月周期
 * @param closeDayOfWeek 周結帳日（0 ﹣ 6）0:sunday 1:monday 2:tuesday 3:wednesday 4:thursday 5:friday 6:saturday
 * @returns {string}
 */
function getWorkDayOfMonth(year, month, weekOfMonth, closeDayOfWeek) {
    var date = new Date(year, 0, 1);
    var weekNum = date.getDay();
    console.log(weekNum);
    var firstDay;
    if (weekNum <= closeDayOfWeek) {
        firstDay = date.setDate(date.getDate() + ((closeDayOfWeek - weekNum) - 6));
    } else {
        firstDay = date.setDate(date.getDate() - ((weekNum - closeDayOfWeek) - 1));
    }
    // var firstDay = date.setDate(date.getDate()-(weekNum+1));
    var startDay;
    var lastDay;
    if (month == 1) {
        startDay = new Date(firstDay);
        lastDay = new Date(date.setDate(date.getDate() + (month * weekOfMonth * 7)) - 1);
    } else {
        startDay = new Date(date.setDate(date.getDate() + (month - 1) * weekOfMonth * 7));
        lastDay = new Date(date.setDate(date.getDate() + weekOfMonth * 7 - 1));
    }


    return startDay.Format("yyyy/MM/dd") + "~" + lastDay.Format("yyyy/MM/dd");
}

