/**
 * Created by zero on 15/4/25.
 */
$(document).ready(function () {



    $('.menu .item').tab({
        history: false
    });

    $('.menu').on('click', '.item', function () {
        $(this)
            .addClass('active')
            .siblings('.item')
            .removeClass('active');
    });


    if ($('.ui.pointing.secondary.menu .item').attr('data-tab') == 'left_list') {
        leftPagination();
    }

    $('.ui.pointing.secondary.menu').on('click', '.item', function () {
        orgArr = [];
        var $today = $('tbody');
        $today.remove();
        if ($(this).attr('data-tab') == "left_list") {
            $today.remove();
            leftPagination();
        } else {
            if (($(this).attr('data-tab') == "right_list")) {
                $today.remove();
                rightPagination();
            } else {
                $('.show_binaryTree').children().remove();
                binary_tree();
            }
        }
    });


    function binary_tree() {
        var $showBinaryTree = $('.show_binaryTree');


        var arr = ['one', 'two', 'four', 'eight', 'sixteen'];
        var x = 1;
        for (var i = 0; i < 5; i++) {
            var $grid = $('<div class ="ui ' + arr[i] + ' column grid"></div>');
            for (var j = 0; j < x; j++) {
                var $icon = $('<h6 class="ui center aligned icon header"><i class="user icon"></i>friend</h6>');
                var $column = $('<div class="column"></div>');
                $column.append($icon);
                $grid.append($column);
            }
            x *= 2;
            $showBinaryTree.append($grid);

        }

        var x_begin = 240, y = 190, c = 484, c2 = 968;
        var z = 1;
        for (var i = 0; i < 4; i++) {

            for (var j = 0; j < z; j++) {
                $showBinaryTree.line(x_begin + c2 * j, y + i * 100, x_begin + c2 * j + c, y + i * 100, {
                    color: "red",
                    zindex: 1,
                    stroke: '2',
                    style: 'solid'
                });
                $showBinaryTree.line(c + c2 * j, y + i * 100, c + c2 * j, y + i * 100 - 10, {
                    color: "red",
                    zindex: 1,
                    stroke: '2',
                    style: 'solid'
                });
                $showBinaryTree.line(x_begin + c2 * j, y + i * 100, x_begin + c2 * j, y + i * 100 + 10, {
                    color: "red",
                    zindex: 1,
                    stroke: '2',
                    style: 'solid'
                });
                $showBinaryTree.line(x_begin + c2 * j + c, y + i * 100, x_begin + c2 * j + c, y + i * 100 + 10, {
                    color: "red",
                    zindex: 1,
                    stroke: '2',
                    style: 'solid'
                });
            }
            c2 /= 2 , x_begin /= 2 , z *= 2, c /= 2;

        }
    }
});


function getLeft(id, data) {
    var lid;
    $.each(data, function (index, obj) {
        if (obj.member == id) {
            lid = obj.left_lower;
            getOrg(lid, data, 2);
        }
    });
}

function getRight(id, data) {
    var rid;
    $.each(data, function (index, obj) {
        if (obj.member == id) {
            rid = obj.right_lower;
            getOrg(rid, data, 2);
        }
    });
}


var orgArr = [];

function getOrg(id, data, j) {
    var rid, lid;

    $.each(data, function (index, obj) {
        var org = {};
        if (obj.member == id) {
            lid = obj.left_lower;
            rid = obj.right_lower;
            orgData(org, obj, j);
            orgArr.push(org);
            //if (lid != undefined) {
            //    console.log("left" + lid + "," + j);
            //}
            //if (rid != undefined) {
            //    console.log("right" + rid + "," + j);
            //}
        }
    });
    j++;
    if (lid != undefined) {
        getOrg(lid, data, j);
    }
    if (rid != undefined) {
        getOrg(rid, data, j);
    }


}

function orgData(org, obj, j) {
    org.generaion = j;
    org.member_no = obj.member_no;
    org.level = obj.level;
    org.name = obj.name;
    org.recommend = getNameById(obj.recommend);
    org.upper = getNameById(obj.upper);
    org.position = getPosition(obj.upper, obj._id);
    org.status = obj.status;
    org.subtract = '';
    org.create_date = obj.create_date;

}


function getNameById(upperId) {
    var result;
    $.ajax({
        type: 'POST',
        url: '../CommonSearch.do',
        data: {
            flag: 'memberservice',
            upperId: upperId,
            pos: 'org'
        },
        async: false,
        dataType: 'json',
        success: function (data) {
            if (data.success) {
                result = data.message;
            }
        },
        error: function (jqXHR) {
            alert("發生錯誤：" + jqXHR.status);
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
            alert("發生錯誤：" + jqXHR.status);
        }
    });
    return result;
}

