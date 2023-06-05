package com.pccw.global.user.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pccw.global.user.constant.UserErrorCode;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private static final String SUCCESS_MESSAGE = "success";

    private String requestId;
    private long serverTime;
    private String resultCode;
    private Object data;
    private String message;

    public static UserResponse createSuccessResponse() {
        UserResponse response = new UserResponse();
        response.setResultCode(SUCCESS_MESSAGE);
        return response;
    }

    public static UserResponse createSuccessResponse(Object data) {
        UserResponse response = new UserResponse();
        response.setResultCode(SUCCESS_MESSAGE);
        response.setData(data);
        return response;
    }

    public static UserResponse createFailedResponse(UserErrorCode errorCode) {
        UserResponse response = new UserResponse();
        response.setResultCode(errorCode.getDescription());
        return response;
    }

    public static UserResponse createFailedResponse(UserErrorCode errorCode, String message) {
        UserResponse response = new UserResponse();
        response.setResultCode(errorCode.getDescription());
        response.setMessage(message);
        return response;
    }

    public static UserResponse createFailedResponse(UserErrorCode errorCode, String message, Object data) {
        UserResponse response = new UserResponse();
        response.setResultCode(errorCode.getDescription());
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
