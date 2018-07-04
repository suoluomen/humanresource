package com.shandong.human.resource.controller;

import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.domain.News;
import com.shandong.human.resource.domain.User;
import com.shandong.human.resource.service.home.CompanyService;
import com.shandong.human.resource.service.sys.CompanyDataService;
import com.shandong.human.resource.service.sys.NewsService;
import com.shandong.human.resource.service.sys.SurveyTimeService;
import com.shandong.human.resource.service.sys.UserService;
import com.shandong.human.resource.util.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 首页控制器
 * <p>
 * Author: noprom <tyee.noprom@qq.com>
 * Date: 3/16/16 下午2:15
 */
@Controller
public class IndexController {

    Logger logger = Logger.getLogger(getClass());

    // 静态资源前缀
    public static final String STATIC_PREFIX = "human-resource";

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private SurveyTimeService surveyTimeService;

    @Autowired
    private CompanyDataService companyDataService;

    @Autowired
    private NewsService newsService;

    /**
     * 首页显示页面
     *
     * @param model model
     * @return 视图
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute(Constant.LOGIN_USER);
        if (loginUser == null) {// 用户没有登陆
            return STATIC_PREFIX + "/login";
        } else {
            // 通知列表所有用户都可以查看
            List<News> newsList = newsService.newsList();
            model.addAttribute("newsList", newsList);

            if (loginUser.getType() == 2) {//企业用户
                // 查看当前登陆企业用户是否已经备案
                Company company = companyService.getCompanyById(loginUser.getId());
                if (company == null) {//企业还没备案
                    model.addAttribute("authorize", false);
                } else {
                    model.addAttribute("authorize", true);
                }
                return STATIC_PREFIX + "/index";
            } else {
                // 已备案企业数量
                Integer totalCompanyCount = companyService.getAllCompany().size();
                // 总系统用户数
                Integer totalUserCount = userService.getCount();
                // 总调查期
                Integer totalSurveyTimeCount = surveyTimeService.getSurveyTimeCount();
                // 待审核企业上报数据总数
                Integer totalCompanyDataCount = companyDataService.getToCheckCompanyDataCount();

                model.addAttribute("totalUserCount", totalUserCount);
                model.addAttribute("totalCompanyCount", totalCompanyCount);
                model.addAttribute("totalSurveyTimeCount", totalSurveyTimeCount);
                model.addAttribute("totalCompanyDataCount", totalCompanyDataCount);
                return STATIC_PREFIX + "/index";
            }
        }
    }
}
