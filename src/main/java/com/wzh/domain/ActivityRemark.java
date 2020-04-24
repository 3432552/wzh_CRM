package com.wzh.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: wzh
 * @ClassName: ActivityRemark
 * @Description:
 * @Date: 2020/4/4 16:58
 */
@Data
@ToString
public class ActivityRemark {
    private String id;
    //备注信息
    private String noteContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String createBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editTime;
    private String editBy;
    private String editFlag;
    private String activityId;
}
