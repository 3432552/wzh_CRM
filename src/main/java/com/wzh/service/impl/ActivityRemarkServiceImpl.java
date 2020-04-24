package com.wzh.service.impl;

import com.wzh.dao.ActivityRemarkMapper;
import com.wzh.domain.ActivityRemark;
import com.wzh.exception.BusinessException;
import com.wzh.service.ActivityRemarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ActivityRemark> actRList(String aid) {
        return activityRemarkMapper.actRemarkList(aid);
    }

    @Override
    public int delByRemarkId(String[] remarkActId) throws BusinessException {
        int remarkFkId = activityRemarkMapper.activityRemarkId(remarkActId);
        int delActId = activityRemarkMapper.delActivityRemarkById(remarkActId);
        if (remarkFkId != delActId || delActId < 0) {
            throw new BusinessException("删除失败");
        } else {
            return delActId;
        }
    }

    @Override
    public int delActRdById(String id) throws BusinessException {
        int delById=activityRemarkMapper.delActRemarkById(id);
        if (delById>0){
            return delById;
        }else{
            throw new BusinessException("删除备注失败");
        }
    }
}
