package com.shandong.human.resource.domain;

/**
 * 企业统计数据实体类
 * <p>
 * Author: helin <helin199210@icloud.com>
 * Time: 16/3/28 下午6:01
 */
public class StatisticsOfCompany {
    private String city;
    private Integer number;
    private String proportion;

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
