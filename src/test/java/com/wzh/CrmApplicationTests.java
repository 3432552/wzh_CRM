package com.wzh;

import com.wzh.dao.ActivityMapper;
import com.wzh.dao.ActivityRemarkMapper;
import com.wzh.dao.UserMapper;
import com.wzh.domain.Activity;
import com.wzh.domain.ActivityRemark;
import com.wzh.domain.User;
import com.wzh.service.ActivityService;
import com.wzh.util.DateUtil;
import com.wzh.util.MD5Utils;
import com.wzh.vo.PageVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CrmApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;
    @Autowired
    private ActivityService activityService;

    @Test
    void contextLoads1() {
        Map<String, Object> map = new HashMap<>();
        int pageNo1 = 2;
        int pageSize1 = 5;
        //略过的记录数
        int skipPageNum = (pageNo1 - 1) * pageSize1;
        map.put("pageNo", skipPageNum);
        map.put("pageSize", pageSize1);
        List<Activity> activityList = activityMapper.activityListByCondition(map);
        System.out.println("数据=====》:" + activityList.toString());
    }

    @Test
    void contextLoads5() {
        Map<String, Object> map = new HashMap<>();
        int nums = activityMapper.totalNum(map);
        System.out.println("多少条数据》:" + nums);
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
