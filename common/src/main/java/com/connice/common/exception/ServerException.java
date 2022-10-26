package com.connice.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 16:52 2022/10/8
 * Modified By:
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public ServerException(String msg) {
        super(msg);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }

    public ServerException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ServerException(String msg, Throwable e) {
        super(msg, e);
        this.code = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }
}
