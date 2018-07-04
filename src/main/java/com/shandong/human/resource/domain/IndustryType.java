package com.shandong.human.resource.domain;

/**
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/31 21:20
 */

public class IndustryType {
    private int id;         //主键
    private String name;    //所属行业名称
    private int type;      //1代表企业性质，2代表所属行业

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
