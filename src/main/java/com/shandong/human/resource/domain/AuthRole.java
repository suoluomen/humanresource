package com.shandong.human.resource.domain;

/**
 * 权限角色对应关系实体类
 *
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/14 9:20
 */
public class AuthRole {
    private Integer auth_id;    //权限id
    private Integer role_id;    //角色id

    public Integer getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(Integer auth_id) {
        this.auth_id = auth_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
}
