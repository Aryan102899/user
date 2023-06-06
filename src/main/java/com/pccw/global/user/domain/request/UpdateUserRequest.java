package com.pccw.global.user.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pccw.global.user.validation.annotations.CheckEmail;
import com.pccw.global.user.validation.annotations.CheckMobile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserRequest {
    /**
     * 用户id
     */
    @NotNull(message = "Required valid id")
    @ApiModelProperty(value = "用户id", required = true)
    private Long id;

    /**
     * 手机号
     */
    @CheckMobile(message = "Required valid mobile", optional = true)
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @CheckEmail(message = "Required valid email", optional = true)
    @ApiModelProperty(value = "邮箱")
    private String email;
}
