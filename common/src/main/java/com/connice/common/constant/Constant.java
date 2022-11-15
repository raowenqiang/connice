package com.connice.common.constant;

/**
 * 常量
 *
 * @author
 */
public interface Constant {
    /**
     * 根节点标识
     */
    Long ROOT = 0L;
    /**
     * 当前页码
     */
    String PAGE = "page";
    /**
     * 数据权限
     */
    String DATA_SCOPE = "dataScope";
    /**
     * 超级管理员
     */
    Integer SUPER_ADMIN = 1;
    /**
     * 禁用
     */
    Integer DISABLE = 0;
    /**
     * 启用
     */
    Integer ENABLE = 1;
    /**
     * 失败
     */
    Integer FAIL = 0;
    /**
     * 成功
     */
    Integer SUCCESS = 1;
    /**
     * OK
     */
    String OK = "OK";
    /**
     * 黑名单缓存名
     */
    String conncie_blacklist="connice_blacklist";

    /**
     * 返回结果：是
     */
    Boolean returnTrue = true;
    /**
     * 返回结果:否
     */
    Boolean returnFalse = false;
    /**
     * token名字
     */
    String AUTHORIZE_TOKEN = "token";
    String LOGIN_TOKEN = "loginToken";
    /**
     * 返回成功
     */
    String RETURN_SUCCESS = "SUCCESS";
    /**
     * 返回失败
     */
    String RETURN_FAIL = "fail";
    String state0 = "0";  //审核中
    String state1 = "1";  //启用
    String state2 = "2";   //限制使用
    String state3 = "3";   //删除

    /**
     * 时间
     */
    int HOURS_SEN = 3600;
     int HOURS_SEN24 = 86400;
     Long TIME1 = 1L;
     Long TIME5 = 5L;
     Long TIME10 = 10L;
     Long TIME30 = 30L;
     Long TIME365 = 365L;
     Long YEAR5 = 1825L;
     Long MONTH1 = 2592000L;
     Long YEAR2 = 63072000L;
     Long TIME365FORSECOND = 31536000L;
     String LOGIN_USER_TOKEN_PREFIX = "LOGIN_USER_TOKEN_";
     String TOKEN_ = "TOKEN_";
     String DICT_TYPE_ = "DICT_TYPE_";
     String SOCKET_SID = "SOCKET_SID_";
}
