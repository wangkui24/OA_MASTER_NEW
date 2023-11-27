package com.kwang43.boot.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 功能：
 * 作者：kwang43
 * 日期：2023/10/12 12:34
 */
@Setter
@Getter
@NoArgsConstructor
public class Message {
    private String field;
    private String content;
    private MessageType messageType;
    private Object payload;

    public Message(MessageType messageType, String field, String content) {
        this.messageType = messageType;
        this.field = field;
        this.content = content;
    }

    public Message(MessageType messageType, Object payload, String content) {
        this.messageType = messageType;
        this.payload = payload;
        this.content = content;
    }

    public Message(MessageType messageType, String content) {
        this.content = content;
        this.messageType = messageType;
    }

    public Message(String field, String content) {
        this.field = field;
        this.content = content;
    }

    public enum MessageType {
        ERROR, NOT_FOUND, FORBIDDEN, UNAUTHORIZED, INTERNAL_SERVER_ERROR
    }
}