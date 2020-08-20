package com.wzh.controller;

import com.wzh.common.Result;
import javafx.application.Application;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wzh
 * @ClassName: TestController
 * @Description:
 * @Date: 2020/4/6 18:43
 */
@Controller
public class TestController {
    @RequestMapping("/map")
    public String test() {
        System.out.println("=======");
        return "test";
    }
}
