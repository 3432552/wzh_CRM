package com.wzh.common;

import javafx.beans.binding.ObjectExpression;

import java.util.HashMap;

/**
 * @Author: wzh
 * @ClassName: Result
 * @Description:
 * @Date: 2020/3/27 23:53
 */
public class Result extends HashMap<String, Object> {
    private Integer code;
    private String msg;
    private Object data;

    public Result() {
        put("code", 200);
        put("msg", "操作成功");
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object data) {
        Result result = new Result();
        result.put("code", 200);
        result.put("data", data);
        return result;
    }

    public static Result error(Object msg) {
        Result result = new Result();
        result.put("code", 500);
        result.put("msg", msg);
        return result;
    }

    public static Result wan(Object msg) {
        Result result = new Result();
        result.put("code", 5);
        result.put("msg", msg);
        return result;
    }
}
