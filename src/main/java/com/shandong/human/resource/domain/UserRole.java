package com.shandong.human.resource.domain;

/**
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/14 9:37
 */

public class UserRole {
    private Integer user_id;  //用户id
    private Integer role_id;  //角色id

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
}
