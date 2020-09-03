package com.sixteen.school.star;

import com.sixteen.school.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice(annotations = {controlSign.class})
@Slf4j
public class ResultExceptionControllerAdvice {

    private final ExceptionResultResolver exceptionResultResolver;

    public ResultExceptionControllerAdvice(ExceptionResultResolver exceptionResultResolver) {
        this.exceptionResultResolver = exceptionResultResolver;
    }

    @ExceptionHandler(value = Exception.class)
    public Result exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        Result<?> result = exceptionResultResolver.resolve(request, response, ex);
        int statusCode = result.getHttpCode();
        if (statusCode == 0)
            statusCode = 500;

        response.setStatus(statusCode);

        if (result.isClientError()) {
            log.warn("Handling of client exception :", ex);
        } else {
            log.error("Handling of server exception :", ex);
        }

        return result;
    }

}
