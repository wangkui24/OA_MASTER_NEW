package com.kwang43.boot.config;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PagedResource<T> extends Response<T> {

    private List<T> dataList;

    private Pagination pagination;

    public PagedResource() {}

    public PagedResource(Page<T> page, String pageParam, String sizeParam) {
        this(page, pageParam, sizeParam, null, null);
    }

    public PagedResource(Page<T> page, String pageParam, String sizeParam, String sortParam, String sortField) {
        this.dataList = page.getContent();
        this.pagination = new Pagination();

        populatePagination(page);
    }

    private void populatePagination(Page<T> page) {
        int perPage = page.getSize();
        int totalPages = page.getTotalPages();
        int pageNumber = page.getNumber();

        this.pagination.setPageNumber(pageNumber);
        this.pagination.setPageSize(perPage);
        this.pagination.setTotalPages(totalPages);
        this.pagination.setTotalElements(page.getTotalElements());
        this.pagination.setLast(page.isLast());
        this.pagination.setFirst(page.isFirst());

    }
}
