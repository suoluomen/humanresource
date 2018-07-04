package com.shandong.human.resource.mapper.sys;

import com.shandong.human.resource.domain.News;

import java.util.List;

/**
 * 通知Mapper
 * <p>
 * Author: helin <helin199210@icloud.com>
 * Time: 16/3/11 下午1:47
 */
public interface NewsMapper {

    /**
     * 新增通知
     *
     * @param news
     * @return news id
     */
    int addNews(News news);

    /**
     * 获得通知列表
     *
     * @return
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
