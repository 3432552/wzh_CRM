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
    @RequestMapping("/actindex")
    public String activity1() {
        return "workbench/activity/index";
    }

    @RequestMapping("/editActindex")
    public String activity2() {
        return "workbench/activity/detail";
    }

    //注销session,返回登录界面
    @RequestMapping("/loginOut")
    public String logout(HttpServletRequest request) {
        HttpSession session = (HttpSession) request.getSession();
        session.invalidate();
        return "user/login";
    }
}
