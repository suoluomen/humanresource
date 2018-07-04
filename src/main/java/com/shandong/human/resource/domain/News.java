package com.shandong.human.resource.domain;

import java.sql.Date;

/**
 * 新闻实体类
 * <p>
 * Author: helin <helin199210@icloud.com>
 * Time: 16/3/11 下午1:41
 */
public class News {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Date createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
