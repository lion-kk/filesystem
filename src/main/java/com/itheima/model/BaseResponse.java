package com.itheima.model;


public class BaseResponse<T> {
    private String code;
    private String msg;
    private T data;
    public  BaseResponse(T data){
        this.code= "0";
        this.msg = "成功";
        this.data = data;
    }
    public  static<T> BaseResponse<T> success(T data){
        return new BaseResponse(data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}