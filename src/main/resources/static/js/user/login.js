$(function () {
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
        alert("账号不能为空!");
        return;
    }
    if (accountPwd == "") {
        alert("密码不能为空!");
        return;
    }
    if (code == "") {
        alert("验证码不能为空!");
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