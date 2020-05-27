$(function () {
    //bootstrap的时间控件
    $(".mydate").datetimepicker({
        minView: "month",//设置只显示月份
        language: 'zh-CN',//语言
        format: 'yyyy-mm-dd',//显示格式
        autoclose: true,//选中自动关闭
        todayBtn: true,//显示今日按钮
        pickerPosition: "bottom-left"
    })
    //动态绑定
    $("#remarkBody").on("mouseover", ".remarkDiv", function () {
        $(this).children("div").children("div").show();
    })
    $("#remarkBody").on("mouseout", ".remarkDiv", function () {
        $(this).children("div").children("div").hide();
    })
    remarkList();
    //点击名称进去点击编辑进行修改时，把要修改的数据加载出来
    $("#e-edit").click(function () {
        $("#editActivityModal").modal("show");
        $.ajax({
            url: "/crm/selectListByAid",
            type: "get",
            dataType: "json",
            data: {
                "aid": $("#sId").val()
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
    //点击名称进去然后点击修改提交
    $("#edit-updateTwo").click(function () {
        $.ajax({
            url: "/crm/updateAct",
            type: "post",
            dataType: "json",
            data: {
                "id": $("#sId").val(),
                "name": $.trim($("#edit-marketActivityName").val()),
                "startDate": $("#edit-startTime").val(),
                "endDate": $("#edit-endTime").val(),
                "description": $.trim($("#edit-describe").val()),
                "budgetcost": $.trim($("#edit-budgetCost").val()),
                "actualcost": $.trim($("#edit-actualCost").val())
            },
            success: function (r) {
                if (r.code == 200) {
                    toastr.success("修改成功!");
                } else {
                    toastr.error(r.msg);
                }
            }
        })
    })
    //新增备注信息
    $("#saveRemark").click(function () {
        if ($.trim($("#remark").val()) == "") {
            toastr.warning("请输入备注信息!");
            return;
        }
        $.ajax({
            url: "/crm/addRemark",
            type: "post",
            dataType: "json",
            data: {
                "noteContent": $.trim($("#remark").val()),
                "activityId": $("#sId").val()
            },
            success: function (r) {
                $("#remark").val("");
                if (r.code == 200) {
                    //alert("市场活动新增备注id:" + r.data.id);
                    //市场活动备注
                    var html = "";
                    html += '<div id="' + r.data.id + '" class="remarkDiv" style="height: 60px;">'
                    html += '<img src="image/user-thumbnail.png" title="wzh" style="width: 30px; height:30px;">'
                    html += '<div style="position: relative; top: -40px; left: 50px;" id="remarkBody">'
                    html += '<h5 id="r' + r.data.id + '">' + r.data.noteContent + '</h5>'
                    html += '<font color="gray">市场活动</font> <font color="gray">-</font> <b>&nbsp;</b>'
                    html += '<small id="m' + r.data.id + '" style="color: gray;">' + r.data.createTime + ' &nbsp;由&nbsp;&nbsp;' + r.data.createBy + '</small>'
                    html += '<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">'
                    html += '<a class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: red;"></span></a>'
                    html += '&nbsp;&nbsp;&nbsp;&nbsp;'
                    html += '<a onclick="delActId(' + r.data.id + ')" class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: red;"></span></a>'
                    html += '</div>'
                    html += '</div>'
                    html += '</div>'
                    $("#remarkDiv").before(html);
                    //目的是让新增的备注在上面显示
                    $.ajax({
                        url: "/crm/actRemarkList",
                        type: "get",
                        dataType: "json",
                        data: {
                            "actId": $("#sId").val()
                        },
                        success: function (r) {
                            //市场活动备注
                            var html = "";
                            $.each(r.data, function (a, b) {
                                html += '<div id="' + b.id + '" class="remarkDiv" style="height: 60px;">'
                                html += '<img src="image/user-thumbnail.png" title="wzh" style="width: 30px; height:30px;">'
                                html += '<div style="position: relative; top: -40px; left: 50px;" id="remarkBody">'
                                html += '<h5 id="r\' + b.id + \'">' + b.noteContent + '</h5>'
                                html += '<font color="gray">市场活动</font> <font color="gray">-</font> <b>&nbsp;</b>'
                                html += '<small id="m\' + r.data.id + \'" style="color: gray;">' + b.editTime + ' &nbsp;由&nbsp;&nbsp;' + b.editBy + '</small>'
                                html += '<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">'
                                html += '<a onclick="editRemark(' + b.id + ')" class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: red;"></span></a>'
                                html += '&nbsp;&nbsp;&nbsp;&nbsp;'
                                html += '<a onclick="delActId(' + b.id + ')" class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: red;"></span></a>'
                                html += '</div>'
                                html += '</div>'
                                html += '</div>'
                                $("#" + b.id).remove();
                            })
                            $("#remarkDiv").before(html);
                        }
                    })
                    toastr.success("新增备注成功!");
                } else {
                    toastr.error(r.msg);
                }
            }
        })
    })
})

function editRemark(id) {
    $("#editRemarkModal").modal("show");
    $.ajax({
        url: "/crm/selRemarkList",
        type: "get",
        dataType: "json",
        data: {
            "remarkId": id
        },
        success: function (r) {
            if (r.code == 200) {
                $("#noteContent").val(r.data.noteContent);
            } else {
                toastr.error(r.msg);
            }
        }
    })
    //修改备注
    $("#updateRemarkBtn").click(function () {
        if ($.trim($("#noteContent").val()) == "") {
            toastr.warning("请修改备注信息!");
            return;
        }
        $.ajax({
            url: "/crm/updateRemark",
            type: "post",
            dataType: "json",
            data: {
                "id": id,
                "noteContent": $("#noteContent").val()
            },
            success: function (r) {
                //alert("市场活动更新备注id:" + r.data.id);
                if (r.code == 200) {
                    $("#r" + r.data.id).html(r.data.remark);
                    $("#m" + r.data.id).html(r.data.editTime + "&nbsp;由&nbsp;&nbsp;" + r.data.editBy);
                    toastr.success("修改备注信息成功!");
                    $("#editRemarkModal").modal("hide");
                } else {
                    toastr.error(r.msg);
                }
            }
        })
    })
}

function remarkList() {
    //备注列表,ajax
    $.ajax({
        url: "/crm/actRemarkList",
        type: "get",
        dataType: "json",
        data: {
            "actId": $("#sId").val()
        },
        success: function (r) {
            //市场活动备注
            var html = "";
            $.each(r.data, function (a, b) {
                html += '<div id="' + b.id + '" class="remarkDiv" style="height: 60px;">'
                html += '<img src="image/user-thumbnail.png" title="wzh" style="width: 30px; height:30px;">'
                html += '<div style="position: relative; top: -40px; left: 50px;" id="remarkBody">'
                html += '<h5 id="r' + b.id + '">' + b.noteContent + '</h5>'
                html += '<font color="gray">市场活动</font> <font color="gray">-</font> <b>&nbsp;</b>'
                html += '<small id="m' + b.id + '" style="color: gray;">' + b.editTime + ' &nbsp;由&nbsp;&nbsp;' + b.editBy + '</small>'
                html += '<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">'
                html += '<a onclick="editRemark(' + b.id + ')" class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: red;"></span></a>'
                html += '&nbsp;&nbsp;&nbsp;&nbsp;'
                html += '<a onclick="delActId(' + b.id + ')" class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: red;"></span></a>'
                html += '</div>'
                html += '</div>'
                html += '</div>'
            })
            $("#remarkDiv").before(html);
        }
    })
}

//删除备注
function delActId(id) {
    if (confirm("确定删除吗?")) {
        $.ajax({
            url: "/crm/delRemark",
            type: "post",
            dataType: "json",
            data: {
                "remarkId": id,
            },
            success: function (r) {
                if (r.code == 200) {
                    $("#" + id).remove();
                    toastr.success("删除备注成功!");
                } else {
                    toastr.error(r.msg);
                }
            }
        })
    }
}