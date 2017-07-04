package com.redemption.shawshank.pojo;

public class User {
    private Integer id;

    private String name;

    private String icon;

    private String sex;

    private Integer birthday;

    private Boolean status;

    private Integer registerTs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getRegisterTs() {
        return registerTs;
    }

    public void setRegisterTs(Integer registerTs) {
        this.registerTs = registerTs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", status=" + status +
                ", registerTs=" + registerTs +
                '}';
    }
}