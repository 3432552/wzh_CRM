package com.wzh.exception;

import java.io.Serializable;

/**
 * @Author: wzh
 * @ClassName: BusinessException
 * @Description:
 * @Date: 2020/4/5 13:04
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 7733247376626654117L;

    public BusinessException(String message) {
        super(message);
    }
}
