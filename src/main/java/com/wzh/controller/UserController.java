package com.wzh.controller;

import com.wzh.common.Result;
import com.wzh.domain.User;
import com.wzh.exception.LoginException;
import com.wzh.service.UserService;
import com.wzh.util.vcode.Captcha;
import com.wzh.util.vcode.GifCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: wzh
 * @ClassName: UserController
 * @Description:
 * @Date: 2020/3/27 22:35
 */
@Controller
@Slf4j
public class UserController {
    private static final String CODE_KEY = "_code";
    @Autowired
    private UserService userService;

    //首页
    @RequestMapping("/home")
    public String index1() {
        return "index";
    }

    @RequestMapping("/mes")
    public String index3() {
        return "user/login";
    }

    @RequestMapping("/n")
    public String index2() {
        return "test";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public Result userList() {
        List<User> userList = userService.getUserList();
        return Result.ok(userList);
    }

    /**
     * 登陆
     *
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(String loginAct, String loginPwd, String code, HttpSession session, HttpServletRequest request) throws LoginException {
        String userIp = request.getLocalAddr();
        User u = userService.userLogin(loginAct, loginPwd, userIp);
        log.info(u.toString());
        String realCode = (String) request.getSession().getAttribute(CODE_KEY);
        log.info("账号:" + loginAct + "\t" + "密码:" + loginPwd + "\t" + "真实验证码：" + realCode + "\t" + "用户输入的验证码:" + code);
        if (!realCode.toLowerCase().equals(code)) {
            return Result.wan("验证码输入错误");
        }
        session.setAttribute("user", u);
        return Result.ok();
    }

    /**
     * 生成验证码
     *
     * @param response
     * @param request
     */
    @GetMapping(value = "gifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");

            Captcha captcha = new GifCaptcha(146, 33, 4);
            HttpSession session = request.getSession(true);
            captcha.out(response.getOutputStream());
            session.removeAttribute(CODE_KEY);
            session.setAttribute(CODE_KEY, captcha.text().toLowerCase());
        } catch (Exception e) {
            log.error("图形验证码生成失败", e);
        }
    }
}
