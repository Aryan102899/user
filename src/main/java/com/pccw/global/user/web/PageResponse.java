package com.pccw.global.user.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> {
    /**
     * 偏移量
     */
    private long offset;

    /**
     * 查询记录数
     */
    private Long count;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 查询结果集
     */
    private List<T> list;

    public PageResponse(long offset, Long count, Long total, List<T> list) {
        this.offset = offset;
        this.count = count;
        this.total = total;
        this.list = list;
    }
}
