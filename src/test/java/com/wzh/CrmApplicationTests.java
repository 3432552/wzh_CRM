package com.wzh;

import com.wzh.dao.ActivityMapper;
import com.wzh.dao.UserMapper;
import com.wzh.domain.Activity;
import com.wzh.domain.User;
import com.wzh.util.DateUtil;
import com.wzh.util.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
class CrmApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ActivityMapper activityMapper;

    @Test
    void contextLoads() {
        Activity activity = new Activity();
        activity.setOwner("1");
        activity.setName("去谈合作");
        activity.setStartDate("2018-03-13");
        activity.setEndDate("2019-09-18");
        activity.setType("会议");
        activity.setState("计划中");
        activity.setBudgetcost("123");
        activity.setActualcost("244");
        activity.setDescription("希望何做能谈下来");
        int r = activityMapper.addAct(activity);
        if (r > 0) {
            System.out.println("新增成功！");
        } else {
            System.out.println("新增失败！");
        }
    }

}
