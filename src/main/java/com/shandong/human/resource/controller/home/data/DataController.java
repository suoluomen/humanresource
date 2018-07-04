package com.shandong.human.resource.controller.home.data;

import com.shandong.human.resource.domain.*;
import com.shandong.human.resource.service.home.ReduceService;
import com.shandong.human.resource.service.sys.CompanyDataService;
import com.shandong.human.resource.service.sys.SurveyTimeService;
import com.shandong.human.resource.domain.CompanyDataQuaryList;
import com.shandong.human.resource.util.Constant;
import com.shandong.human.resource.util.Pair;
import com.shandong.human.resource.util.RegExpUtil;
import com.shandong.human.resource.util.Result;
import org.apache.ibatis.annotations.Param;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 企业数据上报主要的控制器类
 * <p/>
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/14 10:11
 */
@Controller
public class DataController {
    //静态资源前缀
    public static final String STATIC_PREFIX = "human-resource/home/data";

    @Autowired
    private ReduceService reduceService;

    @Autowired
    public SurveyTimeService surveyTimeService;

    @Autowired
    public CompanyDataService companyDataService;


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
     * 添加原因和类型
     *
     * @param model
     * @param request
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "/home/data/add", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        User user = (User) session.getAttribute(Constant.LOGIN_USER);
        int id = user.getId();
        ArrayList<CompanyData> companyDatas = companyDataService.getCompanyDataByCompanyIdLastestTime(id);
        model.addAttribute("companyDataList", companyDatas);
        ArrayList<Reduce> listType = reduceService.getTypeOfReason(1);
        ArrayList<Reduce> listReason = reduceService.getTypeOfReason(2);
        model.addAttribute("listType", listType);
        model.addAttribute("listReason", listReason);
        //获取调查期
        ArrayList<SurveyTime> listSurverTime = surveyTimeService.getAllSurveyTime();

        model.addAttribute("listSurverTime", listSurverTime);
        return STATIC_PREFIX + "/add";
    }

    /**
     * 保存企业信息
     * 获取图形显示页面
     */
    @RequestMapping(value = "/home/data/add/submit", method = RequestMethod.POST)
    @ResponseBody
    public Result postMessage(@Valid CompanyData companyData, BindingResult result,
                              HttpSession session) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            return new Result(Result.Status.ERROR, getFirstErrMsg(errors));
        }

        User user = (User) session.getAttribute(Constant.LOGIN_USER);
        if (user == null) {
            return new Result(Result.Status.ERROR, Constant.SEASSON_TIMEOUT);
        }

        Pattern integerReg = Pattern.compile(RegExpUtil.UNSIGNED_INT);
        if (companyData.getInit_people() == null || !integerReg.matcher(companyData.getInit_people().toString()).matches()) {
            return new Result(Result.Status.ERROR, Constant.INITPEOPLE_ERROR);
        }
        if (companyData.getCur_people() == null || !integerReg.matcher(companyData.getCur_people().toString()).matches()) {
            return new Result(Result.Status.ERROR, Constant.CURPEOPLE_ERROR);
        }

        companyData.setCompany_id(user.getId());
        companyData.setPid(0);
        companyData.setCreate_time(new Date());
        List<CompanyData> temp = companyDataService.selectByCompanyandSurveyTime(companyData.getCompany_id(),companyData.getSurvey_time_id());
        if(temp!=null&&temp.size()!=0){
            return new Result(Result.Status.ERROR, Constant.DATE_DUPLICATE);
        }

        companyDataService.companyDataAddFirst(companyData);

        return new Result(Result.Status.SUCCESS, Constant.DEAL_SUCCESS);
    }

    /**
     * 获取修改页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home/data/query", method = RequestMethod.GET)
    public String query(Model model) {
        ArrayList<CompanyData> companyDatas = companyDataService.getAllCompanyDataFromSQL();
        ArrayList<CompanyDataQuaryList> companyDataQuaryLists = new ArrayList<CompanyDataQuaryList>();
        //DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        for (CompanyData companyData : companyDatas) {
            SurveyTime surveyTime = surveyTimeService.getAllSurveyTimeById(companyData.getSurvey_time_id());
            CompanyDataQuaryList companyDataQuaryList = new CompanyDataQuaryList();
            companyDataQuaryList.setStart_time(surveyTime.getStart_time());
            companyDataQuaryList.setEnd_time(surveyTime.getEnd_time());
            companyDataQuaryList.setCur_people(companyData.getCur_people());
            companyDataQuaryList.setInit_people(companyData.getInit_people());
            companyDataQuaryList.setOther_reason(companyData.getOther_reason());
            companyDataQuaryLists.add(companyDataQuaryList);
        }

        model.addAttribute("companyDataQuaryLists", companyDataQuaryLists);
        return STATIC_PREFIX + "/query";
    }

    @RequestMapping(value = "/home/data/edit/{id}", method = RequestMethod.GET)
    public String toEditPage(@PathVariable("id") Integer data_id, Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        CompanyData companyData = companyDataService.getCompanyDataById(data_id);
        User localUser = (User) session.getAttribute(Constant.LOGIN_USER);
        if (companyData == null || companyData.getCompany_id() != localUser.getId() || companyData.getStatus() >= 0) {
            model.addAttribute("error", "您无权修改该数据");
            return "human-resource/error";
        }

        request.setAttribute("companyData", companyData);

        //获取原因类型
        ArrayList<Reduce> listType = reduceService.getTypeOfReason(1);
        ArrayList<Reduce> listReason = reduceService.getTypeOfReason(2);
        model.addAttribute("listType", listType);
        model.addAttribute("listReason", listReason);
        //获取调查期
        ArrayList<SurveyTime> listSurverTime = surveyTimeService.getAllSurveyTime();

        model.addAttribute("listSurverTime", listSurverTime);

        return STATIC_PREFIX + "/edit";
    }


    /**
     * 保存企业信息
     * 获取图形显示页面
     */
    @RequestMapping(value = "/home/data/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result editData(@Valid CompanyData companyData, BindingResult result,
                           HttpSession session) throws IOException {
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

        User user = (User) session.getAttribute(Constant.LOGIN_USER);

        if (user == null || user.getId() != companyData.getCompany_id()) {
            return new Result(Result.Status.ERROR, Constant.SEASSON_TIMEOUT);
        }

        companyDataService.updateCompanyData(companyData);

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