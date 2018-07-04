package com.shandong.human.resource.domain;

import java.util.Date;

/**
 * 调查时间实体类
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/11 上午10:20
 */
public class SurveyTime {
    private int id;         //主键
    private Date start_time;//开始时间
    private Date end_time;  //结束时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
