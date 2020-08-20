$(function () {
    //toastr 消息弹出框 初始化出现位置上中
    //参数设置，若用默认值可以省略以下面代
    $("#loginBy").click(function () {
        login();
    })
    //当验证码获得焦点时,即可执行敲击Enter执行登陆
    //event:取得我们敲键盘是那个键，回车键对应的数字是 13
    if ($("#account").focus()) {
        $(window).keydown(function (event) {
            if (event.keyCode == 13) {
                login();
            }
        })
    }
})

function login() {
    var account = $.trim($("#account").val());
    var accountPwd = $.trim($("#accountPwd").val());
    var code = $.trim($("#code").val());
    if (account == "") {
        toastr.warning('账号不能为空!');
        return;
    }
    if (accountPwd == "") {
        toastr.warning("密码不能为空!");
        return;
    }
    if (code == "") {
        toastr.warning("验证码不能为空!");
        return;
    }
    $.ajax({
        type: "post",
        url: "/crm/login",
        data: {
            "loginAct": account,
            "loginPwd": accountPwd,
            "code": code
        },
        dataType: "json",
        success: function (r) {
            if (r.code == 200) {
                window.location.href = "/crm/home";
            } else {
                refresh();
                $("#mes").html(r.msg);
            }
        }
    });
}

function refresh() {
    $("#gitCodeImg").attr("src", "gifCode?date=" + new Date())
}