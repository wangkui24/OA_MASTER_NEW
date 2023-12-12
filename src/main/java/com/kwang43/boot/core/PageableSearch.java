package com.kwang43.boot.core;

import com.kwang43.boot.model.BaseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/11/21 13:49
 */

@Data
@NoArgsConstructor
public class PageableSearch implements Serializable {
    private boolean sortAsc;
    private String sortBy;
    private Integer pageNum;
    private Integer pageSize;


    public Pageable getPageableByDefault() {
        final List<Sort.Order> lists = new ArrayList<>();

        if(StringUtils.isEmpty(getSortBy())) {
            lists.add(new Sort.Order(Sort.Direction.DESC, BaseEnum.Defalut.CREATE_DATE_TIME));
        } else {
            lists.add(new Sort.Order(isSortAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, getSortBy()));
        }
        return PageRequest.of(getPageNum() == null ? Const.DEFAULT_PAGE_INDEX : getPageNum(),getPageSize() == null ? Const.DEFAULT_PAGE_SIZE : getPageSize(), Sort.by(lists));
    }

    public Pageable getPageableByDefault(String orderBy) {
        final List<Sort.Order> lists = new ArrayList<>();

        if(StringUtils.isEmpty(getSortBy())) {
            lists.add(new Sort.Order(Sort.Direction.DESC, orderBy));
        } else {
            lists.add(new Sort.Order(isSortAsc() ? Sort.Direction.ASC : Sort.Direction.DESC, getSortBy()));
        }
        return PageRequest.of(getPageNum() == null ? Const.DEFAULT_PAGE_INDEX : getPageNum(),getPageSize() == null ? Const.DEFAULT_PAGE_SIZE : getPageSize(), Sort.by(lists));
    }
}