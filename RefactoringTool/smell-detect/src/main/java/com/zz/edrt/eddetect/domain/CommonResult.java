package com.zz.edrt.eddetect.domain;

import lombok.Data;

@Data
public class CommonResult<T> {
    private T data;
    private Integer code;
    private String message;

    public static final int SUCCESS_CODE = 0;
    public static final int UNKNOWN_FAIL_CODE = 1;

    public static final String EMPTY_DATA = "";
    public static final String SUCCESS_EMPTY_DATA = "success";

    public static final String EMPTY_MESSAGE = "";

    public CommonResult(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(data, SUCCESS_CODE, EMPTY_MESSAGE);
    }

    public static CommonResult<String> success() {
        return new CommonResult<>(SUCCESS_EMPTY_DATA, SUCCESS_CODE, EMPTY_MESSAGE);
    }

    public static CommonResult<String> fail(String msg) {
        return new CommonResult<>(EMPTY_DATA, UNKNOWN_FAIL_CODE, msg);
    }

    public static CommonResult<String> fail(Exception e) {
        return fail(e.getMessage());
    }

}