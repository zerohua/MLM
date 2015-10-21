/**
 * Created by zero on 15/6/23.
 */
require(['../config/config'], function () {

    require(['jquery', 'ajaxCommit', 'md5', 'serializeObject', 'semantic', 'header',
        'common', 'dateExt', 'domReady!'
    ], function ($, ajaxCommit, MD5) {

        var show = {
            manager: function () {
                ajaxCommit.showData('../DataList.do', 'managerservice', function (data) {
                    if (data.success) {
                        var produceData = $.parseJSON(data.message);
                        var $tbody = $("<tbody/>");
                        $.each(produceData, function (index, obj) {
                            var arr = [];
                            arr.push(obj.manager_name, '********', obj.nick_name);
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

        var validations = {
            manager_name: {
                identifier: 'manager_name',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請輸入帳號名稱'
                    }
                ]
            },
            manager_pwd: {
                identifier: 'manager_pwd',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請輸入密碼'
                    }
                ]
            },
            nick_name: {
                identifier: 'nick_name',
                rules: [
                    {
                        type: 'empty',
                        prompt: '請輸入暱稱'
                    }
                ]
            }
        };

        show.manager();
        ajaxCommit.commit('create manager', 'managerservice', validations, '#button', show.manager, MD5);
    });
});