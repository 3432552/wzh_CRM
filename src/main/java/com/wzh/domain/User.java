package com.wzh.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wzh
 * @ClassName: User
 * @Description: 用户表
 * @Date: 2020/3/27 22:16
 */
@Data
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -8375997553271545211L;

    //编号
    private String id;
    //用户名字
    private String userName;
    //登陆账号
    private String loginAct;
    //登录密码
    private String loginPwd;
    //邮箱
    private String email;
    //失效时间
    private String expireTime;
    //锁定状态 0：锁定 1：启用
    private String lockState;
    //部门编号
    private String deptNum;
    //允许访问IP
    private String allowIps;
    //创建时间
    private Date createTime;
    //创建人
    private String createBy;
    //修改时间
    private Date editTime;
    //修改人
    private String editBy;
}
