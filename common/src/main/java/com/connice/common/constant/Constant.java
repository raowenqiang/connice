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
}
