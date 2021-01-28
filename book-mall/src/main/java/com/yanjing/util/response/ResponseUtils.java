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

    public static Response badRequest(String message) {
        return new Response(ResponseStatus.BAD_REQUEST, message);
    }

    public static Response notFound(String message) {
        return new Response(ResponseStatus.NOT_FOUND, message);
    }

    public static Response unAuthorized(String message) {
        return new Response(ResponseStatus.UNAUTHORIZED, message);
    }
}
