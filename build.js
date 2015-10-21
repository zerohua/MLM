({
    //appDir:'src/main/webapp',
    baseUrl: 'src/main/webapp/scripts/control',
    mainConfigFile: 'config.js',
    dir:'src/main/webapp/script',
    paths: {
        //requireLib: '../../components/requirejs/require'
        requireLib: '../../components/almond/almond'
    },
    modules: [
        {
            name: 'index',
            include: 'requireLib'
        },
        {
            name: 'login',
            include: 'requireLib'
        },
        {
            name: 'bonus_search',
            include: 'requireLib'
        },
        {
            name: 'business_set',
            include: 'requireLib'
        },
        {
            name: 'company_set',
            include: 'requireLib'
        },
        {
            name: 'member_account',
            include: 'requireLib'
        },
        {
            name: 'member_search',
            include: 'requireLib'
        },
        {
            name: 'org_search',
            include: 'requireLib'
        },
        {
            name: 'project_pay',
            include: 'requireLib'
        },
        {
            name: 'project_search',
            include: 'requireLib'
        },
        {
            name: 'system_account',
            include: 'requireLib'
        },
        {
            name: 'system_module',
            include: 'requireLib'
        }
    ],
    removeCombined: true
})