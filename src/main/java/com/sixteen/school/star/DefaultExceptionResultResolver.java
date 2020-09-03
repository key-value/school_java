package com.sixteen.school.star;

import com.sixteen.school.result.Result;
import com.sixteen.school.result.ResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

@Slf4j
public class DefaultExceptionResultResolver implements ExceptionResultResolver {
    private final static int FRAME_EXCEPTION_BASE = 100000;

    public DefaultExceptionResultResolver() {

    }

    @Override
    public Result<?> resolve(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
        if (ex == null) {
            Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
            if (statusCode == 404) {
                Result result = new Result(404, "The path is not mapping.");
                result.setHttpCode(404);
                return result;
            }
            return new Result(500, "some error,but can not get then error");
        }

        Result result = new Result(500, ex);
        boolean resultThrowable = false;
        try {
            if (ex instanceof ResultException) {
                result = ((ResultException) ex).getResult();
//            } else if (ex instanceof FeignException) {
//                Throwable cause = ((FeignException) ex).getCause();
//                if (cause instanceof ResultException) {
//                    resultThrowable = true;
//                    result = ((ResultException) cause).getResult();
//                }
            } else if (ex instanceof AccessDeniedException) {
                Principal principal = request.getUserPrincipal();
                if (principal == null) {
                    result.setHttpCode(HttpServletResponse.SC_UNAUTHORIZED);
                    result.setCode(getFrameExceptionCode(HttpServletResponse.SC_UNAUTHORIZED));
                    result.setMsg("Unauthorized");
                } else {
                    result.setHttpCode(HttpServletResponse.SC_FORBIDDEN);
                    result.setCode(getFrameExceptionCode(HttpServletResponse.SC_FORBIDDEN));
                    result.setMsg("Access denied");
                }
//            } else if (ex instanceof CodedAuthenticationException) {
//                if (((CodedAuthenticationException) ex).getCode().equals(AuthConst.JWT_EXPIRED)) {
//                    result.setCode(getFrameExceptionCode(HttpServletResponse.SC_UNAUTHORIZED, 9));
//                }
//                result.setHttpCode(HttpServletResponse.SC_UNAUTHORIZED);
            } else if (ex instanceof MethodArgumentNotValidException) {
                MethodArgumentNotValidException mex = (MethodArgumentNotValidException) ex;
                BindingResult bindingResult = mex.getBindingResult();
                List<ObjectError> errors = bindingResult.getAllErrors();
                if (errors.size() > 0) {
                    ObjectError error = errors.get(0);
                    if (error.contains(ConstraintViolation.class)) {
                        ConstraintViolation violation = error.unwrap(ConstraintViolation.class);
                        result.setMsg(violation.getRootBeanClass().getSimpleName() + "." + violation.getPropertyPath() + ":" + violation.getMessage());
                    }
                }

            } else if (ex instanceof HttpRequestMethodNotSupportedException) {
                String[] supportedMethods = ((HttpRequestMethodNotSupportedException) ex).getSupportedMethods();
                if (supportedMethods != null && response != null) {
                    response.setHeader("Allow", StringUtils.arrayToDelimitedString(supportedMethods, ", "));
                }
                result.setHttpCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED));
            } else if (ex instanceof HttpMediaTypeNotSupportedException) {
                if (response != null) {
                    List<MediaType> mediaTypes = ((HttpMediaTypeNotSupportedException) ex).getSupportedMediaTypes();
                    response.setHeader("Accept", MediaType.toString(mediaTypes));
                }
                result.setHttpCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE));
            } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
                result.setHttpCode(HttpServletResponse.SC_NOT_ACCEPTABLE);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_NOT_ACCEPTABLE));
            } else if (ex instanceof MissingPathVariableException) {
                result.setHttpCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
            } else if (ex instanceof MissingServletRequestParameterException) {
                result.setHttpCode(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_BAD_REQUEST));
            } else if (ex instanceof ServletRequestBindingException) {
                result.setHttpCode(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_BAD_REQUEST));
            } else if (ex instanceof ConversionNotSupportedException) {
                if (log.isWarnEnabled()) {
                    log.warn("Failed to convert request element: " + ex);
                }
                result.setHttpCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
            } else if (ex instanceof TypeMismatchException) {
                if (log.isWarnEnabled()) {
                    log.warn("Failed to bind request element: " + ex);
                }
                result.setHttpCode(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_BAD_REQUEST));
            } else if (ex instanceof HttpMessageNotReadableException) {
                if (log.isWarnEnabled()) {
                    log.warn("Failed to read HTTP message: " + ex);
                }
                result.setHttpCode(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_BAD_REQUEST));
            } else if (ex instanceof HttpMessageNotWritableException) {
                if (log.isWarnEnabled()) {
                    log.warn("Failed to write HTTP message: " + ex);
                }
                result.setHttpCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
            } else if (ex instanceof MethodArgumentNotValidException) {
                result.setHttpCode(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_BAD_REQUEST));
            } else if (ex instanceof MissingServletRequestPartException) {
                result.setHttpCode(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_BAD_REQUEST));
            } else if (ex instanceof BindException) {
                result.setHttpCode(HttpServletResponse.SC_BAD_REQUEST);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_BAD_REQUEST));
            } else if (ex instanceof NoHandlerFoundException) {
                result.setHttpCode(HttpServletResponse.SC_NOT_FOUND);
                result.setCode(getFrameExceptionCode(HttpServletResponse.SC_NOT_FOUND));
            } else if (ex instanceof AsyncRequestTimeoutException) {
                if (log.isDebugEnabled()) {
                    log.debug("Async timeout for " + request.getMethod() + " [" + request.getRequestURI() + "]");
                }
                if (response != null) {
                    if (!response.isCommitted()) {
                        result.setHttpCode(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                        result.setCode(getFrameExceptionCode(HttpServletResponse.SC_SERVICE_UNAVAILABLE));
                    } else {
                        result.setHttpCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        result.setCode(getFrameExceptionCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
                    }
                }
            } else if (ex instanceof ResponseStatusException) {
                result.setHttpCode(((ResponseStatusException) ex).getStatus().value());
                result.setCode(getFrameExceptionCode(((ResponseStatusException) ex).getStatus().value()));
            }
//            Object traceId = context.getValue(MikeTraceTraceValuesProvider.MIKE_TRACE_ID);
//            result.setTrace(traceId == null ? null : traceId.toString());
//            if ("1".equals(exceptionDetailTraceValueProvider.getValue()) && !resultThrowable) {
//                result.setException(getStackTrace(ex));
//                if (ex instanceof ResultFeignException) {
//                    result.setCause(((ResultFeignException) ex).getResult());
//                }
//            }
        } catch (Exception handlerException) {
            if (log.isWarnEnabled()) {
                log.warn("Handling of [" + ex.getClass().getName() + "] resulted in exception", handlerException);
            }
        }

//        if (result.getCode() < 10000) {
//            if (result.getCode() != 0)
//                result.setCode(mikeProperties.getAppId() * 10000 + result.getCode());
//            else
//                result.setCode(mikeProperties.getAppId() * 10000 + result.getHttpCode());
//        }

        return result;
    }

    private String getStackTrace(Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        return stackTrace.toString();
    }

    private int getFrameExceptionCode(int originCode, int seqNo) {
        return FRAME_EXCEPTION_BASE + originCode * 10 + seqNo;
    }

    private int getFrameExceptionCode(int originCode) {
        return getFrameExceptionCode(originCode, 1);
    }

}
