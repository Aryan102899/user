package com.pccw.global.user.web;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.pccw.global.user.constant.UserErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@ControllerAdvice
public class ErrorHandler {
    public final static String REQUEST_ID = "requestId";

    /**
     * 捕获未知异常。
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UserResponse handleAllException(Exception e, HttpServletRequest httpServletRequest) {
        return constructResponse(UserResponse.createFailedResponse(UserErrorCode.UNKNOWN_ERROR), httpServletRequest);
    }

    /**
     * 捕获UserException
     *
     * @param e
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @ExceptionHandler({UserException.class})
    @ResponseStatus(HttpStatus.OK)
    public UserResponse handleServerError(UserException e, HttpServletRequest httpServletRequest) {
        return constructResponse(UserResponse.createFailedResponse(e.getErrorCode(), e.getMessage(), e.getData()),
                httpServletRequest);
    }

    /**
     * 捕获非法请求参数异常。
     */
    @ResponseBody
    @ExceptionHandler({
            IllegalArgumentException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            BindException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMediaTypeNotSupportedException.class
    })
    @ResponseStatus(HttpStatus.OK)
    public UserResponse handleInvalidParam(Exception e, HttpServletRequest httpServletRequest) {
        if (e instanceof ConstraintViolationException) {
            List<String> errors = Lists.newArrayList();
            for (ConstraintViolation<?> s : ((ConstraintViolationException) e).getConstraintViolations()) {
                errors.add(s.getMessage());
            }
            return constructResponse(
                    UserResponse.createFailedResponse(UserErrorCode.INVALID_PARAMETER, Joiner.on("|").join(errors)),
                    httpServletRequest);
        } else if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            StringBuilder message = new StringBuilder();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                message.append(fieldError.getDefaultMessage()).append("|");
            }
            if (StringUtils.isNotEmpty(message)) {
                message.deleteCharAt(message.length() - 1);
            }
            return constructResponse(
                    UserResponse.createFailedResponse(UserErrorCode.INVALID_PARAMETER, message.toString()),
                    httpServletRequest);
        } else if (e instanceof BindException) {
            StringBuilder message = new StringBuilder();
            for (ObjectError error : ((BindException) e).getBindingResult().getAllErrors()) {
                message.append(error.getDefaultMessage()).append("|");
            }
            if (StringUtils.isNotEmpty(message)) {
                message.deleteCharAt(message.length() - 1);
            }
            return constructResponse(
                    UserResponse.createFailedResponse(UserErrorCode.INVALID_PARAMETER, message.toString()),
                    httpServletRequest);
        }
        return constructResponse(UserResponse.createFailedResponse(UserErrorCode.INVALID_PARAMETER, e.getMessage()),
                httpServletRequest);
    }

    private UserResponse constructResponse(UserResponse response, HttpServletRequest request) {
        response.setRequestId((String) request.getAttribute(REQUEST_ID));
        response.setServerTime(System.currentTimeMillis() / 1000);
        return response;
    }
}
