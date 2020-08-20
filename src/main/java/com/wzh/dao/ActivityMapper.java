package com.wzh.dao;

import com.wzh.domain.Activity;
import com.wzh.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @Author: wzh
 * @ClassName: ActivityMapper
 * @Description:
 * @Date: 2020/4/4 17:04
 */
public interface ActivityMapper {
    //多参数入参可以用map,市场活动列表条件查询带分页
    List<Activity> activityListByCondition(Map<String, Object> actListByCondition);

    //查询市场活动列表多少条数据
    int totalNum(Map<String, Object> selCount);

    //修改先查出来信息
    List<Activity> updateSelActivityById(String aid);

    int updateActDao(Activity activity);

    int addAct(Activity activity);

    int delActById(String[] ids);
}
