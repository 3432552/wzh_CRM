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
    int totalNum(Map<String, Object> totalMap);

    //多参数入参可以用map
    List<Activity> activityListByCondition(Map<String, Object> actDaoList);

    //修改先查出来信息
    List<Activity> updateSelActivityById(String aid);

    int updateActDao(Activity activity);

    int addAct(Activity activity) throws BusinessException;

}
