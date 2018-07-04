package com.shandong.human.resource.controller.sys.surveyTime;

import com.shandong.human.resource.domain.SurveyTime;
import com.shandong.human.resource.service.sys.SurveyTimeService;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Brief: 权限管理类
 * Author: syc
 * Date: 2016/3/15
 */
@RequestMapping("/sys")
@Controller
public class SurveyTimeController {

    // 静态资源前缀
    public static final String STATIC_PREFIX = "human-resource/sys/surveyTime";

    @Autowired
    private SurveyTimeService service;

    /**
     * 转到surveyTime页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/surveyTime", method = RequestMethod.GET)
    public String toServeyTimePage(HttpServletRequest request, HttpServletResponse response) {
        return STATIC_PREFIX + "/add";
    }

    /**
     * 提交surveyTime
     *
     * @param date
     * @param response
     */
    @RequestMapping(value = "/surveyTime/add", method = RequestMethod.POST)
    public @ResponseBody
    Result add(String date, HttpServletResponse response) {

        String[] times = date.split("-");
        if (times.length < 2) {
            return new Result(Result.Status.ERROR, Constant.SURVERYTIME_REFUSE);
        }

        SurveyTime toAdd = new SurveyTime();
//        DateFormat date_t = new SimpleDateFormat("MM-dd-yyyy");
//        String strTime_s = times[0].replace('/', '-');
//        String endTime_s = times[1].replace('/', '-');
        DateFormat date_t = new SimpleDateFormat("MM/dd/yyyy");
        String strTime_s = times[0];
        String endTime_s = times[1];

        Date strDate = new Date();
        Date endDate = new Date();

        try {
            strDate = date_t.parse(strTime_s);
            endDate = date_t.parse(endTime_s);
            toAdd.setStart_time(strDate);
            toAdd.setEnd_time(endDate);
            service.insertSurveyTime(toAdd);
        } catch (ParseException e) {
            return new Result(Result.Status.ERROR, Constant.SURVERYTIME_REFUSE);
        }
        return new Result(Result.Status.SUCCESS, Constant.SUBMIT_SUCCESS);
    }
}