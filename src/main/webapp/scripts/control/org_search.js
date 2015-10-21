/**
 * Created by zero on 15/6/22.
 */
require(['../config/config'], function () {

    require(['jquery', 'ajaxCommit', 'line', 'semantic', 'header', 'domReady!'], function ($, ajaxCommit) {

        $('.menu .item').tab({
            history: false
        });

        $('.menu').on('click', '.item', function () {
            $(this)
                .addClass('active')
                .siblings('.item')
                .removeClass('active');
        });

        var memberId;
        var who = sessionStorage.getItem('who');
        if (who == 'member') {
            var memberNo = sessionStorage.getItem('memberNo');
            $.ajax({
                type: 'POST',
                url: '../CommonSearch.do',
                data: {
                    flag: 'memberservice',
                    memberNo: memberNo
                },
                dataType: 'json',
                async: false,
                success: function (data) {
                    if (data.success) {
                        memberId = data.message;
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
            })
        } else {
            $.ajax({
                type: 'POST',
                url: '../GetRow.do',
                data: {
                    flag: 'memberservice',
                    number: 1
                },
                async: false,
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        memberId = data.message;
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
            })
        }

        function Pagination(obj) {
            this.id = null;  //div id
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
        }

        Pagination.prototype.init = function () {
            if (this.data == undefined) {
                this.data = {}
            }
            this.div = $('#' + this.id);
            this.fetchData(1);
        };

        var orgLeft = '';
        var orgRight = '';
        var pageNumTemp = '';
        Pagination.prototype.fetchData = function (pageNum) {
            this.barPos = pageNum;
            this.orgList = '';
            var that = this;
            $.ajax({
                url: that.url,
                data: that.data,
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    if (data.success) {
                        that.orgList = $.parseJSON(data.orgList);
                        if (that.data.line == 'left') {
                            orgLeft = that.orgList;
                        } else {
                            orgRight = that.orgList;
                        }
                        that.total = that.orgList.length;
                        var tmp = that.total % that.pageSize;
                        var num = Math.floor(that.total / that.pageSize);
                        that.totalPage = tmp == 0 ? num : num + 1;
                        that.showUI();
                        that.success(that.orgList);
                    }
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
                        pageNumTemp = parseInt(n);
                        p.showData(pageNumTemp);

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
            if (endData > this.orgList.length) {
                endData = this.orgList.length;
            }
            this.orgList.sort(function (a, b) {
                var a1 = a.generation, b1 = b.generation;
                if (a1 == b1) return 0;
                return a1 > b1 ? 1 : -1;
            });
            $.each(this.orgList, function (index, obj) {
                if (index >= startData && index < endData) {
                    if (obj.status == 'normal') {
                        var status = '有效';
                    }
                    var arr = [];
                    arr.push(obj.generation, obj.member_no, obj.level, obj.name, obj.recommend, obj.upper, obj.position,
                        status, obj.subtract, obj.create_date);

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


        var getOrgList = new Pagination({
            url: '../GetOrgList.do',
            pageSize: 14,//每页显示的记录数
            barSize: 10,//分页工具条上展现的页码数
            data: {
                flag: 'memberextservice',
                numberId: memberId,
            },
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

        //if ($('.ui.pointing.secondary.menu .item').attr('data-tab') == 'left_list') {
        getOrgList.id = 'left_pagination';
        getOrgList.data.line = 'left';
        getOrgList.init();

        $('#list_search_field').show();
        $('#tree_search_field').hide();
        //}

        $('.ui.pointing.secondary.menu').on('click', '.item', function () {
            $('tbody').remove();
            switch ($(this).attr('data-tab')) {
                case 'left_list':
                    if (orgLeft.length != 0) {
                        getOrgList.data.line = 'left';
                        getOrgList.orgList = orgLeft;
                        getOrgList.showUI();
                        getOrgList.showData((pageNumTemp != '') ? pageNumTemp : 1);
                    } else {
                        getOrgList.id = 'left_pagination';
                        getOrgList.data.line = 'left';
                        getOrgList.init();
                    }
                    $('#list_search_field').show();
                    $('#tree_search_field').hide();
                    break;
                case 'right_list':
                    if (orgRight.length != 0) {
                        getOrgList.data.line = 'right';
                        getOrgList.orgList = orgRight;
                        getOrgList.showUI();
                        getOrgList.showData(pageNumTemp);
                    }
                    getOrgList.id = 'right_pagination';
                    getOrgList.data.line = 'right';
                    getOrgList.init();
                    $('#list_search_field').show();
                    $('#tree_search_field').hide();
                    break;
                case 'binary_tree':
                    $('.show_binaryTree').children().remove();
                    binary_tree(memberId);
                    $('#list_search_field').hide();
                    $('#tree_search_field').show();
                    break;
            }
        });

        $('#tree_search_button').click(function () {
            var value = $('#tree_search').val().toUpperCase();
            if (value != '') {
                var regex = /^\w+$/;
                if (regex.test(value)) {
                    $.ajax({
                        type: 'POST',
                        url: '../CommonSearch.do',
                        data: {
                            flag: 'memberservice',
                            memberNo: value
                        },
                        async: false,
                        dataType: 'json',
                        success: function (data) {
                            if (data.success) {
                                $('.show_binaryTree').children().remove();
                                binary_tree(data.message);
                            } else {
                                alert('找不到此會員');
                            }
                        },
                        error: function (jqXHR) {
                            if (jqXHR == '911') {
                                sessionStorage.clear();
                                alert('閒置時間過久，請重新登入..');
                                window.location.href = '../login.html'
                            } else {
                                alert("發生錯誤：" + jqXHR.status);
                            }
                        }
                    });
                } else {
                    $.ajax({
                        type: 'POST',
                        url: 'CommonSearch.do',
                        data: {
                            flag: 'memberservice',
                            memberName: value
                        },
                        async: false,
                        dataType: 'json',
                        success: function (data) {
                            if (data.success) {
                                $('.show_binaryTree').children().remove();
                                binary_tree(data.message);
                            } else {
                                alert('找不到此會員');
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
            }
        });

        function binary_tree(mId) {
            ajaxCommit.showData('../GetOrgMap.do', 'memberextservice', function (data) {
                if (data.success) {
                    var mapList = $.parseJSON(data.message);
                    var setColor;
                    var $showBinaryTree = $('.show_binaryTree');
                    var arr = ['one', 'two', 'four', 'eight', 'sixteen'];
                    var x = 1;
                    for (var a = 0; a < 5; a++) {
                        var $grid = $('<div class ="ui ' + arr[a] + ' column grid"></div>');
                        for (var b = 0; b < x; b++) {

                            switch (mapList[(x - 1) + b].level) {
                                case 1:
                                    setColor = 'purple';
                                    break;
                                case 2:
                                    setColor = 'yellow';
                                    break;
                                case 3:
                                    setColor = 'red';
                                    break;
                                case 4:
                                    setColor = 'blue';
                                    break;
                                case 5:
                                    setColor = 'green';
                                    break;
                                case 6:
                                    setColor = 'teal';
                                    break;
                                default :
                                    setColor = 'black';
                            }

                            var $popUp = $('<div class="ui popup"></div>');
                            var $popGrid = $('<div class="ui one column divided center aligned grid"></div>');
                            var $popCol = $('<div class="green ui column">8</div>');

                            var $icon = $('<h6 class="ui center aligned icon header"><i class="' + setColor
                                + ' user icon link"></i>' + mapList[(x - 1) + b].memberName + '</h6>');
                            var $column = $('<div class="column"></div>');

                            var id = mapList[(x - 1) + b].memberId;
                            var upper = mapList[(x - 1) + b].upper;

                            if (id != undefined && id != 1) {
                                if (x == 1) {
                                    if (upper != 0) {
                                        $icon.bind('click', {msg: upper}, function (event) {
                                            $('.show_binaryTree').children().remove();
                                            binary_tree(event.data.msg);
                                        });
                                    }
                                } else {
                                    $icon.bind('click', {msg: id}, function (event) {
                                        $('.show_binaryTree').children().remove();
                                        binary_tree(event.data.msg);
                                    });
                                }
                            }

                            if (mapList[(x - 1) + b].level == 1 && id != undefined) {
                                $popCol.click(function () {
                                    alert('slid 8 week testing');
                                });
                                $popGrid.append($popCol);
                                $popUp.append($popGrid);
                                $icon.append($popUp);
                            }

                            $column.append($icon);
                            $grid.append($column);


                        }
                        x *= 2;
                        $showBinaryTree.append($grid);

                        $('.header .icon').popup({
                            inline: true,
                            hoverable: true,
                            position: 'top center',
                            delay: {
                                show: 300,
                                hide: 800
                            }
                        });

                    }

                    var x_begin = 240, y = 190, c = 484, c2 = 968;
                    var z = 1;
                    var lineParam = {color: "red", zindex: 1, stroke: '2', style: 'solid'};
                    for (var i = 0; i < 4; i++) {
                        for (var j = 0; j < z; j++) {
                            $showBinaryTree.line(x_begin + c2 * j, y + i * 100, x_begin + c2 * j + c, y + i * 100, lineParam);
                            $showBinaryTree.line(c + c2 * j, y + i * 100, c + c2 * j, y + i * 100 - 10, lineParam);
                            $showBinaryTree.line(x_begin + c2 * j, y + i * 100, x_begin + c2 * j, y + i * 100 + 10, lineParam);
                            $showBinaryTree.line(x_begin + c2 * j + c, y + i * 100, x_begin + c2 * j + c, y + i * 100 + 10, lineParam);
                        }
                        c2 /= 2;
                        x_begin /= 2;
                        z *= 2;
                        c /= 2;
                    }
                }
            }, mId);

        }

        $('#list_search').on("keyup", function () {
            var value = $(this).val().toUpperCase();
            var tmp;
            var num;
            if (value == '') {
                getOrgList.orgList = (getOrgList.data.line == 'left') ? orgLeft : orgRight;
                getOrgList.total = getOrgList.orgList.length;
                tmp = getOrgList.total % getOrgList.pageSize;
                num = Math.floor(getOrgList.total / getOrgList.pageSize);
                getOrgList.totalPage = tmp == 0 ? num : num + 1;
                getOrgList.showUI();
            } else {

                getOrgList.orgList = getObjects((getOrgList.data.line == 'left') ? orgLeft : orgRight,
                    'member_no', 'name', 'recommend', 'upper', value);
                getOrgList.total = getOrgList.orgList.length;
                tmp = getOrgList.total % getOrgList.pageSize;
                num = Math.floor(getOrgList.total / getOrgList.pageSize);
                getOrgList.totalPage = tmp == 0 ? num : num + 1;
                getOrgList.showUI();
            }
            if (getOrgList.total != 0) {
                getOrgList.showData(1);
            }


        });

        function getObjects(obj, key, key2, key3, key4, val) {
            var objects = [];
            for (var i in obj) {
                if (!obj.hasOwnProperty(i)) continue;
                if (typeof obj[i] == 'object') {
                    objects = objects.concat(getObjects(obj[i], key, key2, key3, key4, val));
                } else if (i == key && obj[key].indexOf(val) != -1) {
                    objects.push(obj);
                } else if (i == key2 && obj[key2].indexOf(val) != -1) {
                    objects.push(obj);
                } else if (i == key3 && obj[key3].indexOf(val) != -1) {
                    objects.push(obj);
                } else if (i == key4 && obj[key4].indexOf(val) != -1) {
                    objects.push(obj);
                }
            }
            return objects;
        }


    });
});