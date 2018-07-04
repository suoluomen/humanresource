package com.shandong.human.resource.domain;

/**
 * 企业人数减少类型|原因
 * <p>
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/14 9:28
 */
public class Reduce {
    private int id;        //主键
    private String name;   //
    private int type;      //类型，1:就业人数减少类型, 2:就业人数减少原因

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
