package com.example.dietAssistant.result;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 200;
        return result;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = 200;
        return result;
    }
}
