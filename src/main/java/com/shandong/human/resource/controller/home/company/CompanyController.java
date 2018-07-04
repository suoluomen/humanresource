package com.shandong.human.resource.controller.home.company;

import com.shandong.human.resource.domain.Area;
import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.domain.IndustryType;
import com.shandong.human.resource.domain.User;
import com.shandong.human.resource.service.home.AreaService;
import com.shandong.human.resource.service.home.CompanyService;
import com.shandong.human.resource.service.home.IndustryTypeService;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.Pair;
import com.shandong.human.resource.util.Result;
import com.shandong.human.resource.util.config.CompositeFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公司备案主要的控制类
 * <p>
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/14 10:11
 * Modified: noprom <tyee.noprom@qq.com>
 */
@Controller
public class CompanyController {

    Logger logger = Logger.getLogger(getClass());

    // 静态资源前缀
    public static final String STATIC_PREFIX = "human-resource/home/company";

    @Autowired
    private AreaService areaService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private IndustryTypeService industryTypeService;

    private static ArrayList<Pair<String, String>> companyErrorMsg;

    static {
        companyErrorMsg = new ArrayList<Pair<String, String>>();
        companyErrorMsg.add(new Pair<String, String>("name", setErrorMessage(Constant.COMPANY_NAME_5_30_CHAR)));
        companyErrorMsg.add(new Pair<String, String>("business", setErrorMessage(Constant.COMPANY_BUSINESS_5_255)));
        companyErrorMsg.add(new Pair<String, String>("code", setErrorMessage(Constant.COMPANY_CODE_2_9_CHAR)));
        companyErrorMsg.add(new Pair<String, String>("zipcode", setErrorMessage(Constant.COMPANY_ZIP_CODE_6_NUM)));
        companyErrorMsg.add(new Pair<String, String>("contact", setErrorMessage(Constant.COMPANY_CONTACT_2_20)));
        companyErrorMsg.add(new Pair<String, String>("phone", setErrorMessage(Constant.COMPANY_PHONE_ILLEGAL)));
        companyErrorMsg.add(new Pair<String, String>("fax", setErrorMessage(Constant.COMPANY_FAX_ILLEGAL)));
        companyErrorMsg.add(new Pair<String, String>("email", setErrorMessage(Constant.COMPANY_EMAIL_ILLEGAL)));
        companyErrorMsg.add(new Pair<String, String>("address", setErrorMessage(Constant.COMPANY_ADDRESS_5_100_CHAR)));
    }

