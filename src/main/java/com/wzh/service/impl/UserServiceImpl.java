package com.wzh.service.impl;

import com.wzh.dao.UserMapper;
import com.wzh.domain.User;
import com.wzh.exception.BusinessException;
import com.wzh.exception.LoginException;
import com.wzh.service.UserService;
import com.wzh.util.DateUtil;
import com.wzh.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpServletRequest request;

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
            throw new LoginException("账号或密码输入错误!");
        }
        log.info(user.toString());
        if (user.getLockState().equals("0")) {
            throw new LoginException("账号已锁定，请联系管理员!");
        }
        if (user.getExpireTime().compareTo(DateUtil.expireTime(new Date())) < 0) {
            throw new LoginException("账号已失效,请联系管理员!");
        }
        if (!user.getAllowIps().contains(ip)) {
            throw new LoginException("你的ip被封禁,禁止登陆!");
        }
        return user;
    }

    @Override
    public int updatePwd(String id, String frontNewPwd) throws BusinessException {
        String frontDbPwd = (String) request.getSession().getAttribute("op");
        String oldDb = MD5Utils.MD5(frontDbPwd);
        log.info("前端传来的原密码:" + frontDbPwd);
        String newPwd = MD5Utils.MD5(frontNewPwd);
        User u = userMapper.selUserById(id);
        String dbPwd = u.getLoginPwd();
        if (!oldDb.equals(dbPwd)) {
            throw new BusinessException("原密码输入错误!");
        }
        if (dbPwd.equals(newPwd)) {
            throw new BusinessException("新密码和原密码一致，请重新输入新密码!");
        }
        int up = userMapper.updatePwdById(id, newPwd);
        if (up > 0) {
            return up;
        } else {
            throw new BusinessException("更新密码失败!");
        }
    }
}
