package com.wzh.service.impl;


import com.wzh.dao.ActivityMapper;
import com.wzh.domain.Activity;
import com.wzh.exception.BusinessException;
import com.wzh.service.ActivityService;
import com.wzh.util.Page;
import com.wzh.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wzh
 * @ClassName: ActivityServiceImpl
 * @Description:
 * @Date: 2020/4/4 23:07
 */
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    @Transactional
    public int addActMes(Activity activity) throws BusinessException {
        log.info("Activity对象:" + activity);
        activity.setCreateTime(new Date());
        int res = activityMapper.addAct(activity);
        if (res > 0) {
            return res;
        } else {
            throw new BusinessException("新增失败");
        }
    }

    @Override
    public List<Activity> actList(String aid) {
        List<Activity> activities = activityMapper.updateSelActivityById(aid);
        return activities;
    }

    @Override
    public PageVo getPageMesVo(Map<String, Object> pageVo, int current, int pageSize) {
        Page page = new Page();
        int total = activityMapper.totalNum(pageVo);
        page.setCurrent(current);
        page.setLimit(pageSize);
        page.setRows(total);
        List<Activity> actList = activityMapper.activityListByCondition(pageVo);
        PageVo vo = new PageVo();
        vo.setCurrentPage(page.getCurrent());
        vo.setTotalPages(page.getTotalPages());
        vo.setTotalRows(total);
        vo.setDataList(actList);
        return vo;
    }

    @Override
    @Transactional
    public int updateActInfo(Activity activity) throws BusinessException {
        int re = activityMapper.updateActDao(activity);
        log.info("更新的对象 activity:" + activity + "\t" + "更新是否成功:" + re);
        if (re > 0) {
            return re;
        } else {
            throw new BusinessException("新增失败");
        }
    }

    @Override
    public int delActById(String[] aid) throws BusinessException {
        int delActId = activityMapper.delActById(aid);
        if (delActId > 0) {
            return delActId;
        } else {
            log.info("删除市场活动Id失败:" + delActId);
            throw new BusinessException("删除失败!");
        }
    }
}
