package com.pccw.global.user.web;

import com.pccw.global.user.constant.UserErrorCode;

public class UserException extends RuntimeException {
    private UserErrorCode errorCode;
    private Object data;

    public UserException(String message, Throwable e) {
        super(message, e);
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, UserErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public UserException(UserErrorCode errorCode, String messageTemplate, Object... values) {
        super(String.format(messageTemplate, values));
        this.errorCode = errorCode;
    }

    public UserException(String message, UserErrorCode errorCode, Object data) {
        super(message);
        this.errorCode = errorCode;
        this.data = data;
    }

    public UserErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(UserErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
