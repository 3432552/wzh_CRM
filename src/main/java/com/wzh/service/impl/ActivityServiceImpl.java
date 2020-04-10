package com.wzh.service.impl;


import com.wzh.common.PaginationVo;
import com.wzh.dao.ActivityMapper;
import com.wzh.domain.Activity;
import com.wzh.exception.BusinessException;
import com.wzh.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
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
    public PaginationVo<Activity> actPage(Map<String, Object> pageMap) {
        int total = activityMapper.totalNum(pageMap);
        List<Activity> aList = activityMapper.activityListByCondition(pageMap);
        PaginationVo<Activity> paginationVo = new PaginationVo<>();
        paginationVo.setTotal(total);
        paginationVo.setActList(aList);
        return paginationVo;
    }

    @Override
    public List<Activity> actList(String aid) {
        List<Activity> activities = activityMapper.updateSelActivityById(aid);
        return activities;
    }

    @Override
    public int updateActInfo(Activity activity) throws BusinessException {
        int re = activityMapper.updateActDao(activity);
        log.info("更新的对象 activity:" + activity + "\t" + "更新是否成功:" + re);
        if (re > 0) {
            return re;
        } else {
            throw new BusinessException("新增失败");
        }
    }
}
