package com.wzh.controller;

import com.wzh.common.PaginationVo;
import com.wzh.common.Result;
import com.wzh.domain.Activity;
import com.wzh.domain.User;
import com.wzh.exception.BusinessException;
import com.wzh.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wzh
 * @ClassName: ActivityController
 * @Description:
 * @Date: 2020/4/4 23:10
 */
@RestController
@Slf4j
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping("/addAct")
    public Result addAct(Activity activity, HttpServletRequest request) throws BusinessException {
        log.info(activity.toString());
        User u = (User) request.getSession().getAttribute("user");
        activity.setCreateBy(u.getUserName());
        activityService.addActMes(activity);
        return Result.ok();
    }

    @RequestMapping("/selectListByAid")
    public Result selectListById(String aid) {
        log.info("选择要修改的市场活动的信息Id:" + aid);
        List<Activity> actList = activityService.actList(aid);
        return Result.ok(actList);
    }

    @RequestMapping("/actList")
    public Result actListCon(Activity activity, String pageNo, String pageSize) {
        log.info(activity.toString());
        //pageNO为第几页，这里计算下略过记录数 （limit 0,3 ====第一页展示三条数据）
        int pageNo1 = Integer.valueOf(pageNo);
        int pageSize1 = Integer.valueOf(pageSize);
        //略过的记录数
        int skipPageNum = (pageNo1 - 1) * pageSize1;
        Map<String, Object> map = new HashMap<>();
        map.put("skipPageNum", skipPageNum);
        map.put("pageSize", pageSize1);
        map.put("name", activity.getName());
        map.put("owner", activity.getOwner());
        map.put("type", activity.getType());
        map.put("state", activity.getState());
        map.put("startTime", activity.getStartDate());
        map.put("endTime", activity.getEndDate());
        System.out.println("------------------------->" + activity.getEndDate());
        PaginationVo<Activity> activityPaginationVo = activityService.actPage(map);
        log.info(activityPaginationVo.toString());
        return Result.ok(activityPaginationVo);
    }

    @PostMapping("/updateAct")
    public Result updateActMes(Activity activity,HttpServletRequest request) throws BusinessException {
        User u=(User) request.getSession().getAttribute("user");
        activity.setEditBy(u.getUserName());
        activityService.updateActInfo(activity);
        return Result.ok();
    }
}
