package com.sixteen.school.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Data
public class Result<T> {
    private int code;
    @JsonIgnore
    private int httpCode = 500;
    private long ts;
    private String msg;
    private T data;
    private Map<String, Object> ext;

    private String host = hostName;
    private String trace;
    private Result cause;
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private Throwable ex;
    private String exception;

    private static String hostName = null;

    static {
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
    }

    public Result() {
    }

    @Deprecated
    public Result(T data) {
        this(200, data, "Success");
    }

    public Result(int code) {
        this(code, (String) null);
    }

    public Result(int code, String message) {
        this(code, null, message);
    }

    public Result(int code, String message, int httpCode) {
        this(code, null, message);
        this.httpCode = httpCode;
        if (httpCode < 400 || httpCode >= 600)
            throw new IllegalArgumentException("Http code must be 4** or 5**, current is " + httpCode);
    }

    public Result(int code, Throwable ex) {
        this(code, ex.getMessage(), ex);
    }

    public Result(int code, String message, Throwable ex) {
        this(code, message, ex, null);
    }

    public Result(int code, String message, Throwable ex, Result cause) {
        this.code = code;
        this.msg = message;
        this.ex = ex;
        this.cause = cause;
        ts = System.currentTimeMillis();
    }

    public Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.msg = message;
        ts = System.currentTimeMillis();
    }

    @JsonIgnore
    public boolean isClientError() {
        return getHttpCode() >= 400 && getHttpCode() < 500;
    }

}
