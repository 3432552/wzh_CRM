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
    void contextLoads() {
        String aid = "27";
        List<ActivityRemark> list = activityRemarkMapper.actRemarkList(aid);
        System.out.println(list.toString());
    }

}
