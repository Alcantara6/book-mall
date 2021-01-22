package com.yanjing.util.response;

public final class ResponseUtils {
    public static Response success() {
        return new Response(ResponseStatus.OK);
    }

    public static <T> Response<T> success(T body) {
        return new Response<T>(ResponseStatus.OK, body);
    }

    public static <T> Response<T> success(String message) {
        return new Response<T>(ResponseStatus.OK, message);
    }

    public static Response fail(ResponseStatus responseStatus) {
        return new Response(responseStatus);
    }

    public static Response fail(ResponseStatus responseStatus, String message) {
        return new Response(responseStatus, message);
    }
}
