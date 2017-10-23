package com.wxs.util;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.wxs.core.ex.AppException;
import org.wxs.core.ex.ErrorCode;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@JsonIgnoreProperties({"ex","result","error"})
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Result<T,R> {
    private int code = 1;   //默认成功
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
    private T data;

    private AppException ex;
    private R result;
    private Result(){
        code = 1;
    }
    private Result(T data){
        this.data = data;
        this.code = 1;
    }
    private Result(int code, String message){
        this.code = code;
        this.message = message;
    }
    private Result(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(AppException ex){
        this.ex = ex;
        this.code = ex.status();
        this.message = ex.getMessage();
    }

    public static <T,R> Result<T,R> of(){
        return new Result<>();
    }
    public static <T,R> Result<T,R> of(Optional<T> optional){
        if(optional.isPresent()){
            return new Result<>(optional.get());
        }
        return new Result<>();
    }
    public static <T,R> Result<T,R> of(T data){
        return new Result<>(data);
    }
    public static <T,R> Result<T,R> ofNull(){
        return new Result<>();
    }
    public static <T,R> Result<T,R> of(String message,int code){
        return new Result<>(code,message);
    }

    public static <R> DMap<R> ofMap(){
        return DMap.of(new Result<>(Maps.newHashMap()));
    }

    public static <T extends List,R> DList<T,R> ofList(){
        return DList.of(new Result<>(Lists.newArrayList()));
    }
    public static <T extends List,R> DList<T,R> ofList(List<?> data){
        return DList.of(new Result<>(data));
    }

    public static <T,R> Result<T,R> of(AppException ex){
        return new Result<>(ex);
    }
    public static <R> Result<Map<String,Object>,R> of(int code,String message,Map<String,Object> data){
        return new Result<>(code,message,data);
    }
    public static <T,R> Result<T,R> error(int code,String message){
        return of(message,code);
    }
    public static <T,R> Result<T,R> error(String message){
        return of(message,ErrorCode.NOT_EXPECT);
    }
    public static <T,R> Result<T,R> error(AppException ex){
        return of(ex);
    }
    public T data() {
        return data;
    }
    public Result data(T data) {
        this.data = data;
        return this;
    }
    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public static Result notifyUser(String message){
        return of(message, ErrorCode.USER_NOTIFY);
    }
    public static Result notFound(String message){
        return of(message, ErrorCode.NOT_FOUND);
    }
    public static Result success(){
        return new Result().code(1);
    }

    public String message() {
        return message;
    }


    public Result message(String message) {
        this.message = message;
        return this;
    }

    public int code() {
        return code;
    }

    public Result code(int code) {
        this.code = code;
        return this;
    }


    public AppException exception(){
        return ex;
    }

    public Result exception(AppException ex){
        this.ex = ex;
        this.code = ex.status();
        return this;
    }
    public Result exception(Throwable ex){
        this.ex = AppException.raise(ex);
        this.code = this.ex.status();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        if(data instanceof List){
            if(((List) data).size()==0){
                return null;
            }
        }
        if (data instanceof Map){
            if(((Map) data).size() ==0){
                return null;
            }
        }
        return data;
    }



    public boolean isError(){
        return code != 1;
    }


    public Result<T,R> result(R result){
        this.result = result;
        return this;
    }
    public R result(){
        return this.result;
    }


    static class D<T,R>{
        protected Result<T,R> result;
        public D(Result<T,R> result){
            this.result = result;
        }

        public Result<T,R> R(){
            return result;
        }
    }

    public static class DMap <R> extends D<Map<String,Object>,R>{

        public DMap(Result<Map<String, Object>, R> result) {
            super(result);
        }

        public static <R> DMap of(Result<Map<String,Object>,R> result){
            return new DMap(result);
        }

        public DMap<R> put(String key,Object value){
            this.result.data.put(key,value);
            return this;
        }
    }

    public static class DList<T extends List,R> extends D<T,R>{


        public DList(Result<T, R> result) {
            super(result);
        }
        public static <T,R> DList of(Result<T,R> result){
            return new DList(result);
        }
        public DList<T,R> add(T t){
            result.data.add(t);
            return this;
        }
        public DList<T,R> addAll(Collection<? extends T> t){
            result.data.addAll(t);
            return this;
        }
    }
}
