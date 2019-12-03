package com.gw.dev.hcbq.entity;

public class ResultVO {
    private int code;

    private String msg;

    private Long count;

    private Object data;

    public ResultVO(Object obj,Long count){
        setCode(0);
        setMsg("");
        setCount(count);
        setData(obj);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
