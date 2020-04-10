package com.wzh.service;

import com.wzh.domain.User;
import com.wzh.exception.LoginException;

import java.util.List;

/**
 * @Author: wzh
 * @ClassName: UserService
 * @Description:
 * @Date: 2020/3/27 23:49
 */
public interface UserService {
    List<User> getUserList();
    User userLogin(String loginAct, String loginPwd, String ip) throws LoginException;
}
