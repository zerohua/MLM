/**
 * Created by zero on 15/6/21.
 */
var user = sessionStorage.getItem('user');
if (user == null && window.location.href.indexOf('login.html') == -1) {
    window.location.href = '../login.html';
}

$('#user_dropdown')
    .dropdown('set text', '歡迎，' + user).dropdown({
        action: 'hide',
        on: 'hover',
        onChange: function (text,value) {
            if (text.indexOf('註銷登入') != -1) {
                sessionStorage.clear();
            }

        }


    });
