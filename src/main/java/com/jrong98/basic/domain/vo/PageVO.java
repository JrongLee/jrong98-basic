package com.jrong98.basic.domain.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Setter;

/**
 * 分页查询
 * @author jrong98
 * @date 2022/6/22
 */
@Setter
public class PageVO {

    public static final Integer DEFAULT_PAGE_NO = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页
     * @mock 1
     */
    @JsonAlias(value = "page")
    private Integer pageNo;

    /**
     * 每一页的大小
     * @mock 10
     */
    @JsonAlias(value = "per_page")
    private Integer pageSize;

    /**
     * 获取分页
     * @return
     */
    public Integer getPageNo() {
        return null == pageNo ? DEFAULT_PAGE_NO : pageNo;
    }

    /**
     * 获取页大小
     * @return
     */
    public Integer getPageSize() {
        return null == pageSize ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
