package com.redemption.shawshank.utils.enums;

/**
 * Author : xingshukui .
 * Date : 2017/7/7.
 * Desc :
 */
public enum ResourceType {
    MENU(0,"menu"),
    BUTTON(1,"button")
    ;
    private Integer code;
    private String desc;

    ResourceType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
