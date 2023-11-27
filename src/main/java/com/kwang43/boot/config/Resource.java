package com.kwang43.boot.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/12 12:31
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resource<T> {
    private T data;
    protected List<Message> messageContent;


    public Resource(T data) {
        this.data = data;
    }

    public Resource(List<Message> messageContent) {
        setMessageContent(messageContent);
    }

    public static final <T> Resource<T> of(T data) {
        final Resource resource = new Resource();
        resource.setData(data);
        return resource;
    }
}