package com.zhoulesin.retrofitdemo.bean;

/**
 * Created by zhoul on 2018/9/30.
 */

public class RetroResult<T> {
    //{"code":200,"message":"成功!","result":{...}}
    private int code;
    private String message;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public RetroResult() {

    }

    public RetroResult(int code, String message, T result) {

        this.code = code;
        this.message = message;
        this.result = result;
    }
}
