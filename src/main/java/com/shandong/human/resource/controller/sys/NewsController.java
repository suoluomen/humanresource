package com.shandong.human.resource.controller.sys;

import com.shandong.human.resource.domain.News;
import com.shandong.human.resource.domain.User;
import com.shandong.human.resource.service.sys.NewsService;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 通知控制器
 * 包括:
 * 增加,删除,修改,查询通知
 * <p>
 * Author: helin <helin199210@icloud.com>
 * Time: 16/3/11 下午1:45
 */
@Controller
public class NewsController {

    //静态资源前缀
    public static final String STATIC_PREFIX = "human-resource/sys/news";

    @Autowired
    private NewsService newsService;

    /**
     * 通知列表
     *
     * @param model
     * @param news
     * @return
     */
    @RequestMapping(value = "/sys/news", method = RequestMethod.GET)
    String newsList(Model model, News news) {
        List<News> newsList = newsService.newsList();
        model.addAttribute("newsList", newsList);
        return STATIC_PREFIX + "/list";
    }

    /**
     * 新闻详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/news/{id}", method = RequestMethod.GET)
    String newsDetail(@PathVariable("id") Integer id, Model model) {
        News news = newsService.selectNewsById(id);
        model.addAttribute("newsDetail", news);
        return STATIC_PREFIX + "/detail";
    }

    /**
     * 新增新闻
     *
     * @param news
     * @return
     */
    @RequestMapping(value = "/sys/news/add", method = RequestMethod.POST)
    public
    @ResponseBody
    Result newsAdd(News news, HttpSession session) {
        User loginUser = (User) session.getAttribute(Constant.LOGIN_USER);
        news.setUserId(loginUser.getId());
        Integer id = newsService.addNews(news);
        if (id >= 0) {
            return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
        } else {
            return new Result(Result.Status.ERROR, Constant.DEAL_FAIL);
        }
    }

    /**
     * 显示新增新闻页面
     *
     * @return
     */
    @RequestMapping(value = "/sys/news/add", method = RequestMethod.GET)
    String addNews() {
        return STATIC_PREFIX + "/add";
    }

    /**
     * 删除新闻
     *
     * @param model
     * @param httpSession
     * @param request
     * @return
     */
    @RequestMapping(value = "/sys/news/delete/{id}", method = RequestMethod.GET)
    String newsDelete(@PathVariable("id") Integer id, Model model, HttpSession httpSession, HttpServletRequest request) {
        newsService.deleteNewsById(id);
        List<News> newsList = newsService.newsList();
        model.addAttribute("newsList", newsList);
        return STATIC_PREFIX + "/list";
    }

    /**
     * 编辑新闻页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/news/edit/{id}", method = RequestMethod.GET)
    public String newsEditPage(@PathVariable("id") Integer id, Model model) {
        News news = newsService.selectNewsById(id);
        model.addAttribute("newToEdit", news);
        return STATIC_PREFIX + "/edit";
    }

    /**
     * 编辑新闻提交页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/news/edit", method = RequestMethod.POST)
    public
    @ResponseBody
    Result editNews(Model model, News news) {
        Integer id = newsService.editNewsById(news);
        if (id > 0) {
            return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
        } else {
            return new Result(Result.Status.ERROR, Constant.DEAL_FAIL);
        }
    }
}