function Pagination(obj) {
    this.id = obj.id;  //div id
    this.url = obj.url;
    this.pageSize = obj.pageSize;
    this.pageNum = 1; //current page number
    this.total = 0; //total count
    this.totalPage = 0;
    this.pos = obj.pos;
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
    this.data.pageNum = pageNum;
    this.data.pageSize = this.pageSize;
    //this.data.memberData = '';
    var that = this;
    $.ajax({
        url: that.url,
        data: that.data,
        type: 'post',
        dataType: 'json',
        success: function (data) {
            var memberData = $.parseJSON(data.message);
            //if (this.pos == 'right') {
            //    getRight(1, memberData);
            //}
            //else {
            //    getLeft(1, memberData);
            //}
            //that.total = orgArr.length;
            //var tmp = that.total % that.pageSize;
            //var num = Math.floor(that.total / that.pageSize);
            //that.totalPage = tmp == 0 ? num : num + 1;
            that.success(memberData);
            //that.showUI();
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
    this.div.append('<a class="item"><i class="left arrow icon"></i> 上一页</a>');
    for (var i = 0; i < currentBarSize; i++) {
        this.div.append('<a class="item">' + (this.pageNum + i) + '</a>');
    }
    this.div.append('<a class="item"> 下一页 <i class="icon right arrow"></i></a>');

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
    if (endData > orgArr.length) {
        endData = orgArr.length;
    }
    orgArr.sort(function (a, b) {
        var a1 = a.generaion, b1 = b.generaion;
        if (a1 == b1) return 0;
        return a1 > b1 ? 1 : -1;
    });
    $.each(orgArr, function (index, obj) {
        if (index >= startData && index < endData) {
            var arr = [];
            arr.push(obj.generaion, obj.member_no, obj.level, obj.name, obj.recommend, obj.upper, obj.position,
                obj.status, obj.subtract, obj.create_date);

            var $tr = $("<tr/>");
            for (var j = 0; j < arr.length; j++) {
                var $td = $("<td/>");
                $td.text(arr[j]);
                $tr.append($td);
            }
            $tbody.append($tr);
        }
    });
    $('table').append($tbody);
};

function leftPagination() {
    var left = new Pagination({
        id: 'left_pagination',
        url: '../DataList.do',
        pos: 'left',
        pageSize: 3,//每页显示的记录数
        barSize: 5,//分页工具条上展现的页码数
        data: {flag: 'memberservice,memberextservice'},
        success: function (data) { //回调函数
            if(who == 'member') {
                getLeft(memberId,data);
            } else {
                getLeft(1, data);
            }
            this.total = orgArr.length;
            var tmp = this.total % this.pageSize;
            var num = Math.floor(this.total / this.pageSize);
            this.totalPage = tmp == 0 ? num : num + 1;
            this.showUI();
            this.showData(this.pageNum);


        },
        error: function (msg) {//回调函数
            console.log(msg);
        }
    });

}

function rightPagination() {
    var right = new Pagination({
        id: 'right_pagination',
        url: '../DataList.do',
        pos: 'right',
        pageSize: 10,//每页显示的记录数
        barSize: 5,//分页工具条上展现的页码数
        data: {flag: 'memberservice,memberextservice'},
        success: function (data) { //回调函数
            if(who == 'member') {
                getRight(memberId,data);
            } else {
                getRight(1, data);
            }
            this.total = orgArr.length;
            var tmp = this.total % this.pageSize;
            var num = Math.floor(this.total / this.pageSize);
            this.totalPage = tmp == 0 ? num : num + 1;
            this.showUI();
            this.showData(this.pageNum);

        },
        error: function (msg) {//回调函数
            console.log(msg);
        }
    });
}
var memberId;
var who = sessionStorage.getItem('who');
console.log(who);
if ( who == 'member') {
    var memberNo = sessionStorage.getItem('memberNo');
    $.ajax({
        type: 'POST',
        url: '../CommonSearch.do',
        data: {
            flag: 'memberservice',
            memberNo : memberNo
        },
        async:false,
        dataType: 'json',
        success: function (data) {
            if(data.success) {
                memberId = data.message;
            }
        },
        error: function (jqXHR) {
            alert("發生錯誤：" + jqXHR.status);
        }
    })
}

