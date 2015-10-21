/**
 * Created by zero on 15/6/25.
 */
requirejs.config({
    paths: {
        "jquery": "../../components/jquery/dist/jquery",
        "jqueryAddress": "../../components/jquery-address/src/jquery.address",
        "semantic": "../../components/semantic-ui/dist/semantic",
        "domReady": "../../components/requirejs-domready/domReady",
        "serializeObject": "../../components/jquery-serializeObject/jquery.serializeObject",
        "line": "../../components/jquery.line/jquery.line",
        "header": "header",
        "md5": "../util/md5",
        "area": "../util/area",
        "dateExt": "../util/dateExt",
        "common": "../util/common",
        "ajaxCommit": "../net/ajaxCommit"
    },
    shim: {

        semantic: {
            deps: ["jquery"]
        },
        serializeObject: {
            deps: ["jquery"]
        },
        md5: {
            exports: 'MD5'
        },
        header: {
            deps: ["semantic"]
        },
        ajaxCommit: {
            dep: ["jquery"],
            exports: 'ajaxCommit'
        },
        common: {
            deps: ["semantic"]
        },
        dateExt: {
            deps: ["jquery"]
        },
        line: {
            deps: ["jquery"]
        },
        jqueryAddress: {
            deps: ["jquery"]
        }

    }
});