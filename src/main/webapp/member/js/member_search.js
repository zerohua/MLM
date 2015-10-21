/**
 * Created by zero on 15/5/10.
 */
$(document).ready(function () {

    var userNo = sessionStorage.getItem('memberNo');
    showData('../DataList.do','memberservice,memberextservice,member_no,' + userNo,function(data){
        if(data.success) {
            var produceData = $.parseJSON(data.message);
            $.each(produceData,function(index,obj){
                $('input').attr('readonly', true);
                $('.checkbox').checkbox('destroy');
                $('input[name=status][value=' + obj.status + ']').attr('checked', true);
                $('#slide_select').dropdown('set selected', obj.slide);
                $('#create_name').val(obj.create_name);
                $('#create_date').val(obj.create_date);
                $('#recommend').val(getNameById(obj.recommend));
                $('#upper').val(getNameById(obj.upper));
                $('#selection').dropdown('set selected',getPosition(obj.upper,obj._id));
                $('#member_no').val(obj.member_no);
                $('#member_pwd').val('********');
                $('#name').val(obj.name);
                $('#id').val(obj.id);
                $('#birthday').val(obj.birthday);
                $('#sex_select').dropdown('set selected', obj.sex);
                $('#mobile1').val(obj.mobile1);
                $('#mobile2').val(obj.mobile2);
                $('#tel').val(obj.tel);
                $('#email').val(obj.email);
                $('#bank_account').val(obj.bank_account);
                $('#bank_code_field').hide();
                $('#city_field').hide();
                $('#area_field').hide();
                $('#address').val(obj.address);
                $('.dropdown').dropdown('destroy');
            })
        }

    });
});

function getNameById(upperId) {
    var result;
    $.ajax({
        type: 'POST',
        url: '../CommonSearch.do',
        data: {
            flag: 'memberservice',
            upperId: upperId
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

function getPosition(upperId,memberId) {
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