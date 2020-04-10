package com.wzh.controller;
import com.wzh.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
       return "Test";
    }
}
