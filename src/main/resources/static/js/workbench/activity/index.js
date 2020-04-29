$(function () {
    //首页的界面
    //导航中所有文本颜色为黑色
    $(".liClass > a").css("color", "black");

    //默认选中导航菜单中的第一个菜单项
    $(".liClass:first").addClass("active");

    //第一个菜单项的文字变成白色
    $(".liClass:first > a").css("color", "white");

    //给所有的菜单项注册鼠标单击事件
    $(".liClass").click(function () {
        //移除所有菜单项的激活状态
        $(".liClass").removeClass("active");
        //导航中所有文本颜色为黑色
        $(".liClass > a").css("color", "black");
        //当前项目被选中
        $(this).addClass("active");
        //当前项目颜色变成白色
        $(this).children("a").css("color", "white");
    });
    $(".mydate").datetimepicker({
        minView: "month",//设置只显示月份
        language: 'zh-CN',//语言
        format: 'yyyy-mm-dd',//显示格式
        autoclose: true,//选中自动关闭
        todayBtn: true,//显示今日按钮
        pickerPosition: "bottom-left"
    })
    $("#add-mod").click(function () {
        $.ajax({
            url: "/crm/userList",
            type: "get",
            dataType: "json",
            success: function (r) {
                var html = "<option></option>";
                $.each(r.data, function (i, u) {
                    html += "<option value='" + u.id + "'>" + u.userName + "</option>";
                })
                $("#create-marketActivityOwner").html(html);
                //将当前登陆的用户设为下拉框的选项,这个id是是从 session中取的
                $("#create-marketActivityOwner").val(uid);
            }
        })
        $("#createActivityModal").modal("show");
    })
    //新增提交
    $("#saveMarketActivity").click(function () {
        $.ajax({
            url: "/crm/addAct",
            type: "post",
            dataType: "json",
            data: {
                "owner": $("#create-marketActivityOwner").val(),
                "type": $("#create-marketActivityType").val(),
                "name": $.trim($("#create-marketActivityName").val()),
                "state": $("#create-marketActivityState").val(),
                "startDate": $("#create-startTime").val(),
                "endDate": $("#create-endTime").val(),
                "description": $.trim($("#create-describe").val()),
                "budgetcost": $.trim($("#create-budgetCost").val()),
                "actualcost": $.trim($("#create-actualCost").val())
            },
            success: function (r) {
                if (r.code == 200) {
                    actListMap(1, 6);
                    alert("新增成功");
                    $("#marketform")[0].reset();
                    $("#createActivityModal").modal("hide");
                } else {
                    alert(r.msg);
                }
            }
        })
    })
    //全选
    $("#topActId").click(function () {
        $("input[name=actId]").prop("checked", this.checked);
    })
    //点击修改提交
    $("#edit-update").click(function () {
        var $actId = $("input[name=actId]:checked");
        var aid = $actId.val();
        $.ajax({
            url: "/crm/updateAct",
            type: "post",
            dataType: "json",
            data: {
                "id": aid,
                "name": $.trim($("#edit-marketActivityName").val()),
                "startDate": $("#edit-startTime").val(),
                "endDate": $("#edit-endTime").val(),
                "description": $.trim($("#edit-describe").val()),
                "budgetcost": $.trim($("#edit-budgetCost").val()),
                "actualcost": $.trim($("#edit-actualCost").val())
            },
            success: function (r) {
                if (r.code == 200) {
                    actListMap(1, 6);
                    alert("修改成功!");
                } else {
                    alert(r.msg);
                }
            }
        })
    })
    //修改按钮点击事件
    $("#edit-mod").click(function () {
        var $actId = $("input[name=actId]:checked");
        if ($actId.length == 0) {
            alert("请至少要选择一条市场活动信息修改!");
            return;
        } else if ($actId.length > 1) {
            alert("只能选择一条市场活动信息修改!");
            return;
        }
        $("#editActivityModal").modal("show");
        var aid = $actId.val();
        $.ajax({
            url: "/crm/selectListByAid",
            type: "get",
            dataType: "json",
            data: {
                "aid": aid
            },
            success: function (r) {
                if (r.code = 200) {
                    var html1 = "";
                    var html2 = "";
                    var html3 = "";
                    $.each(r.data, function (u, a) {
                        html1 += "<option>" + a.owner + "</option>";
                        $("#edit-marketActivityOwner").html(html1);
                        html2 += "<option>" + a.type + "</option>";
                        $("#edit-marketActivityType").html(html2);
                        html3 += "<option>" + a.state + "</option>";
                        $("#edit-marketActivityState").html(html3);
                        $("#edit-marketActivityName").val(a.name);
                        $("#edit-startTime").val(a.startDate);
                        $("#edit-endTime").val(a.endDate);
                        $("#edit-actualCost").val(a.actualcost);
                        $("#edit-budgetCost").val(a.budgetcost);
                        $("#edit-describe").val(a.description);
                    })
                }
            }
        })
    })
    $("#search").click(function () {
        actListMap(1, 6);
    })
    //动态元素绑定复选框方式
    $("#activityList").on("click", $("input[name=actId]"), function () {
        $("#topActId").prop("checked", $("input[name=actId]").length == $("input[name=actId]:checked").length)
    })

    $("#delButton").click(function () {
        var $actId = $("input[name=actId]:checked");
        if ($actId.length == 0) {
            alert("请至少要选择一条市场活动信息删除!");
        } else {
            //拼接参数
            var params = "";
            for (var i = 0; i < $actId.length; i++) {
                params += "id=" + $($actId[i]).val();
                if (i < $actId.length - 1) {
                    params += "&";
                }
            }
            if (confirm("确定删除吗?")) {
                $.ajax({
                    url: "/crm/delAct",
                    type: "post",
                    dataType: "json",
                    data: params,
                    success: function (r) {
                        if (r.code == 200) {
                            actListMap(1, 6);
                            alert(r.msg);
                        } else {
                            alert(r.msg);
                        }
                    }
                })
            }
        }
    })

})

