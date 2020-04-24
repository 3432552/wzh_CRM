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
                html += '<img src="../../../image/home.png" title="wzh" style="width: 30px; height:30px;">'
                html += '<div style="position: relative; top: -40px; left: 50px;" id="remarkBody">'
                html += '<h5>' + b.noteContent + '</h5>'
                html += '<font color="gray">市场活动</font> <font color="gray">-</font> <b>&nbsp;</b>'
                html += '<small style="color: gray;">' + b.editTime + ' &nbsp;由&nbsp;&nbsp;' + b.editBy + '</small>'
                html += '<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">'
                html += '<a class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: red;"></span></a>'
                html += '&nbsp;&nbsp;&nbsp;&nbsp;'
                html += '<a onclick="delActId(' + b.id + ')" class="myHref" href="javascript:void(0);"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: red;"></span></a>'
                html += '</div>'
                html += '</div>'
                html += '</div>'
            })
            $("#remarkDiv").before(html);
        }
    })
    //点击名称进去先把要修改的数据加载出来
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
                    alert("修改成功!");
                } else {
                    alert(r.msg);
                }
            }
        })
    })
})

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
                    alert("删除备注成功!");
                } else {
                    alert(r.msg);
                }
            }
        })
    }
}