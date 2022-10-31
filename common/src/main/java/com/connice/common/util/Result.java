package com.connice.common.util;

import com.connice.common.exception.ErrorCode;
import lombok.Data;

/**
 * @Author: WenQiangRao
 * @Description: 统一返回类
 * @Date: Created in 16:41 2022/10/8
 * Modified By:
 **/
@Data
public class Result<T> {
    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private T data;// 返回数据

    public Result() {
        this.flag = true;
        this.code = 1;
        this.message = "成功";
        this.data = null;
    }

    public Result(Boolean flag, Integer code, String message, T data) {
        this.flag = false;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error() {
        return error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    public static <T> Result<T> error(String msg) {
        return error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static <T> Result<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

}
