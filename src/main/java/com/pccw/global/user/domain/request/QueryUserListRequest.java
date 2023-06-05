package com.pccw.global.user.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pccw.global.user.validation.annotations.CheckEmail;
import com.pccw.global.user.validation.annotations.CheckMobile;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryUserListRequest {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String name;

    /**
     * 手机号
     */
    @CheckMobile(message = "Required valid mobile")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @CheckEmail(message = "Required valid email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private Byte status;

    /**
     * 分页参数-偏移量
     */
    private Long offset;

    /**
     * 分页参数-数量
     */
    private Long count;
}