    /**
     * 获取所有的城市
     * 获取数据显示页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home/company/ ", method = RequestMethod.GET)
    public String getProvince(Model model, HttpServletRequest request, HttpSession session) {

        ArrayList<Area> list = areaService.getAllCity();
        //刚开始打开页面时显示济南市的所有县
        ArrayList<Area> listCity = areaService.getAllAreaById(170);
        //获取所有的企业性质
        ArrayList<IndustryType> listIndustryType = industryTypeService.getIndustryByType(1);
        //获取所有的所属行业
        ArrayList<IndustryType> listIndustryInvolve = industryTypeService.getIndustryByType(2);
        model.addAttribute("editResult", list);
        model.addAttribute("editResultCity", listCity);
        model.addAttribute("listIndustryType", listIndustryType);
        model.addAttribute("listIndustryInvolve", listIndustryInvolve);
        User user = (User) session.getAttribute(Constant.LOGIN_USER);
        int id = user.getId();
        //检查数据是否已经插入到数据库中
        int count = companyService.isNull(id);
        if (count > 0) {
            model.addAttribute("info", "exit");
        } else
            model.addAttribute("info", "");
        return STATIC_PREFIX + "/add";
    }

    /**
     * 获取所有的地区
     * 获取数据显示页面
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/home/company/add/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Area> getAllArea(@PathVariable("id") Integer id) {
        //获取地区
        System.out.println(id.intValue());
        ArrayList<Area> list = areaService.getAllAreaById(id.intValue());
        return list;
    }

    /**
     * 保存企业备案信息
     *
     * @param company
     * @param result
     * @param session
     * @return
     */
    @RequestMapping(value = "/home/company/add/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result saveCompany(@Valid Company company, BindingResult result, HttpSession session) {
        User user = (User) session.getAttribute(Constant.LOGIN_USER);
        int userId = user.getId();
        // 检查是否已经上报数据
        int count = companyService.isNull(userId);
        if (count > 0) {
            return new Result(Result.Status.ERROR, Constant.COMPANY_ALREADY_EXISTS, false);
        }
        // 校验数据格式,并返回错误信息
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();

            // 返回第一条错误即可
            String firstFieldError = getFirstFieldErrorMessage(errors);
            return new Result(Result.Status.ERROR, Constant.DEAL_FAIL, firstFieldError);
        }
        company.setId(userId);
        companyService.setCompanyInfo(company);
        return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
    }

    /**
     * 企业信息修改
     * 获取修改页面
     *
     * @param model
     * @param session
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/home/company/edit", method = RequestMethod.GET)
    public String getProvinceEdit(Model model, HttpSession session, HttpServletResponse response) throws IOException {
        User user = (User) session.getAttribute(Constant.LOGIN_USER);
        int id = user.getId();
        int count = companyService.isNull(id);
        if (count == 0) {
            response.sendRedirect("/home/company/add");
            return null;
        }
        Company company = companyService.getCompanyById(id);
        //id是主键，数组只有一个元素
        Area cityArea = areaService.getById(company.getCity_id());
        Area areaArea = areaService.getById(company.getArea_id());
        ArrayList<Area> listCity = areaService.getAllCity();
        ArrayList<Area> listArea = areaService.getAllAreaById(company.getCity_id());
        //获取所有的企业性质
        ArrayList<IndustryType> listIndustryType = industryTypeService.getIndustryByType(1);
        IndustryType industryType = industryTypeService.getIndustryById(company.getIndustry_type());
        //获取所有的所属行业
        ArrayList<IndustryType> listIndustryInvolve = industryTypeService.getIndustryByType(2);
        IndustryType industryType1 = industryTypeService.getIndustryById(company.getIndustry_involved());
        model.addAttribute("listCity", listCity);
        model.addAttribute("listArea", listArea);
        model.addAttribute("company", company);
        model.addAttribute("cityArea", cityArea);
        model.addAttribute("areaArea", areaArea);
        model.addAttribute("listIndustryType", listIndustryType);
        model.addAttribute("listIndustryInvolve", listIndustryInvolve);
        model.addAttribute("industryType", industryType);
        model.addAttribute("industryType1", industryType1);
        return STATIC_PREFIX + "/edit";
    }

    /**
     * 保存修改信息
     *
     * @param company
     * @param session
     * @return
     */
    @RequestMapping(value = "/home/company/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map saveInfo(Company company, HttpSession session) {
        Map map = new HashMap();
        User user = (User) session.getAttribute(Constant.LOGIN_USER);
        int id = user.getId();
        //检查数据是否已经插入到数据库中
        int count = companyService.isNull(id);
        if (count == 0) {
            map.put("success", "noInfo");
            return map;
        }
        //判断企业名称格式
        Matcher matcher = Pattern.compile("^[\\u4e00-\\u9fa5]*$").matcher(company.getName());
        int flag = 0;
        if (!matcher.matches()) {
            flag++;
        }
        matcher = Pattern.compile("^[a-zA-Z]+$").matcher(company.getName());
        if (!matcher.matches()) {
            flag++;
        }
        if (flag == 2) {
            map.put("success", "error0");
            return map;
        }
        matcher = Pattern.compile("^.{0,60}$").matcher(company.getName());
        if (!matcher.matches()) {
            map.put("success", "error01");
            return map;
        }
        if (company.getName() == "") {
            map.put("success", "error02");
            return map;
        }
        matcher = Pattern.compile("^.{0,255}$").matcher(company.getBusiness());
        if (!matcher.matches()) {
            map.put("success", "error5");
            return map;
        }
        if (company.getBusiness() == "") {
            map.put("success", "error51");
            return map;
        }
        matcher = Pattern.compile("^[0-9a-zA-Z]{0,9}$").matcher(company.getCode());
        if (!matcher.matches()) {
            map.put("success", "error6");
            return map;
        }
        if (company.getCode() == "") {
            map.put("success", "error61");
            return map;
        }
        matcher = Pattern.compile("\\b\\d{6}\\b").matcher(company.getZipcode());
        if (!matcher.matches()) {
            map.put("success", "error1");
            return map;
        }
        //联系人格式判断
        matcher = Pattern.compile("^[\\u4e00-\\u9fa5]*$").matcher(company.getContact());
        flag = 0;
        if (!matcher.matches()) {
            flag++;
        }
        matcher = Pattern.compile("^[a-zA-Z]+$").matcher(company.getContact());
        if (!matcher.matches()) {
            flag++;
        }
        if (flag == 2) {
            map.put("success", "error7");
            return map;
        }
        matcher = Pattern.compile("^.{0,20}$").matcher(company.getContact());
        if (!matcher.matches()) {
            map.put("success", "error71");
            return map;
        }

        if (company.getContact() == "") {
            map.put("success", "error72");
            return map;
        }
        matcher = Pattern.compile("^1\\d{10}$|^(0\\d{2,3}-?)?[1-9]\\d{4,7}(-\\d{1,8})?$").matcher(company.getPhone());
        if (!matcher.matches()) {
            map.put("success", "error2");
            return map;
        }
        matcher = Pattern.compile("^((\\d{3,4})|\\d{3,4}-)?\\d{7,8}$").matcher(company.getFax());
        if (!matcher.matches()) {
            map.put("success", "error3");
            return map;
        }
        matcher = Pattern.compile("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}").matcher(company.getEmail());
        if (!matcher.matches()) {
            map.put("success", "error4");
            return map;
        }

        //联系地址的长度
        matcher = Pattern.compile("^.{0,100}$").matcher(company.getAddress());
        if (!matcher.matches()) {
            map.put("success", "error8");
            return map;
        }
        if (company.getAddress() == "") {
            map.put("success", "error81");
            return map;
        }
        company.setId(id);
        companyService.updateCompanyInfo(company);
        map.put("success", "success");
        return map;
    }

    /**
     * 获得字段验证的错误信息
     *
     * @param key
     * @return
     */
    private String getErrorMessage(String key) {
        for (Pair<String, String> pair : companyErrorMsg) {
            if (pair.first.equals(key)) {
                return pair.second;
            }
        }
        return null;
    }

    /**
     * 设置模板消息
     *
     * @param message
     * @return
     */
    private static String setErrorMessage(String message) {
        return CompositeFactory.getInstance().getString(message);
    }

    /**
     * 获得第一条错误信息
     *
     * @param errors
     * @return
     */
    private String getFirstFieldErrorMessage(List<FieldError> errors) {
        List<String> fields = new ArrayList<String>();
        for (FieldError error : errors) {
            fields.add(error.getField());
        }
        // 按照制定的顺序查找
        for (Pair<String, String> pair : companyErrorMsg) {
            if (fields.contains(pair.first)) {
                return pair.second;
            }
        }
        return null;
    }
}
