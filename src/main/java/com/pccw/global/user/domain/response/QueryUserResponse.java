package com.pccw.global.user.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pccw.global.user.constant.UserStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryUserResponse {
    @ApiModelProperty(value = "用户id", required = true)
    private Long id;

    @ApiModelProperty(value = "用户名称", required = true)
    private String name;

    @ApiModelProperty(value = "用户电话号", required = true)
    private String mobile;

    @ApiModelProperty(value = "用户邮箱", required = true)
    private String email;

    @ApiModelProperty(value = "用户状态", required = true)
    private Byte status;

    @ApiModelProperty(value = "用户创建时间", required = true)
    private Date createTime;
}
