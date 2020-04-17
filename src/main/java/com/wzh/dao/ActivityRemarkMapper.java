package com.wzh.dao;

/**
 * @Author: wzh
 * @ClassName: ActivityRemarkMapper
 * @Description:
 * @Date: 2020/4/17 11:33
 */
public interface ActivityRemarkMapper {
    //查询有多少个市场活动的id
    int activityRemarkId(String[] remarkId);
    //根据外键删除备注
    int delActivityRemarkById(String[] aid);
}
