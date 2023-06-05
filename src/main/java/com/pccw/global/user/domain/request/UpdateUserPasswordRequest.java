package com.pccw.global.user.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUserPasswordRequest {
    /**
     * 用户ID
     */
    @NotNull(message = "Required valid id")
    @ApiModelProperty(value = "用户ID", required = true)
    private long id;

    /**
     * 上一个密码
     */
    @NotNull(message = "Required valid prevPassword")
    @ApiModelProperty(value = "原密码", required = true)
    private String prevPassword;

    /**
     * 当前密码
     */
    @NotNull(message = "Required valid currentPassword")
    @ApiModelProperty(value = "新密码", required = true)
    private String currentPassword;
}
