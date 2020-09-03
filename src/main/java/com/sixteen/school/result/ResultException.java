package com.sixteen.school.result;

public class ResultException extends RuntimeException{
    private Result result;

    public ResultException(int code, String message) {
        this(new Result(code, message));
    }

    public ResultException(Result result) {
        this(result, null);
    }

    public ResultException(Result result, Throwable cause) {
        super(result.getMsg(), cause);
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
