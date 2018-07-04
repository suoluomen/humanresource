package com.shandong.human.resource.controller.sys;

import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.domain.CompanyData;
import com.shandong.human.resource.domain.StatisticsOfCompany;
import com.shandong.human.resource.domain.SurveyTime;
import com.shandong.human.resource.service.home.CompanyService;
import com.shandong.human.resource.service.sys.CompanyDataService;
import com.shandong.human.resource.service.sys.SurveyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据分析的控制器类
 * <p/>
 * Author: helin <helin199210@icloud.com>
 * Time: 16/3/27 上午9:51
 */
@Controller
public class AnalyseController {

    public static final String STATIC_PREFIX = "human-resource/sys/companyData";

    @Autowired
    private CompanyDataService companyDataService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private SurveyTimeService surveyTimeService;


//    @RequestMapping(value = "/sys/data/display", method = RequestMethod.GET)
//    String companyDataDisplay(Model model, HttpSession httpSession) {
//
//
////        List<CompanyData> companyDataOfPeople = companyDataService.getTotalPeopleFromCompanyDataOfEverySuvryTime();
////        if (companyDataOfPeople.size() > 0) {
////            httpSession.setAttribute("peopleOfEverySurveyTime", companyDataOfPeople);
////        } else {
////            for (int i = 0; i < 12; i++) {
////                companyDataOfPeople.get(i).setInit_people(0);
////            }
////            httpSession.setAttribute("peopleOfEverySurveyTime", companyDataOfPeople);
////        }
//
//
//        return STATIC_PREFIX + "/display";
//
//
//    }

    /**
     * 按照城市获取企业数量
     *
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/sys/data/display", method = RequestMethod.GET)
    String companyNumber(Model model, HttpSession httpSession) {
        /**
         * 全省企业的汇总数据
         */
        List<CompanyData> companyDataOfPeople = companyDataService.getTotalPeopleFromCompanyDataOfEverySuvryTime();
        List<Float> trend = new ArrayList<Float>();
        DecimalFormat df = new DecimalFormat("######0.00");
        if (companyDataOfPeople.size() > 0) {
            model.addAttribute("peopleOfEverySurveyTime", companyDataOfPeople);

            /**
             * 走势
             */
            trend.add((float) 0.0);
            for (int i = 1; i < companyDataOfPeople.size(); i++) {
                float temp = (companyDataOfPeople.get(i).getCur_people() - companyDataOfPeople.get(i - 1).getCur_people()) / (float) companyDataOfPeople.get(i - 1).getCur_people();
                trend.add(Float.valueOf(df.format(temp)));
                System.out.println(trend.get(i - 1));
            }
            model.addAttribute("trend", trend);


        } else {
//            for (int i = 0; i < 12; i++) {
//                companyDataOfPeople.get(i).setInit_people(0);
//            }
            model.addAttribute("peopleOfEverySurveyTime", companyDataOfPeople);
        }
        /**
         *企业分布
         */
        List<Company> companyNumberByCity = companyService.getCompanyNumberByCity();
        float sum = 0;
        for (int i = 0; i < companyNumberByCity.size(); i++) {

            sum += companyNumberByCity.get(i).getCity_id();
        }

        List<StatisticsOfCompany> statisticsOfCompanyList = new ArrayList<StatisticsOfCompany>();

        for (int i = 0; i < companyNumberByCity.size(); i++) {


            StatisticsOfCompany statisticsOfCompany = new StatisticsOfCompany();

            float proportion = companyNumberByCity.get(i).getCity_id() / sum * 100;

            statisticsOfCompany.setCity(companyNumberByCity.get(i).getAddress());
            statisticsOfCompany.setNumber(companyNumberByCity.get(i).getCity_id());
            statisticsOfCompany.setProportion(df.format(proportion));

            statisticsOfCompanyList.add(statisticsOfCompany);
        }

        model.addAttribute("statisticsOfCompanyList", statisticsOfCompanyList);

        /**
         *
         */
        List<SurveyTime> surveyTimeList = new ArrayList<SurveyTime>();
        surveyTimeList = surveyTimeService.getAllSurveyTime();
        model.addAttribute("surveyTimeList", surveyTimeList);


        return STATIC_PREFIX + "/analyse";
    }

    /**
     * 对比分析
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/sys/data/duibifenxi", method = RequestMethod.POST)
    public
    @ResponseBody
    List<String> companyNumber(HttpServletRequest request) {
        List<CompanyData> companyDataOfPeriodOneList = companyDataService.getCompanyDataBySurveyTimeId(Integer.parseInt(String.valueOf(request.getParameter("period"))));

        if (companyDataOfPeriodOneList.size() == 0) {
            System.out.println("hello 0000");
            List<String> ajaxReturn = new ArrayList<String>();
            for(int i=0;i<6;i++){
                ajaxReturn.add("0");
            }

            for (int i = 0; i < ajaxReturn.size(); i++)
                System.out.println(ajaxReturn.get(i));
            return ajaxReturn;
        } else
            return analysis(companyDataOfPeriodOneList);
    }

    /**
     * 计算企业总数、建档期总岗位数、调查期总岗位数、岗位变化总数、岗位减少总数、岗位变化数量占比。
     *
     * @param companyDataList
     * @return
     */
    private List<String> analysis(List<CompanyData> companyDataList) {
        /**
         * 计算企业总数
         */
        Integer totalCompanyNumber = companyDataList.size();

        /**
         * 计算建档期总岗位数
         */
        Integer totalInitPeople = 0;
        Integer totalCurPeople = 0;
        Integer totalChangeOfCompany = 0;
        Integer totalReduceOfPeople = 0;

        for (int i = 0; i < companyDataList.size(); i++) {
            Integer I = companyDataList.get(i).getInit_people();
            Integer C = companyDataList.get(i).getCur_people();
            totalInitPeople += I;
            totalCurPeople += C;
            if (I != C)
                totalChangeOfCompany++;
            if (I < C)
                totalReduceOfPeople++;
        }


        List<String> ajaxReturn = new ArrayList<String>();
        ajaxReturn.add(totalCompanyNumber.toString());
        ajaxReturn.add(totalInitPeople.toString());
        ajaxReturn.add(totalCurPeople.toString());
        Integer temp1 = totalCurPeople - totalInitPeople;
        ajaxReturn.add(temp1.toString());
        ajaxReturn.add(totalReduceOfPeople.toString());
        Float temp2 = (totalCurPeople - totalInitPeople) / (float) totalInitPeople * 100;
        DecimalFormat df = new DecimalFormat("######0.00");

        ajaxReturn.add(df.format(temp2).toString());

        for (int i = 0; i < ajaxReturn.size(); i++)
            System.out.println(ajaxReturn.get(i));
        return ajaxReturn;
    }
}
