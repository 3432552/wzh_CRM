package com.wzh;

import com.wzh.dao.ActivityMapper;
import com.wzh.dao.ActivityRemarkMapper;
import com.wzh.dao.UserMapper;
import com.wzh.domain.Activity;
import com.wzh.domain.ActivityRemark;
import com.wzh.domain.User;
import com.wzh.util.DateUtil;
import com.wzh.util.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CrmApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Test
    void contextLoads1() {
        int res = userMapper.updatePwdById("1", "12345");
        if (res > 0) {
            System.out.println("修改密码成功!");
        }
    }

    @Test
    void contextLoads2() {
        String dbPwd = "123456";
        String saltPwd = MD5Utils.generate(dbPwd);
        System.out.println("普通加密:" + MD5Utils.MD5(dbPwd));
        System.out.println("加盐密码:" + saltPwd);
        System.out.println("校验是否成功:" + MD5Utils.verify("123456", saltPwd));
    }
}
