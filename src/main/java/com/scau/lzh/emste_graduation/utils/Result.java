package com.scau.lzh.emste_graduation.utils;

public class Result<T> {
    private Integer code;

    private String message;

    private T data;

    public Result() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result getSuccess(String message){
        return new Result(1,message);
    }

    public static <T> Result getSuccess(String message,T data){
        return new Result(1,message,data);
    }

    public static <T> Result getFail(String message){
        return new Result(0,message);
    }

    public static <T> Result getFail(String message,T data){
        return new Result(0,message,data);
    }

}
