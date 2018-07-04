package com.shandong.human.resource.domain;

import java.util.Date;

/**
 * 企业数据查询结果类
 */
public class CompanyDataQuaryList {
    private Date start_time;       //开始时间
    private Date end_time;         //结束时间
    private int init_people;        //建档期就业人数,填写初次将档时监测点就业人数
    private int cur_people;         //调查期就业人数,填写本次调查期当时的监测点就业人数
    private String other_reason;    //其他原因

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public int getInit_people() {
        return init_people;
    }

    public void setInit_people(int init_people) {
        this.init_people = init_people;
    }

    public int getCur_people() {
        return cur_people;
    }

    public void setCur_people(int cur_people) {
        this.cur_people = cur_people;
    }

    public String getOther_reason() {
        return other_reason;
    }

    public void setOther_reason(String other_reason) {
        this.other_reason = other_reason;
    }
}