//更新密码
function updatePwd() {
    var oldPwd = $.trim($("#oldPwd").val());
    var newPwd = $.trim($("#newPwd").val());
    var confirmPwd = $.trim($("#confirmPwd").val());
    if (oldPwd == null || oldPwd == "") {
        alert("请输入旧密码!");
        return;
    }
    if (newPwd == null || newPwd == "") {
        alert("请输入新密码!");
        return;
    }
    if (confirmPwd == null || confirmPwd == "") {
        alert("请在输入一遍新密码!");
        return;
    }
    if (newPwd != confirmPwd) {
        alert("新密码输入前后不一致，请重新输入!");
        return;
    }
    $.ajax({
        url: "/crm/updatePassword",
        type: "post",
        dataType: "json",
        data: {
            "oldPwd": $.trim($("#oldPwd").val()),
            "newPwd": $.trim($("#newPwd").val())
        },
        success: function (r) {
            if (r.code == 200) {
                $("#oldPwd").val("");
                $("#newPwd").val("");
                $("#confirmPwd").val("");
                alert("修改密码成功!");
                //跳转到登录页面,主要清除session信息
                window.location.href = "/crm/loginOut";
            } else {
                alert(r.msg);
                $("#oldPwd").val("");
                $("#newPwd").val("");
                $("#confirmPwd").val("");
            }
        }
    })
}

function actListMap(pageNo, pageSize) {
    $.ajax({
        url: "/crm/actList",
        type: "get",
        dataType: "json",
        data: {
            "owner": $.trim($("#iowner").val()),
            "type": $("#itype").val(),
            "name": $.trim($("#iname").val()),
            "state": $("#istate").val(),
            "startDate": $("#startTime").val(),
            "endDate": $("#endTime").val(),
            "pageNo": pageNo,
            "pageSize": pageSize
        },
        success: function (r) {
            var html = "";
            $.each(r.data.actList, function (i, a) {
                html += '<tr class="active">';
                html += '<td><input type="checkbox" value="' + a.id + '"name="actId"/></td>';
                html += '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'/crm/editActindex?id=' + a.id + '\';">' + a.name + '</a></td>';
                html += '<td>' + a.type + '</td>';
                html += '<td>' + a.state + '</td>';
                html += '<td>' + a.startDate + '</td>';
                html += '<td>' + a.endDate + '</td>';
                html += '<td>' + a.owner + '</td>';
                html += '<td>' + a.budgetcost + '</td>';
                html += '<td>' + a.actualcost + '</td>';
                html += '<td>' + a.createBy + '</td>';
                html += '<td>' + a.createTime + '</td>';
                html += '<td>' + a.editBy + '</td>';
                html += '<td>' + a.editTime + '</td>';
                html += '<td>' + a.description + '</td>';
                html += '</tr>';
            })
            $("#activityList").html(html);
        }
    })
}