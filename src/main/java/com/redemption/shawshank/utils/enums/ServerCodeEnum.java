package com.redemption.shawshank.utils.enums;

/**
 * Author : xingshukui .
 * Date : 2017/7/7.
 * Desc :
 */
public enum ServerCodeEnum {

    SUCCESS(0,"成功"),
    ERROR(40001,"系统错误"),
    PARM_NULL(40002,"参数为空"),
    PARM_ELE_NULL(40003,"参数对象中有字段为空"),
    PARM_ERROE(40004,"参数错误"),
    RESULT_NULL(40005,"查询结果为空"),
    DB_OPER_ERROR(40006,"数据库操作失败")
    ;


    private Integer code;
    private String msg;

    ServerCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
