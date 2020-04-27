package com.wzh.service.impl;

import com.wzh.dao.ActivityRemarkMapper;
import com.wzh.domain.ActivityRemark;
import com.wzh.domain.User;
import com.wzh.exception.BusinessException;
import com.wzh.service.ActivityRemarkService;
import com.wzh.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    public int updateRemarkSer(ActivityRemark activityRemark) throws BusinessException {
        log.info("修改备注的对象:"+activityRemark.toString());
        int upResult=activityRemarkMapper.updateRemark(activityRemark);
        if (upResult>0){
            return upResult;
        }else {
            throw new RuntimeException("修改备注失败");
        }
    }

    @Override
    public ActivityRemark getRemarkListById(String id) throws BusinessException  {
        if (StringUtils.isBlank(id)){
            throw new BusinessException("查询备注无数据");
        }
        return activityRemarkMapper.selectRemarkById(id);
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
        int delById = activityRemarkMapper.delActRemarkById(id);
        if (delById > 0) {
            return delById;
        } else {
            throw new BusinessException("删除备注失败");
        }
    }
    @Override
    @Transactional
    public int addRemark(ActivityRemark activityRemark) throws BusinessException {
        log.info("新增备注ActivityRemark:" + activityRemark.toString());
        activityRemark.setCreateTime(DateUtil.dateTime(new Date()));
        int addRes = activityRemarkMapper.insertRemark(activityRemark);
        if (addRes > 0) {
            return addRes;
        } else {
            throw new BusinessException("新增备注失败");
        }
    }
}
