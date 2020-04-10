$(function () {
    $(".mydate").datetimepicker({
        minView: "month",
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        pickerPosition: "bottom-left"
    });

    $("#add-mod").click(function () {
        $.ajax({
            url: "/userList",
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
            url: "/addAct",
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
    //点击修改提交
    $("#edit-update").click(function () {
        /*alert("name："+$.trim($("#create-marketActivityName").val()));*/
        var $actId = $("input[name=actId]:checked");
        var aid = $actId.val();
        $.ajax({
            url: "/updateAct",
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
            url: "/selectListByAid",
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
            alert(params);
        }
    })
    /*$.ajax({
        url: "/actList",
        type: "get",
        dataType: "json",
        data: {
            "aid": $("#SelectUpId").val()
        }
    })*/
})

function actListMap(pageNo, pageSize) {
    $.ajax({
        url: "/actList",
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
                /*html += '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=';
                detail.html;
                ';">a.name</a></td>';*/
                html += '<td>' + a.name + '</td>';
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