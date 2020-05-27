package com.wzh.web.listener;

import com.wzh.dao.UserMapper;
import com.wzh.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
@Slf4j
public class SysInitListener implements ServletContextListener {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("开启监听。。。。");
        ServletContext servletContext = sce.getServletContext();
        List<User> u = userMapper.userList();
        log.info("u的对象是:" + u);
        servletContext.setAttribute("application", u);

    }
}
