package com.yanjing.util.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message = "";
    private T body;

    public Response(ResponseStatus responseStatus) {
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
    }

    public Response(ResponseStatus responseStatus, T body) {
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
        this.body = body;
    }

    public Response(ResponseStatus responseStatus, String message) {
        this.code = responseStatus.getCode();
        this.message = message;
    }

    public Response(ResponseStatus responseStatus, String message, T body) {
        this.code = responseStatus.getCode();
        this.message = message;
        this.body = body;
    }
}
