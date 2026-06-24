package com.kwang43.boot.config;


import lombok.Data;

@Data
public class Pagination {

    private long totalElements;      // 总条数

    private int totalPages;          // 总页数

    private int pageNumber;          // 当前页 (0-based)

    private int pageSize;            // 每页大小

    private boolean last;            // 是否是最后一页

    private boolean first;           // 是否是第一页

}
