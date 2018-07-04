package com.shandong.human.resource.controller.city;

import com.shandong.human.resource.domain.CompanyData;
import com.shandong.human.resource.domain.User;
import com.shandong.human.resource.service.city.CityService;
import com.shandong.human.resource.service.sys.CompanyDataService;
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
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 城市用户主要的控制器类
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/30 上午10:37
 */
@Controller
@RequestMapping("/sys")
public class CityController {

    public static final String STATIC_PREFIX = "human-resource/city";

    @Autowired
    CityService cityService;

    @Autowired
    CompanyDataService companyDataService;

    /**
     * 显示查询页面
     *
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/city/query", method = RequestMethod.GET)
    public String query(Model model, HttpServletRequest request, HttpServletResponse response) {
        User localUser = (User) request.getSession().getAttribute(Constant.LOGIN_USER);
        int city_id = localUser.getType();

        Integer[] IDS = cityService.getCompanyIdByCity(city_id);

        ArrayList<CompanyData> list = new ArrayList<CompanyData>();

        for (Integer i : IDS) {
            //System.out.println(i);
            ArrayList<CompanyData> temp = cityService.getCompanyDataByCompanyIdStatus0(i);
            for (CompanyData cd : temp) {
                list.add(cd);
            }
        }
        System.out.println(list.size());
        model.addAttribute("cityQueryResult", list);
        return STATIC_PREFIX + "/query";
    }

    /**
     * 城市用户审核
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/city/check/{id}", method = RequestMethod.GET)
    public String check(Model model, @PathVariable("id") Integer id) {
        CompanyData cd = companyDataService.getCompanyDataById(id);
        model.addAttribute("check", cd);
        return STATIC_PREFIX + "/check";
    }

    /**
     * 城市用户审核提交数据
     *
     * @param model
     * @param cdid
     * @param pass
     * @param reason
     * @return
     */
    @RequestMapping(value = "/city/check", method = RequestMethod.POST)
    @ResponseBody
    public Result checked(Model model, String cdid, String pass, String reason) {
        Integer id = Integer.parseInt(cdid);
        Integer flag = Integer.parseInt(pass);
        if(id == null || flag == null ){
            return new Result(Result.Status.ERROR,Constant.SEASSON_TIMEOUT);
        }
        if (flag == 1) {
            cityService.cityCheckPass(id);
            return new Result(Result.Status.SUCCESS,Constant.DEAL_SUCCESS);
        }
        else if(reason!=null && !reason.isEmpty()) {
            cityService.cityCheckFail(id, reason);
            return new Result(Result.Status.SUCCESS,Constant.DEAL_SUCCESS);
        }
        return new Result(Result.Status.ERROR,Constant.REASON_INVALID);
    }
}
