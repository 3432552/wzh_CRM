package com.wzh.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: wzh
 * @ClassName: Activity
 * @Description: 市场活动表
 * @Date: 2020/4/4 16:41
 */
@Data
@ToString
public class Activity {
    //主键
    private String id;
    //所有者 外键 关联用户 id
    private String owner;
    //市场活动名称
    private String name;
    private String startDate;
    private String endDate;
    //类型
    private String type;
    //状态
    private String state;
    //预算成本
    private String budgetcost;
    //实际成本
    private String actualcost;
    private String description;
    private String createBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String editBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date editTime;
}
