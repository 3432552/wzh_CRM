package com.wzh.service;

import com.wzh.domain.Activity;
import com.wzh.exception.BusinessException;
import com.wzh.vo.PageVo;

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

    //分页的多种数据信息封装在了一起
    PageVo getPageMesVo(Map<String, Object> pageVo, int current, int pageSize);

    int updateActInfo(Activity activity);

    List<Activity> actList(String aid);

    int delActById(String[] aid) throws BusinessException;
}
