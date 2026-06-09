package com.kwang43.boot.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 功能：统一的分页结果返回对象
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResult<T> implements Serializable {
    private List<T> content;         // 数据列表
    private long totalElements;      // 总条数
    private int totalPages;          // 总页数
    private int pageNumber;          // 当前页 (0-based)
    private int pageSize;            // 每页大小
    private boolean last;            // 是否是最后一页
    private boolean first;           // 是否是第一页

    // 可以提供一个便捷的构造函数，直接由 Spring 的 Page 转换而来
    public PageResult(Page<T> page) {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.last = page.isLast();
        this.first = page.isFirst();
    }
}