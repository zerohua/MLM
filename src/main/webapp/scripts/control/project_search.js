/**
 * Created by zero on 15/6/22.
 */
require(['../config/config'], function () {

    require(['jquery', 'semantic', 'header', 'domReady!'], function ($, ajaxCommit) {
        $('.menu').on('click', '.item', function () {
            $(this)
                .addClass('active')
                .siblings('.item')
                .removeClass('active');
        });

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

                memberList.memberData = getObjects(memberList.memberDataTemp, 'project_no', 'member_no', 'member_name', value);
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
            //this.data.pageNum = pageNum;
            //d this.pageSize = this.pageSize;
            this.barPos = pageNum;
            this.memberData = '';
            this.memberDataTemp = '';
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
                    that.showUI();
                    that.success(that.memberData);
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
                    if (obj.status == 'normal') {
                        obj.status = '有效';
                    }
                    var arr = [];
                    arr.push(obj.project_no, obj.member_no, obj.member_name, obj.pname, obj.price, obj.pv, obj.pay, obj.pay_method);

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
            $('.ui.active.loader').hide();
        };

        var memberList = new Pagination({
            id: 'pagination',
            url: '../DataList.do',
            pageSize: 14,//每页显示的记录数
            barSize: 10,//分页工具条上展现的页码数
            data: {flag: 'projectservice'},
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

    });
});