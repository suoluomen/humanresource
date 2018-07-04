package com.shandong.human.resource.controller.sys;

import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.domain.CompanyData;
import com.shandong.human.resource.domain.SurveyTime;
import com.shandong.human.resource.domain.User;
import com.shandong.human.resource.service.home.CompanyService;
import com.shandong.human.resource.service.sys.CompanyDataService;
import com.shandong.human.resource.service.sys.SurveyTimeService;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.Pair;
import com.shandong.human.resource.util.RegExpUtil;
import com.shandong.human.resource.util.Result;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 企业上报数据控制器
 * <p>
 * Author: helin <helin199210@icloud.com>
 * Time: 16/3/15 下午2:52
 */
@Controller
public class CompanyDataController {

    public static final String STATIC_PREFIX = "human-resource/sys/companyData";

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private CompanyDataService companyDataService;

    @Autowired
    private SurveyTimeService surveyTimeService;

    @Autowired
    private CompanyService companyService;

    private static final List<Pair<String, String>> companyDataErrMsg;

    static {
        companyDataErrMsg = new ArrayList<Pair<String, String>>();
        companyDataErrMsg.add(new Pair<String, String>("init_people", Constant.INITPEOPLE_ERROR));
        companyDataErrMsg.add(new Pair<String, String>("cur_people", Constant.CURPEOPLE_ERROR));
        companyDataErrMsg.add(new Pair<String, String>("reduce_type", Constant.REDUCE_ERROR));
        companyDataErrMsg.add(new Pair<String, String>("reason1", Constant.REASON1_ERROR));
        companyDataErrMsg.add(new Pair<String, String>("reason2", Constant.REASON2_ERROR));
        companyDataErrMsg.add(new Pair<String, String>("reason3", Constant.REASON3_ERROR));
        companyDataErrMsg.add(new Pair<String, String>("reason1_explain", Constant.REASON1EXP_ERROR));
        companyDataErrMsg.add(new Pair<String, String>("reason2_explain", Constant.REASON2EXP_ERROR));
        companyDataErrMsg.add(new Pair<String, String>("reason3_explain", Constant.REASON3EXP_ERROR));
        companyDataErrMsg.add(new Pair<String, String>("other_reason", Constant.OTHERREASONEXP_ERROR));
    }

    /**
     * 显示companyData界面
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/sys/data/list", method = RequestMethod.GET)
    String companyDataList(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute(Constant.LOGIN_USER);
        List<Long> status = new ArrayList<Long>();
        if (loginUser.getType() == 1) { // 省用户
            status.add(-2L);
            status.add(1L);
            status.add(2L);
            status.add(3L);
        } else if (loginUser.getType() >= 170) { // 市用户
            status.add(-1L);
            status.add(0L);
            status.add(1L);
        }

        List<CompanyData> companyDataList = companyDataService.getCompanyDataList(status);
        model.addAttribute("companyDataList", companyDataList);
        return STATIC_PREFIX + "/list";
    }

    /**
     * 删除companyData项
     *
     * @param id
     * @param model
     * @param httpSession
     * @param request
     * @return
     */
    @RequestMapping(value = "/sys/data/delete/{id}", method = RequestMethod.GET)
    String companyDataDelete(@PathVariable("id") Integer id, Model model, HttpSession httpSession, HttpServletRequest request) {

        companyDataService.deleteNewsById(id);

        List<CompanyData> companyDataList = companyDataService.companyDataList();
        model.addAttribute("companyDataList", companyDataList);
        return STATIC_PREFIX + "/list";
    }

    /**
     * 根据id获取companyData修改页
     *
     * @param id
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/sys/data/edit/{id}", method = RequestMethod.GET)
    String companyDataEditPage(@PathVariable Integer id, Model model, HttpSession session) {
        CompanyData companyData = companyDataService.getCompanyDataById(id);
        SurveyTime surveyTime = surveyTimeService.getAllSurveyTimeById(companyData.getSurvey_time_id());
        Company company = companyService.getCompanyById(companyData.getCompany_id());

        model.addAttribute("company", company);
        model.addAttribute("companyData", companyData);
        model.addAttribute("create_time", companyData.getCreate_time());
        model.addAttribute("surveyTime", surveyTime);
        return STATIC_PREFIX + "/edit";
    }

    /**
     * 修改companyData项,并添加到数据库中
     *
     * @param companyData
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/sys/data/edit", method = RequestMethod.POST)
    @ResponseBody
    Result companyDataEdit(@Valid CompanyData companyData, BindingResult result,
                           Model model, HttpSession httpSession) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            return new Result(Result.Status.ERROR, getFirstErrMsg(errors));
        }

        Pattern integerReg = Pattern.compile(RegExpUtil.UNSIGNED_INT);
        if (companyData.getInit_people() == null || !integerReg.matcher(companyData.getInit_people().toString()).matches()) {
            return new Result(Result.Status.ERROR, Constant.INITPEOPLE_ERROR);
        }
        if (companyData.getCur_people() == null || !integerReg.matcher(companyData.getCur_people().toString()).matches()) {
            return new Result(Result.Status.ERROR, Constant.CURPEOPLE_ERROR);
        }
        companyData.setCreate_time((Date) httpSession.getAttribute("create_time"));
        Integer id = companyDataService.companyDataAdd(companyData);
        // TODO:新增纪录之后要向log表中插入一条日志
        return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
    }

    /**
     * 审核页面
     *
     * @param id
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/sys/data/audit/{id}", method = RequestMethod.GET)
    String companyDataAuditPage(@PathVariable Integer id, Model model, HttpSession session) {
        CompanyData companyData = companyDataService.getCompanyDataById(id);
        SurveyTime surveyTime = surveyTimeService.getAllSurveyTimeById(companyData.getSurvey_time_id());
        Company company = companyService.getCompanyById(companyData.getCompany_id());

        model.addAttribute("company", company);
        model.addAttribute("companyData", companyData);
        model.addAttribute("create_time", companyData.getCreate_time());
        model.addAttribute("surveyTime", surveyTime);
        return STATIC_PREFIX + "/audit";
    }

    /**
     * 审核页面提交
     *
     * @param companyData
     * @return
     */
    @RequestMapping(value = "/sys/data/audit", method = RequestMethod.POST)
    @ResponseBody
    Result companyDataAudit(CompanyData companyData) {
        int status = companyData.getStatus();
        String notPassReason = companyData.getNot_pass_reason().trim();
        if ((status == -1 || status == -2) && notPassReason.equals("")) {
            return new Result(Result.Status.ERROR, Constant.COMPANY_DATA_NOT_PASS_MUST_HAVE_A_REASON);
        }

        companyDataService.provinceCheck(companyData.getId(), status, notPassReason);
        return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
    }

    private String getFirstErrMsg(List<FieldError> errors) {
        List<String> errorStr = new ArrayList<String>();
        for (FieldError r : errors) {
            errorStr.add(r.getField());
        }
        for (Pair<String, String> r : companyDataErrMsg) {
            if (errorStr.contains(r.first)) {
                return r.second;
            }
        }
        return "未知错误";
    }
}
