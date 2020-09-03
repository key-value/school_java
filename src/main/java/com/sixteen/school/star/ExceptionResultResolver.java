package com.sixteen.school.star;

import com.sixteen.school.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExceptionResultResolver {
    Result<?> resolve(HttpServletRequest request, HttpServletResponse response, Throwable ex);
}