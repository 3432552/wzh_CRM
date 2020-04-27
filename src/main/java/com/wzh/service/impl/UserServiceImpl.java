package com.wzh.service.impl;

import com.wzh.dao.UserMapper;
import com.wzh.domain.User;
import com.wzh.exception.LoginException;
import com.wzh.service.UserService;
import com.wzh.util.DateUtil;
import com.wzh.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: wzh
 * @ClassName: UserServiceImpl
 * @Description:
 * @Date: 2020/3/27 23:49
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.userList();
    }

    @Override
    public User userLogin(String loginAct, String loginPwd, String ip) throws LoginException {
        log.info(loginAct + "\t" + loginPwd + "\t" + ip);
        String md5Pwd = MD5Utils.MD5(loginPwd);
        User user = userMapper.login(loginAct, md5Pwd);
        if (StringUtils.isEmpty(user)) {
            throw new LoginException("账号或密码输入错误");
        }
        log.info(user.toString());
        if (user.getLockState().equals("0")) {
            throw new LoginException("账号已锁定，请联系管理员");
        }
        if (user.getExpireTime().compareTo(DateUtil.expireTime(new Date())) < 0) {
            throw new LoginException("账号已失效,请联系管理员");
        }
        if (!user.getAllowIps().contains(ip)) {
            throw new LoginException("你的ip被封禁,禁止登陆");
        }
        return user;
    }

}
