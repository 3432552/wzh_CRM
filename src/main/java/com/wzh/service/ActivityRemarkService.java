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
    int delByRemarkId(String[] remarkActId) throws BusinessException;
    int delActRdById(String id) throws BusinessException;
}
