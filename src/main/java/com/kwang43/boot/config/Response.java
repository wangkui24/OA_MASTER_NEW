package com.kwang43.boot.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kwang43.boot.utils.HttpStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    /**
     * 状态码
     */
    protected Integer code;
    /**
     * 响应信息
     */
    protected String msg;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 若没有数据返回，默认状态码为 200，提示信息为“请求成功！”
     */
    public Response() {
        this.code = HttpStatus.SUCCESS;
        this.msg = "SUCCESS";
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
        public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为 200，默认提示信息为“请求成功！”
     * @param data
     */
    public Response(T data) {
        this.code = HttpStatus.SUCCESS;
        this.msg = "SUCCESS";
        this.data = data;
    }

    /**
     * 有数据返回，状态码为 200，人为指定提示信息
     * @param data
     * @param msg
     */
    public Response(String msg, T data) {
        this.code = HttpStatus.SUCCESS;
        this.msg = msg;
        this.data = data;
    }

}
