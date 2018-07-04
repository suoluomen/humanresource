package com.shandong.human.resource.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * CommonController
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/8 上午11:24
 */
@Controller
public class CommonController {

    protected Logger logger = Logger.getLogger(getClass());

    // 静态资源前缀
    private static final String STATIC_PREFIX = "human-resource";

    /**
     * 404错误页面
     *
     * @return
     */
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String error_404() {
        return STATIC_PREFIX + "/404";
    }

    /**
     * 505错误页面
     *
     * @return
     */
    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String error_500() {
        return STATIC_PREFIX + "/500";
    }

}
