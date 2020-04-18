function login() {
    var account = $("#account").val().trim();
    var accountPwd = $("#accountPwd").val().trim();
    var code = $("#code").val().trim();
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
                location.href = "/crm/home";
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