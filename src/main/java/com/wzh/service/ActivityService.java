package com.wzh.service;

import com.wzh.common.PaginationVo;
import com.wzh.domain.Activity;
import com.wzh.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @Author: wzh
 * @ClassName: ActivityService
 * @Description:
 * @Date: 2020/4/4 23:07
 */
public interface ActivityService {
    int addActMes(Activity activity) throws BusinessException;

    PaginationVo<Activity> actPage(Map<String, Object> pageMap);

    int updateActInfo(Activity activity);

    List<Activity> actList(String aid);
}
