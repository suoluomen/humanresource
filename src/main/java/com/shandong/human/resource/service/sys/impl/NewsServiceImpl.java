package com.shandong.human.resource.service.sys.impl;

import com.shandong.human.resource.domain.News;
import com.shandong.human.resource.mapper.sys.NewsMapper;
import com.shandong.human.resource.service.sys.NewsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 与News相关的接口实现类
 * 主要包括:
 * 1.查看通知
 * 2.增加通知
 * 3.删除通知
 * 4.修改通知
 * <p>
 * Author: helin <helin199210@icloud.com>
 * Time: 16/3/11 下午1:47
 */
@Service
@Transactional(isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    /**
     * 新增通知
     *
     * @param news News
     * @retrun news id
     */
    public int addNews(News news) {
        return newsMapper.addNews(news);
    }

    /**
     * 查询所有通知
     *
     * @return news
     */
    public List<News> newsList() {
        return newsMapper.newsList();
    }

    /**
     * 通过通知id删除新闻
     *
     * @param id
     */
    public void deleteNewsById(Integer id) {
        newsMapper.deleteNewsById(id);
    }

    /**
     * 通过通知id查询新闻
     *
     * @param id
     * @return
     */
    public News selectNewsById(int id) {
        return newsMapper.selectNewsById(id);
    }

    /**
     * 编辑新闻
     *
     * @param news
     * @return
     */
    public int editNewsById(News news) {
        return newsMapper.editNewsById(news);
    }
}
