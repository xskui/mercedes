package com.redemption.shawshank.web.base;

import com.redemption.shawshank.utils.enums.ServerCodeEnum;

import java.io.Serializable;

/**
 * Author : xingshukui .
 * Date : 2017/7/7.
 * Desc :
 */
public class ResponBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer status;
    private String reason;
    private Object object;

    public ResponBean(Integer status, String reason, Object object) {
        this.status = status;
        this.reason = reason;
        this.object = object;
    }

    public ResponBean(Integer status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public static ResponBean ServerResponBean(ServerCodeEnum codeEnum){
        return new ResponBean(codeEnum.code(),codeEnum.msg());
    }

    public static ResponBean errorResponBean(){
        return new ResponBean(ServerCodeEnum.ERROR.code(),ServerCodeEnum.ERROR.msg());
    }

    public static ResponBean successRespon(){
        return new ResponBean(ServerCodeEnum.SUCCESS.code(),ServerCodeEnum.SUCCESS.msg());
    }

    public static ResponBean successRespon(Object o){
        return new ResponBean(ServerCodeEnum.SUCCESS.code(),ServerCodeEnum.SUCCESS.msg(),o);
    }

    public static ResponBean respon(int code,String msg){
        return new ResponBean(code,msg);
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public Object getObject() {
        return object;
    }
}
