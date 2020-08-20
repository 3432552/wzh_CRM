package com.wzh.exception;

import com.wzh.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wzh
 * @ClassName: LoginControllerAdvice
 * @Description:
 * @Date: 2020/3/29 0:53
 */
@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public Result loginException(LoginException e) {
        log.info("全局异常信息:" + e.getMessage());
        return Result.error(e.getMessage());
    }
    //业务异常统一处理
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result businessException(BusinessException e) {
        log.info("全局异常信息:" + e.getMessage());
        return Result.error(e.getMessage());
    }
}
