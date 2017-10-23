package org.wxs.core.ex;



/**
 * Created by Administrator on 2015/9/2.
 */
public class ExceptionContext {
    private static ThreadLocal<Message> exceptions = new ThreadLocal<>();



    public static void set(Message ex){
        exceptions.set(ex);
    }
    public static Message get(){
        return exceptions.get();
    }
    public static void clear(){
        exceptions.remove();
    }



    public static class Message{
        private int status;
        private Throwable thr;
        private String message;


        public Message(int status, Throwable thr, String message) {
            this.status = status;
            this.thr = thr;
            this.message = message;
        }
        public Message(Throwable thr) {
            this.status = ErrorCode.NOT_EXPECT;
            this.thr = thr;
            this.message = thr.getMessage();
        }
        public Message(Exception exception) {
            this.status = ErrorCode.NOT_EXPECT;
            this.thr = exception.getCause();
            this.message = exception.getMessage();
        }
        public Message(AppException exception) {
            this.status = exception.status();
            this.thr = exception.getCause();
            this.message = exception.getMessage();
        }

        public Message(RuntimeException exception) {
            this.thr = exception.getCause();
            this.message = exception.getMessage();
        }
        public Throwable thr() {
            return thr;
        }

        public String message() {
            return message;
        }
        public int status(){
            return status;
        }
    }
}
