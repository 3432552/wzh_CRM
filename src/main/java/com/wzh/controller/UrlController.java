package com.wzh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: wzh
 * @ClassName: UrlController
 * @Description: 用来跳转
 * @Date: 2020/4/3 22:46
 */
@Controller
public class UrlController {
    //市场活动
    @RequestMapping("/actindex")
    public String activity1() {
        return "workbench/activity/index";
    }

    //线索
    @RequestMapping("/clueindex")
    public String clue() {
        return "clue/index";
    }

    //注销session,返回登录界面
    @RequestMapping("/loginOut")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "user/login";
    }

    //显示工作台界面
    @RequestMapping("/workBench")
    public String activityWorkBench() {
        return "workbench/main/index";
    }
}
