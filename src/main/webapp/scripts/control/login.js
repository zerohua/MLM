require(['../config/config'], function () {

    require(['jquery', 'md5', 'semantic', 'serializeObject'], function ($, MD5) {
        sessionStorage.clear();
        var errorMessage = $('.ui.icon.error.message');

        $("input").focus(function () {
            errorMessage.hide();
        });

        var validations = {
                manager_name: {
                    identifier: 'manager_name',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '登入帳號'
                        }
                    ]
                },
                manager_pwd: {
                    identifier: 'manager_pwd',
                    rules: [
                        {
                            type: 'empty',
                            prompt: '登入密碼'
                        }
                    ]
                }
        };

        $.fn.form.settings.rules.keyDown = function (value) {
            if (value != '') {
                errorMessage.addClass('hidden');
            }
            return true;
        };

        $('.form').api({
            url: 'Login.do',
            method: 'POST',
            serializeForm: true,
            beforeSend: function (settings) {
                $('.form').form('validate form');
                settings.data.flag = 'managerservice,memberservice';
                settings.data.manager_pwd = MD5($.trim($('#manager_pwd').val()));
                return settings;
            },
            successTest: function (response) {
                if (response && response.success) {
                    return response.success;
                }
                return false;
            },
            onSuccess: function (response) {
                if (response.who == 'manager') {
                    window.location.href = "admin/index.html";
                    sessionStorage.setItem('user', response.user);
                    sessionStorage.setItem('who', response.who);
                    sessionStorage.setItem('level', response.level);
                } else {
                    window.location.href = "member/index.html";
                    sessionStorage.setItem('user', response.user);
                    sessionStorage.setItem('who', response.who);
                    sessionStorage.setItem('memberNo', response.memberNo);
                }
            },
            onFailure: function (response) {
                errorMessage.show();
                $('body').transition({
                    animation: 'shake',
                    reverse: true
                    //interval: 200
                });

            },
            onError: function () {
                // invalid response
            }

        }).form({
            //keyboardShortcuts: false,
            fields : validations,
            inline: true,
            on: 'blur',
            transition: 'slide down'
        });
    });
});