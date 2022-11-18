package com.connice.common.util;

import cn.hutool.core.util.StrUtil;
import com.connice.common.exception.ServerException;

/**
 * @Author: WenQiangRao
 * @Description:
 * @Date: Created in 16:40 2022/11/17
 * Modified By:
 **/
public class ReturnUtils {

    /**
     * 抛出错误信息
     * @param variable
     */
    public static void returnBlank(String variable) {
        throw new ServerException(variable + "不能为空");
    }
}
