package com.soullan.fakeojbe.util;

import com.soullan.fakeojbe.modle.constant.HttpCode;
import com.soullan.fakeojbe.modle.response.Result;

public class ResultUtil extends BaseUtil {

    public static Result cmd(Integer res, String code, String message, Object Data) {
        System.out.println(message);
        return new Result(res, code, message, Data);
    }

    public static Result success() {
        return new Result(Result.SUCCESS, HttpCode.OK.getCode(), HttpCode.OK.getMessage(), null);
    }

    public static Result dSuccess(Object data) {
        return new Result(Result.SUCCESS, HttpCode.OK.getCode(), HttpCode.OK.getMessage(), data);
    }

    public static Result cSuccess(String code) {
        return new Result(Result.SUCCESS, code, "success", null);
    }

    public static Result cmSuccess(String code, String message) {
        return new Result(Result.SUCCESS, code, message, null);
    }

    public static Result cmdSuccess(String code, String message, Object Data) {
        return new Result(Result.SUCCESS, code, message, Data);
    }

    public static Result fail() {
        return new Result(Result.FAIL, HttpCode.FORBIDDEN.getCode(), HttpCode.FORBIDDEN.getMessage(), null);
    }

    public static Result cFail(String code) {
        return new Result(Result.FAIL, code, "success", null);
    }

    public static Result cmFail(String code, String message) {
        return new Result(Result.FAIL, code, message, null);
    }

    public static Result cmdFail(String code, String message, Object Data) {
        return new Result(Result.FAIL, code, message, Data);
    }

}
