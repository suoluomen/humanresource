package com.shandong.human.resource.domain;

import java.util.List;

/**
 * 权限控制实体
 * <p>
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/14 9:09
 */
public class Auth {
    private Integer id;               // 主键
    private String name;              // 权限名称
    private String url;               // 权限对应的访问url
    private Integer pid;              // 上级权限
    private Integer level;            // 权限等级，1:一级菜单,2:二级菜单,3:三级菜单,4:页面按钮级别的权限
    private Boolean hasAuth;          // 判断某个角色是否有这个权限
    private List<Auth> childList;     // 儿子权限列表,只为遍历方便使用

    public List<Auth> getChildList() {
        return childList;
    }

    public void setChildList(List<Auth> childList) {
        this.childList = childList;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getHasAuth() {
        return hasAuth;
    }

    public void setHasAuth(Boolean hasAuth) {
        this.hasAuth = hasAuth;
    }
}
