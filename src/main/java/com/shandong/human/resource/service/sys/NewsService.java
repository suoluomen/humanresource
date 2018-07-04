package com.shandong.human.resource.service.sys;

import com.shandong.human.resource.domain.News;

import java.util.List;

/**
 * 与News相关的接口
 * 主要包括:
 * 1.查看通知
 * 2.增加通知
 * 3.删除通知
 * 4.修改通知
 * <p>
 * Author: helin <helin199210@icloud.com>
 * Time: 16/3/11 下午1:46
 */
public interface NewsService {

    /**
     * 新增通知
     *
     * @param news News
     * @retrun news id
     */
    int addNews(News news);

    /**
     * 查询所有通知
     *
     * @return news
     */
    List<News> newsList();

    /**
     * 通过通知id删除新闻
     *
     * @param id
     */
    void deleteNewsById(Integer id);

    /**
     * 通过通知id查询新闻
     *
     * @param id
     * @return
     */
    News selectNewsById(int id);

    /**
     * 编辑新闻
     *
     * @param news
     * @return
     */
    int editNewsById(News news);
}
