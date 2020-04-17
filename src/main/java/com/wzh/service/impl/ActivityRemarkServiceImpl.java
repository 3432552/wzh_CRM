package com.wzh.service.impl;

import com.wzh.dao.ActivityRemarkMapper;
import com.wzh.exception.BusinessException;
import com.wzh.service.ActivityRemarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wzh
 * @ClassName: ActivityRemarkServiceImpl
 * @Description:
 * @Date: 2020/4/17 11:51
 */
@Service
@Slf4j
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public int delByRemarkId(String[] remarkActId) throws BusinessException {
        int remarkFkId = activityRemarkMapper.activityRemarkId(remarkActId);
        int delActId = activityRemarkMapper.delActivityRemarkById(remarkActId);
        if (remarkFkId != delActId || delActId < 0) {
            throw new BusinessException("删除失败!");
        } else {
            return delActId;
        }
    }
}
