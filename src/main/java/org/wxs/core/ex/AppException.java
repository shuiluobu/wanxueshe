package org.wxs.core.ex;


import static org.wxs.core.ex.ErrorCode.*;

/**
 * Created by Administrator on 2015/9/2.
 */
public class AppException  extends RuntimeException {

    private String requestId;
    private int status = NOT_EXPECT2;

    public AppException() {
        super();
    }

    public AppException(int status) {
        this.status = status;
    }
    public AppException(String message, int status) {
        this.status = status;
    }
    public AppException(String requestId,String message, int status) {

        this.status = status;
        this.requestId = requestId;
    }

    public AppException(Throwable cause, int status) {
        this.status = status;
    }

    public AppException(String message, Throwable cause, int status) {
        this.status = status;
    }

    public AppException requestId(String requestId){
        this.requestId = requestId;
        return this;
    }

    public static AppException raise(String message) {
        return new AppException(message,NOT_EXPECT);
    }
    public static AppException raise(String message,int status) {
        return new AppException(message,status);
    }
    public static AppException raise(String requestId,Throwable cause) {
        return new AppException(cause,NOT_EXPECT).requestId(requestId);
    }

    /**
     * 通知用户
     * @param message
     * @return
     */
    public static AppException notifyUser(String message){
        return raise(message,USER_NOTIFY);
    }

    /**
     * 数据不存在
     * @param message
     * @return
     */
    public static AppException notExist(String message){
        return raise(message,NOT_FOUND);
    }

    public static AppException raise(Throwable cause) {
        return new AppException(cause,NOT_EXPECT);
    }

    public static AppException raise(String message,int status, Throwable cause) {
        return new AppException(message, cause,status);
    }

    public int status(){
        return status;
    }
    public AppException status(int status){
        this.status = status;
        return this;
    }
}
