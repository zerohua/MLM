/**
 * Created by zero on 15/6/21.
 */
define(function(){
    function showData(url, service, callback,param,boolean) {
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                flag: service,
                param: param
            },
            async: boolean,
            dataType: 'json',
            success: function (data) {
                callback(data);
            },
            error: function (jqXHR) {
                if(jqXHR.status == '911') {
                    sessionStorage.clear();
                    alert('閒置時間過久，請重新登入..');
                    window.location.href = '../login.html'
                } else {
                    alert("發生錯誤：" + jqXHR.status);
                }
            }
        })
    }

    function commit(action, flag, validation, button, showResult,md5) {
        $('.form' + ',' + button).api({
            action: action,
            method: 'POST',
            serializeForm: true,
            beforeSend: function (settings) {
                $('.form').form('validate form');
                settings.data.flag = flag;
                if (flag == 'managerservice') {
                    settings.data.manager_pwd = md5($.trim($('#manager_pwd').val()));
                }
                return settings;
            },
            successTest: function (response) {
                if (response && response.success) {
                    return response.success;
                }
                return false;
            },
            onSuccess: function (response) {

                $('.image').html(' <i class = "green check circle icon"></i>');
                $('.description').html('<div class="ui header"> '+ response.message + '</div>');
                $('.small.ui.modal').modal('setting', 'transition', 'horizontal flip').modal('show');
                $('form').form('reset');
                $('tbody').remove();
                showResult();
            },
            onFailure: function (response) {

                $('.image').html(' <i class = " red remove circle icon"></i>');
                $('.description').html('<div class="ui header"> '+ response.message + '</div>');
                $('.small.ui.modal').modal('setting', 'transition', 'horizontal flip').modal('show');

            },
            onError: function (jqXHR) {
                if(jqXHR.status == '911') {
                    sessionStorage.clear();
                    alert('閒置時間過久，請重新登入..');
                    window.location.href = '../login.html'
                } else {
                    alert("發生錯誤：" + jqXHR.status);
                }
            }

        }).form({
            keyboardShortcuts: false,
            fields: validation,
            inline: true,
            on: 'change',
            revalidate: true,
            transition: 'slide down'
        });
    }

    return {
        showData : showData,
        commit : commit
    }
});

