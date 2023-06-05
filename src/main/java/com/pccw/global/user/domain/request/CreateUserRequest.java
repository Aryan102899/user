package com.pccw.global.user.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pccw.global.user.validation.annotations.CheckEmail;
import com.pccw.global.user.validation.annotations.CheckMobile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest {
    /**
     * 用户名
     */
    @NotNull(message = "Required valid name")
    @ApiModelProperty(value = "用户名", required = true)
    private String name;

    /**
     * 手机号
     */
    @NotNull
    @CheckMobile(message = "Required valid mobile")
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

    /**
     * 密码
     */
    @NotNull(message = "Required valid password")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 邮箱
     */
    @NotNull
    @CheckEmail(message = "Required valid email")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;
}
