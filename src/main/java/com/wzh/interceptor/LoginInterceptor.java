package com.wzh.interceptor;

import com.wzh.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: wzh
 * @ClassName: LoginInterceptor
 * @Description:
 * @Date: 2020/3/29 18:11
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("==========================>" + "拦截器开始生效...");
        String url = request.getRequestURI();
        log.info("拦截的url:" + url);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            log.info("拦截器中，没值就进来了！");
            response.sendRedirect("/user/n");
            return false;
        }
        return true;
    }
}
