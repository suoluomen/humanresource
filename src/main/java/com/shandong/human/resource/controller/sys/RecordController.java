package com.shandong.human.resource.controller.sys;

import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.domain.CompanyData;
import com.shandong.human.resource.service.sys.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * 企业数据查询控制器
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/10 下午2:07
 */
@Controller
public class RecordController {

    // 静态资源前缀
    public static final String STATIC_PREFIX = "human-resource/sys/record";

    @Autowired
    private RecordService recordService;

    /**
     * 显示查询页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/record/query", method = RequestMethod.GET)
    public String record(Model model) {
        ArrayList<Company> list = recordService.getAllCompany();
        model.addAttribute("recordResult", list);
        return STATIC_PREFIX + "/query";
    }

    /**
     * 查询功能
     *
     * @param city_id
     * @param area_id
     * @param name
     * @param code
     * @param contact
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/record/query", method = RequestMethod.POST)
    public String handleQuery(String city_id, String area_id, String name, String code, String contact, Model model) {
//        System.out.println(city_id);
//        System.out.println(area_id);
//        System.out.println(name);
//        System.out.println(code);
//        System.out.println(contact);
        ArrayList<Company> list = recordService.getCompanyByCondition(city_id,
                area_id,
                name,
                code,
                contact);
        System.out.println(list.size());
        model.addAttribute("recordResult", list);
        return STATIC_PREFIX + "/query";
    }

    /**
     * 显示详情页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/sys/record/{id}", method = RequestMethod.GET)
    public String recordDetail(Model model, @PathVariable("id") Integer id) {
        Company detail = recordService.getCompanyById(id);
        model.addAttribute("companyDetail", detail);
        ArrayList<CompanyData> list = recordService.getCompanyDataByCompanyId(id);
        model.addAttribute("companyDataList", list);
        return STATIC_PREFIX + "/detail";
    }

    /**
     * 导出数据到excel
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/record/export", method = RequestMethod.POST)
    public String export(Model model) {
        ArrayList<Company> list = recordService.getAllCompany();
        model.addAttribute("exResult", list);
        return STATIC_PREFIX + "/model";
    }
}
