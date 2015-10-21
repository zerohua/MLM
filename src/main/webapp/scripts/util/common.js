/**
 * Created by zero on 15/6/21.
 */
$('.dropdown')
    .dropdown();

$('.checkbox')
    .checkbox();



$.fn.api.settings.api = {
    'create member': '../MemberAdd.do',
    'create produce': '../CommonAdd.do',
    'create member_level': '../CommonAdd.do',
    'create work_cycle': '../CommonAdd.do',
    'create manager': '../CommonAdd.do',
    'create pv': '../CommonAdd.do',
    'create consume': '../CommonAdd.do',
    'create amortization': '../CommonAdd.do',
    'create slide': '../CommonAdd.do',
    'create bonus': '../CommonAdd.do',
    'create bump': '../CommonAdd.do',
    'create upgrade': '../CommonAdd.do',
    'create project': '../CommonAdd.do',
    'search project': '../DataList.do',
    'update member' : '../CommonUpdate.do'
};