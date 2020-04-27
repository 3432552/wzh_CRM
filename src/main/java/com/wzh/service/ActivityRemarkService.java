package com.wzh.service;

import com.wzh.domain.ActivityRemark;
import com.wzh.exception.BusinessException;

import java.util.List;

/**
 * @Author: wzh
 * @ClassName: ActivityRemarkService
 * @Description:
 * @Date: 2020/4/17 11:51
 */
public interface ActivityRemarkService {
    List<ActivityRemark> actRList(String aid);
    ActivityRemark getRemarkListById(String id);
    int updateRemarkSer(ActivityRemark activityRemark) throws BusinessException;
    int delByRemarkId(String[] remarkActId) throws BusinessException;
    int delActRdById(String id) throws BusinessException;
    int addRemark(ActivityRemark activityRemark) throws BusinessException;
}
