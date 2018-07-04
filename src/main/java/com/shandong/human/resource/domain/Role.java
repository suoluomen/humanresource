package com.shandong.human.resource.domain;

/**
 * 角色实体类
 * <p>
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/14 9:31
 */

public class Role {
    private Integer id;         //主键
    private String name;        //角色名
    private short status;       //状态,-1: 禁用，1:正常
    private Boolean hasRole;    //在用户授权角色页面判断是否有这个角色

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
        this.name = name;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Boolean getHasRole() {
        return hasRole;
    }

    public void setHasRole(Boolean hasRole) {
        this.hasRole = hasRole;
    }
}
