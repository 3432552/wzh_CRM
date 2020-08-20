package com.wzh.controller;

import com.wzh.common.Result;
import com.wzh.domain.Activity;
import com.wzh.domain.ActivityRemark;
import com.wzh.domain.User;
import com.wzh.exception.BusinessException;
import com.wzh.service.ActivityRemarkService;
import com.wzh.service.ActivityService;
import com.wzh.util.Page;
import com.wzh.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wzh
 * @ClassName: ActivityController
 * @Description:
 * @Date: 2020/4/4 23:10
 */
@Controller
@Slf4j
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityRemarkService activityRemarkService;

    //添加市场活动信息
    @PostMapping("/addAct")
    @ResponseBody
    public Result addAct(Activity activity, HttpServletRequest request) throws BusinessException {
        log.info(activity.toString());
        User u = (User) request.getSession().getAttribute("user");
        activity.setCreateBy(u.getUserName());
        activityService.addActMes(activity);
        return Result.ok();
    }

    //根据市场活动id查找市场活动信息
    @RequestMapping("/selectListByAid")
    @ResponseBody
    public Result selectListById(String aid) {
        log.info("选择要修改的市场活动的信息Id:" + aid);
        List<Activity> actList = activityService.actList(aid);
        return Result.ok(actList);
    }

    @RequestMapping("/actList")
    public String actListCon(Activity activity, Model model, @RequestParam(value = "pageNo", defaultValue = "1",required = false) String pageNo, @RequestParam(value = "pageSize", defaultValue = "5",required = false) String pageSize) {
        log.info("接收前端传来的Activity数据:" + activity.toString() + "pageNo:" + pageNo + "\t" + "pageSize:" + pageSize);
        Page page = new Page();
        //设置当前页码
        page.setCurrent(Integer.valueOf(pageNo));
        //设置每页条数
        page.setLimit(Integer.valueOf(pageSize));
        //略过的条数,用于传给limit的第一个参数
        int pageStarIndex = page.getOffset();
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", pageStarIndex);
        map.put("pageSize", Integer.valueOf(pageSize));
        map.put("name", activity.getName());
        map.put("owner", activity.getOwner());
        map.put("type", activity.getType());
        map.put("state", activity.getState());
        map.put("startTime", activity.getStartDate());
        map.put("endTime", activity.getEndDate());
        PageVo pageVom = activityService.getPageMesVo(map, page.getCurrent(), page.getLimit());
        model.addAttribute("actData", pageVom);
        return "workbench/activity/index";
    }

    //修改市场活动信息
    @PostMapping("/updateAct")
    @ResponseBody
    public Result updateActMes(Activity activity, HttpServletRequest request) throws BusinessException {
        log.info("wzh修改后的市场活动信息:" + activity);
        User u = (User) request.getSession().getAttribute("user");
        activity.setEditBy(u.getUserName());
        log.info("修改备注的时间:" + new Date());
        activity.setEditTime(new Date());
        activityService.updateActInfo(activity);
        return Result.ok();
    }

    //批量删除市场活动
    @PostMapping("/delAct")
    @ResponseBody
    public Result delActMes(HttpServletRequest request) {
        String[] delIds = request.getParameterValues("id");
        activityRemarkService.delByRemarkId(delIds);
        activityService.delActById(delIds);
        return Result.ok("删除成功");
    }

    //点击市场活动列表名称进入修改界面:
    @RequestMapping("/editActindex")
    @ResponseBody
    public ModelAndView activity2(String id) {
        log.info("点击市场活动列表名称进入修改界面:" + id);
        List<Activity> editList = activityService.actList(id);
        System.out.println("看我修改后的值【】:" + editList.toString());
        ModelAndView mv = new ModelAndView();
        mv.addObject("editList", editList);
        mv.setViewName("workbench/activity/detail");
        return mv;
    }

    //备注列表
    @RequestMapping("/actRemarkList")
    @ResponseBody
    public Result actRemarkCon(String actId) {
        log.info("备注列表根据外键:" + actId);
        List<ActivityRemark> activityRemarkList = activityRemarkService.actRList(actId);
        return Result.ok(activityRemarkList);
    }

    //查询备注列表
    @RequestMapping("/selRemarkList")
    @ResponseBody
    public Result selRemarkByIdCon(String remarkId) {
        log.info("备注列表根据Id:" + remarkId);
        ActivityRemark a = activityRemarkService.getRemarkListById(remarkId);
        return Result.ok(a);
    }

    //修改备注
    @RequestMapping("/updateRemark")
    @ResponseBody
    public Result updateRemarkByIdCon(ActivityRemark activityRemark, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        User u = (User) request.getSession().getAttribute("user");
        activityRemark.setEditBy(u.getUserName());
        activityRemark.setEditTime(new Date());
        map.put("id", activityRemark.getId());
        map.put("remark", activityRemark.getNoteContent());
        map.put("editTime", activityRemark.getEditTime());
        map.put("editBy", activityRemark.getEditBy());
        activityRemarkService.updateRemarkSer(activityRemark);
        return Result.ok(map);
    }

    //新增备注
    @PostMapping("/addRemark")
    @ResponseBody
    public Result addRemarkCon(ActivityRemark activityRemark, HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        activityRemark.setCreateBy(u.getCreateBy());
        activityRemarkService.addRemark(activityRemark);
        return Result.ok(activityRemark);
    }

    //删除备注
    @RequestMapping("/delRemark")
    @ResponseBody
    public Result actRemarkDelById(String remarkId) {
        activityRemarkService.delActRdById(remarkId);
        return Result.ok();
    }
}
