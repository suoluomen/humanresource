package com.shandong.human.resource.controller.sys;

import com.shandong.human.resource.domain.CompanyData;
import com.shandong.human.resource.service.sys.ReportService;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * 企业数据上报控制器
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/123 下午3:24
 */
@Controller
public class ReportController {
    public static final String STATIC_PREFIX = "human-resource/sys/report";

    @Autowired
    private ReportService reportService;

    /**
     * 获取全部status为1的CompanyData
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/report/query", method = RequestMethod.GET)
    public String getAllCompanyData(Model model) {
        ArrayList<CompanyData> list = reportService.getAllCompanyData();
        model.addAttribute("reportResult", list);
        return STATIC_PREFIX + "/query";
    }

    /**
     * 获取指定company_id并且status为1的CompanyData
     *
     * @param model
     * @param company_id
     * @return
     */
    @RequestMapping(value = "/sys/report/query", method = RequestMethod.POST)
    public String getAllCompanyDataById(Model model, int company_id) {
        ArrayList<CompanyData> list = reportService.getCompanyDataByCompanyId(company_id);
        model.addAttribute("reportResult", list);
        return STATIC_PREFIX + "/query";
    }

    /**
     * 上报到部级
     *
     * @param model
     * @param choose
     */
    @RequestMapping(value = "/sys/report", method = RequestMethod.POST)
    public
    @ResponseBody
    Result report(Model model, String choose) {
        if (choose == null || choose.isEmpty()) {
            return new Result(Result.Status.ERROR, Constant.YOU_MUST_CHOOSE_THA_DATA_TO_REPORT);
        }
        String[] ids = choose.split(",");
        ArrayList<CompanyData> list = new ArrayList<CompanyData>();
        for (String r : ids) {
            if (!r.equals("0")) {
                int id = Integer.parseInt(r);
                CompanyData companyData = reportService.getCompanyDataById(id);
                list.add(companyData);
            }
        }
        for (String r : ids) {
            if (!r.equals("0")) {
                int id = Integer.parseInt(r);
                reportService.changeCompanyDataStatusById(id);
            }
        }
        //TODO:假装上报成功
        return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
    }

    /**
     * 显示详情页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/sys/report/{id}", method = RequestMethod.GET)
    public String recordDetail(Model model, @PathVariable("id") Integer id) {
//        Company detail = recordService.getCompanyById(id);
//        model.addAttribute("companyDetail", detail);
        ArrayList<CompanyData> list = reportService.getCompanyDataByCompanyId(id);
        model.addAttribute("companyDataList", list);
        return STATIC_PREFIX + "/detail";
    }

    /**
     * 获取指定company_id并且status为1的CompanyData
     *
     * @param model
     * @param name
     * @return
     */
    @RequestMapping(value = "/sys/report/queryByName", method = RequestMethod.POST)
    public String getAllCompanyDataByName(Model model, String name) {
        Integer company_id = 0;
        ArrayList<CompanyData> list = null;

        try {
            company_id = reportService.getCompanyIdByName(name);
        } catch (Exception e) {
            model.addAttribute("reportResult", list);
            return STATIC_PREFIX + "/query";
        }

        list = reportService.getCompanyDataByCompanyIdS2(company_id);
        model.addAttribute("reportResult", list);
        return STATIC_PREFIX + "/query";
    }
}
