package com.wzh.dao;

import com.wzh.domain.ActivityRemark;
import com.wzh.exception.BusinessException;

import java.util.List;

/**
 * @Author: wzh
 * @ClassName: ActivityRemarkMapper
 * @Description:
 * @Date: 2020/4/17 11:33
 */
public interface ActivityRemarkMapper {
    //查询市场活动备注列表
    List<ActivityRemark> actRemarkList(String actId);
   //查询市场活动根据备注Id
    ActivityRemark selectRemarkById(String id) throws BusinessException;
    //修改备注根据id
    int updateRemark(ActivityRemark activityRemark);
    //查询有多少个市场活动的id
    int activityRemarkId(String[] remarkId);

    //根据外键删除备注
    int delActivityRemarkById(String[] aid);

    //根据备注id 输出备注
    int delActRemarkById(String id);

    int insertRemark(ActivityRemark activityRemark);
}
