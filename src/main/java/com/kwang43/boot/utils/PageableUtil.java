package com.kwang43.boot.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/12/22 16:25
 */
public class PageableUtil {

    public static Pageable createPageable(int page, int size, String sortField, String sortOrder) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
        return PageRequest.of(page, size, sort);
    }
}