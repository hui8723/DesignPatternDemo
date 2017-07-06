package com.example.tangminghui.designpatterndemo.entity;

import java.util.List;

/**
 * Created by tangminghui on 2017/6/27.
 */

public class Result<T> {

    private String msg;
    private List<T> result;
    private String retCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{msg=" + msg +
                ",result=" + result.toString() +
                ",retCode=" + retCode +
                "}";
    }
}
