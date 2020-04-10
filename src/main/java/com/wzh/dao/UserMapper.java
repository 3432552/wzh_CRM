package com.wzh.dao;

import com.wzh.domain.User;
import com.wzh.exception.LoginException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wzh
 * @ClassName: UserMapper
 * @Description:
 * @Date: 2020/3/27 23:42
 */
public interface UserMapper {
    List<User> userList();
    User login(@Param("loginAct") String loginAct, @Param("loginPwd") String loginPwd) throws LoginException;
}
