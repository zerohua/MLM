/**
 * Created by zero on 15/6/24.
 */
require(['../config/config'], function () {

    require(['jquery', 'ajaxCommit', 'serializeObject', 'semantic', 'header', 'jqueryAddress',
        'dateExt', 'common', 'domReady!'
    ], function ($, ajaxCommit) {

        $('.menu .item').tab();

        var show = {
            bonus: function () {
                ajaxCommit.showData('../DataList.do', 'bonusservice', function (data) {
                    if (data.success) {
                        if (data.message != '[]') {
                            $('#ebonus_btn').show();
                            $('#nbonus_btn').hide();
                            $('.bonus_input').attr('readonly', true);
                            var produceData = $.parseJSON(data.message);
                            $.each(produceData, function (index, obj) {
                                $('#recommend_bonus').val(obj.recommend_bonus);
                                $('#bump_bonus').val(obj.bump_bonus);
                                $('#equal_bonus').val(obj.equal_bonus);
                                $('#director_pv').val(obj.director_pv);
                                $('#director_bouns').val(obj.director_bonus);
                                $('#up_director_bonus').val(obj.up_director_bonus);
                                $('#cdo_pv').val(obj.cdo_pv);
                                $('#cdo_bouns').val(obj.cdo_bonus);
                                $('#up_cdo_bonus').val(obj.up_cdo_bonus);
                                $('#minister_pv').val(obj.minister_pv);
                                $('#minister_bonus').val(obj.minister_bonus);
                                $('#up_minister_bonus').val(obj.up_minister_bonus);
                                $('#coo_pv').val(obj.coo_pv);
                                $('#coo_bonus').val(obj.coo_bonus);
                                $('#up_coo_bonus').val(obj.up_coo_bonus);
                            });

                        } else {
                            ajaxCommit.commit('create bonus', 'bonusservice', bonus_validations, '#nbonus_btn', show.bonus);
                        }
                    }
                });
            },
            bump: function () {
                ajaxCommit.showData('../DataList.do', 'bumplimitservice', function (data) {
                    if (data.success) {
                        if (data.message != '[]') {
                            $('#nbump_btn').hide();
                            $('#ebump_btn').show();
                            $('.bump_input').attr('readonly', true);
                            var bumpData = $.parseJSON(data.message);
                            $.each(bumpData, function (index, obj) {
                                $('#week_limit').val(obj.week_limit);
                                $('#month_limit').val(obj.month_limit);
                            })
                        } else {
                            ajaxCommit.commit('create bump', 'bumplimitservice', bump_validations, '#nbump_btn', show.bump);
                        }
                    }
                })
            },
            upgrade: function () {
                ajaxCommit.showData('../DataList.do', 'upgradeservice', function (data) {
                    if (data.success) {
                        if (data.message != '[]') {
                            $('#nupgrade_btn').hide();
                            $('#eupgrade_btn').show();
                            $('.upgrade_input').attr('readonly', true);
                            var upgradeData = $.parseJSON(data.message);
                            $.each(upgradeData, function (index, obj) {
                                $('#director_slide_mini').val(obj.director_slide_mini);
                                $('#director_slide_total').val(obj.director_slide_total);
                                $('#director_mini').val(obj.director_mini);
                                $('#director_total').val(obj.director_total);
                                $('#cdo_rside_director').val(obj.cdo_rside_director);
                                $('#cdo_lside_director').val(obj.cdo_lside_director);
                                $('#minister_rside_cdo').val(obj.minister_rside_cdo);
                                $('#minister_lside_cdo').val(obj.minister_lside_cdo);
                                $('#coo_rside_minister').val(obj.coo_rside_minister);
                                $('#coo_lside_minister').val(obj.coo_lside_minister);
                            })
                        } else {
                            ajaxCommit.commit('create upgrade', 'upgradeservice', upgrade_validations, '#nupgrade_btn', show.upgrade);
                        }
                    }
                })
            }
        };

        var bonus_validations = {
            recommend_bonus: {
                identifier: 'recommend_bonus',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            bump_bonus: {
                identifier: 'bump_bonus',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            equal_bonus: {
                identifier: 'equal_bonus',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            director_pv: {
                identifier: 'director_pv',
                rules: [
                    {
                        type: 'integer',
                        prompt: 'PV必須為整數數字'
                    }
                ]
            },
            director_bouns: {
                identifier: 'director_bouns',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            up_director_bonus: {
                identifier: 'up_director_bonus',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            cdo_pv: {
                identifier: 'cdo_pv',
                rules: [
                    {
                        type: 'integer',
                        prompt: 'PV必須為整數數字'
                    }
                ]
            },
            cdo_bouns: {
                identifier: 'cdo_bouns',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            up_cdo_bonus: {
                identifier: 'up_cdo_bonus',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            minister_pv: {
                identifier: 'minister_pv',
                rules: [
                    {
                        type: 'integer',
                        prompt: 'PV必須為整數數字'
                    }
                ]
            },
            minister_bonus: {
                identifier: 'minister_bonus',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            up_minister_bonus: {
                identifier: 'up_minister_bonus',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            coo_pv: {
                identifier: 'coo_pv',
                rules: [
                    {
                        type: 'integer',
                        prompt: 'PV必須為整數數字'
                    }
                ]
            },
            coo_bonus: {
                identifier: 'coo_bonus',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            },
            up_coo_bonus: {
                identifier: 'up_coo_bonus',
                rules: [
                    {
                        type: 'integer',
                        prompt: '金額必須為整數數字'
                    }
                ]
            }
        };

        var bump_validations = {
            week_limit: {
                identifier: 'week_limit',
                rules: [
                    {
                        type: 'integer',
                        prompt: 'PV必須為整數數字'
                    }
                ]
            },
            month_limit: {
                identifier: 'month_limit',
                rules: [
                    {
                        type: 'integer',
                        prompt: 'PV必須為整數數字'
                    }
                ]
            }

        };

        var upgrade_validations = {
            director_slide_mini: {
                identifier: 'director_slide_mini',
                rules: [
                    {
                        type: 'integer',
                        prompt: 'PV必須為整數數字'
                    }
                ]
            },
            director_slide_total: {
                identifier: 'director_slide_total',
                rules: [
                    {
                        type: 'integer',
                        prompt: 'PV必須為整數數字'
                    }
                ]
            },
            director_mini: {
                identifier: 'director_mini',
                rules: [
                    {
                        type: 'integer',
                        prompt: 'PV必須為整數數字'
                    }
                ]
            },
            director_total: {
                identifier: 'director_total',
                rules: [
                    {
                        type: 'integer',
                        prompt: 'PV必須為整數數字'
                    }
                ]
            },
            cdo_rside_director: {
                identifier: 'cdo_rside_director',
                rules: [
                    {
                        type: 'integer',
                        prompt: '必須為整數數字'
                    }

                ]
            },
            cdo_lside_director: {
                identifier: 'cdo_lside_director',
                rules: [
                    {
                        type: 'integer',
                        prompt: '必須為整數數字'
                    }
                ]
            },
            minister_rside_cdo: {
                identifier: 'minister_rside_cdo',
                rules: [
                    {
                        type: 'integer',
                        prompt: '必須為整數數字'
                    }
                ]
            },
            minister_lside_cdo: {
                identifier: 'minister_lside_cdo',
                rules: [
                    {
                        type: 'integer',
                        prompt: '必須為整數數字'
                    }
                ]
            },
            coo_rside_minister: {
                identifier: 'coo_rside_minister',
                rules: [
                    {
                        type: 'integer',
                        prompt: '必須為整數數字'
                    }
                ]
            },
            coo_lside_minister: {
                identifier: 'coo_lside_minister',
                rules: [
                    {
                        type: 'integer',
                        prompt: '必須為整數數字'
                    }
                ]
            }
        };
        if ($('.ui.top.attached.tabular.menu .item').attr('data-tab') == 'bonus') {
                show.bonus();
        }
        $('.ui.top.attached.tabular.menu').on('click', '.item', function () {
            switch ($(this).attr('data-tab')) {
                case 'bonus':
                    show.bonus();
                    break;
                case 'bump':
                    show.bump();
                    break;
                case 'upgrade':
                    show.upgrade();
                    break;
            }
        });
    });
});