package com.connice.common.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.connice.common.constant.Constant;
import com.connice.common.exception.ServerException;

import java.util.List;

/**
 * 校验工具类
 *
 * @author
 */
public class AssertUtils {

    public static void isBlank(String str, String variable) {
        if (StrUtil.isBlank(str)) {
            throw new ServerException(variable + "不能为空");
        }
    }

    public static void isNull(Object object, String variable) {
        if (object == null) {
            throw new ServerException(variable + "不能为空");
        }
    }

    public static void isArrayEmpty(Object[] array, String variable) {
        if (ArrayUtil.isEmpty(array)) {
            throw new ServerException(variable + "不能为空");
        }

    }

    /**
     * 判断一个对象是否存在于list中
     * @return  true  存在 false 不存在
     */
    public static Boolean isExistList(List list , String o){
        isBlank(o,"IP");
        if (ArrayUtil.isEmpty(list)){   //为空则表示无黑名单，直接通过
            return Constant.returnTrue;
        }
        if (list.contains(o)){
            return Constant.returnTrue;
        }
        return Constant.returnFalse;

    }


}
