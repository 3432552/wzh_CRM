package com.wzh.exception;

/**
 * @Author: wzh
 * @ClassName: LoginException
 * @Description:
 * @Date: 2020/3/28 22:48
 */
public class LoginException extends RuntimeException {
    private static final long serialVersionUID = -3608667856397125671L;

    public LoginException(String message) {
        super(message);
    }
}
