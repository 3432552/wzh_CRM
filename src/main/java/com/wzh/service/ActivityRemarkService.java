package com.wzh.service;

import com.wzh.exception.BusinessException;

/**
 * @Author: wzh
 * @ClassName: ActivityRemarkService
 * @Description:
 * @Date: 2020/4/17 11:51
 */
public interface ActivityRemarkService {
    int delByRemarkId(String[] remarkActId) throws BusinessException;
}
