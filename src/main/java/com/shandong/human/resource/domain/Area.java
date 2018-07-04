package com.shandong.human.resource.domain;

/**
 * 区域类
 *
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/14 9:09
 */
public class Area {
    private int id;             //主键
    private int pid;            //上级id
    private  String name;       //地区名字
    private short status;      //状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
}
