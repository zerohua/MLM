/**
 * Created by zero on 15/6/23.
 */
require(['../config/config'], function () {
    require(['jquery', 'ajaxCommit', 'domReady', 'serializeObject', 'semantic', 'header', 'dateExt', 'common'
    ], function ($, ajaxCommit, domReady) {

        var menuData = function () {
            ajaxCommit.showData('../DataList.do', 'projectservice', function (data) {
                if (data.success) {
                    var produceData = $.parseJSON(data.message);
                    $.each(produceData, function (index, obj) {
                        if ((obj.project_no).indexOf('S') != -1) {
                            $('#project_menu').append('<div class="item" data-value="' + obj.project_no + '">'
                                + obj.member_no + " " + obj.project_no + '</div>');
                        }
                    });

                }
            }, 'no', true);
        };

        domReady(function () {
            var update = $('#update');
            var supdate = $('#supdate');

            menuData();
            var now = new Date();
            var day = now.Format('yyyy/MM/dd');

            update.click(function () {
                $.ajax({
                    type: 'POST',
                    url: '../DataUpdate.do',
                    data: {
                        flag: 'projectservice',
                        paramName: 'pay',
                        paramValue: 'yes',
                        columnName: 'project_no',
                        columnValue: projectNo
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $('.image').html(' <i class = " green check circle icon"></i>');
                            $('.description').html(data.message);
                            //$('.small.ui.modal').modal('setting', 'transition', 'horizontal flip').modal('show');
                            //$('.ui.table').hide();
                            //$('#update').hide();
                            //$('#project_search').dropdown('clear');
                            //$('#project_menu').empty();
                            //menuData();
                            $('.small.modal').modal({
                                transition: 'horizontal flip',
                                closable: false,
                                onApprove: function () {
                                    location.reload();
                                }
                            }).modal('show');
                        } else {
                            $('.image').html(' <i class = " red remove circle icon"></i>');
                            $('.description').html(data.message);
                            $('.small.ui.modal').modal('setting', 'transition', 'horizontal flip').modal('show');
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
            });
            supdate.click(function () {
                $.ajax({
                    type: 'POST',
                    url: '../DataUpdate.do',
                    data: {
                        flag: 'installmentservice',
                        paramName: 'stage' + stage + ',stage' + stage + '_sum',
                        paramValue: day + ',' + price,
                        columnName: 'project_no',
                        columnValue: projectNo
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $('.image').html(' <i class = " green check circle icon"></i>');
                            $('.description').html(data.message);
                            //$('.small.ui.modal').modal('setting', 'transition', 'horizontal flip').modal('show');
                            //$('.ui.table').hide();
                            //$('#supdate').hide();
                            //$('#project_search').dropdown('clear');
                            //$('#project_menu').empty();
                            //menuData();
                            $('.small.modal').modal({
                                transition: 'horizontal flip',
                                closable: false,
                                onApprove: function () {
                                    location.reload();
                                    console.log('ok');
                                }
                            }).modal('show');
                        } else {
                            $('.image').html(' <i class = " red remove circle icon"></i>');
                            $('.description').html(data.message);
                            $('.small.ui.modal').modal('setting', 'transition', 'horizontal flip').modal('show');
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
            });

            var validations = {
                project_no: {
                    identifier: 'project_no',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '請選擇會員或專案編號'
                        }
                    ]
                }
            };
            var projectNo;
            var stage;
            var price;
            $('.form,#button').api({
                action: 'search project',
                method: 'POST',
                serializeForm: true,

                beforeSend: function (settings) {
                    $('.form').form('validate form');
                    settings.data.param = 'project_no';
                    settings.data.flag = 'projectservice';
                    return settings;
                },
                successTest: function (response) {
                    if (response && response.success) {
                        return response.success;
                    }
                    return false;
                },
                onSuccess: function (response) {
                    $('tbody').remove();
                    $('.ui.table').show();
                    var produceData = $.parseJSON(response.message);
                    var $tbody = $("<tbody/>");
                    $.each(produceData, function (index, obj) {
                        if (obj.stage == undefined) {
                            obj.stage = '無分期';
                        } else {
                            obj.stage = obj.stage + ' 期';
                        }
                        projectNo = obj.project_no;
                        var arr = [];
                        arr.push(obj.project_no, obj.member_no, obj.pname, obj.price, obj.stage);
                        var $tr = $("<tr/>");
                        var $td;
                        for (var j = 0; j <= arr.length; j++) {
                            if (j < arr.length) {
                                $td = $("<td/>");
                                $td.text(arr[j]);
                                $tr.append($td);
                            } else {
                                $td = $("<td/>");
                                var checkBox;
                                if (obj.stage != '無分期') {
                                    ajaxCommit.showData('../CommonSearch.do', 'installmentservice', function (data) {
                                        if (data.success) {
                                            price = obj.price;
                                            stage = data.message;
                                            checkBox = $('<div class="ui checkbox">' +
                                                '<input type="checkbox" name="fun"><label>結清第 ' + stage + ' 期</label></div>').checkbox({
                                                    onChecked: function () {
                                                        $('#supdate').show();
                                                    },
                                                    onUnchecked: function () {
                                                        $('#supdate').hide();
                                                    }
                                                }
                                            );
                                        }
                                    }, obj.project_no, false);
                                } else {
                                    checkBox = $('<div class="ui checkbox">' +
                                        '<input type="checkbox" name="fun"><label>專案結清</label></div>').checkbox({
                                            onChecked: function () {
                                                $('#update').show();
                                            },
                                            onUnchecked: function () {
                                                $('#update').hide();
                                            }
                                        }
                                    );
                                }
                                $td.append(checkBox);
                                $tr.append($td);
                            }
                        }
                        $tbody.append($tr);
                    });
                    $('table').append($tbody);
                },
                onFailure: function (response) {


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
                on: 'blur',
                transition: 'slide down'
            });
        });
    });
});