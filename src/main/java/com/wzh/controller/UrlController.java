package com.wzh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
