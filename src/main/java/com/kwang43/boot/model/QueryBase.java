package com.kwang43.boot.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryBase implements Serializable {

    private static final long serialVersionUID = -8564649172132862866L;

    private Integer page;

    private Integer perPage;

    private String sortField;

    private boolean sortAsc;
}
