package org.wxs.core.ex;

/**
 * Created by 徐翔 on 2015/11/23.
 */
public abstract class ErrorCode {

    public static final int USER_AUTH = 401;
    /***
     * 通知用户
     */
    public static final int USER_NOTIFY = 100;
    /**
     * 不存在
     */
    public static final int NOT_FOUND = 404;

    /**
     * 未预期的异常
     */
    public static final int NOT_EXPECT = 500;
    /**
     * 未预期的异常2
     */
    public static final int NOT_EXPECT2 = -1;

    /**
     * RPC调用
     */
    public static final int RPC = 1000;
}
