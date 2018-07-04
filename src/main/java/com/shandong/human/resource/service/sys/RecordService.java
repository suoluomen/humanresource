package com.shandong.human.resource.service.sys;

import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.domain.CompanyData;
import com.shandong.human.resource.domain.SurveyTime;

import java.util.ArrayList;

/**
 * 企业上报数据service
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/11 上午10:38
 */
public interface RecordService {

    /**
     * 通过id获取Company对象
     * 返回类型为一个Company类
     *
     * @param id
     * @return
     */
    Company getCompanyById(Integer id);

    /**
     * 获得所有SurveyTime对象
     * 返回类型为一个SurveyTime的ArrayList
     *
     * @return
     */
    ArrayList<SurveyTime> getAllSurveyTime();

    /**
     * 通过id获取指定的CompanyData对象
     *
     * @param id
     * @return
     */
    ArrayList<CompanyData> getCompanyDataByCompanyId(Integer id);

    /**
     * 获取全部的Company对象
     *
     * @return
     */
    ArrayList<Company> getAllCompany();

    /**
     * 通过条件查找Company对象
     *
     * @param city_id
     * @param area_id
     * @param name
     * @param code
     * @param contact
     * @return
     */
    ArrayList<Company> getCompanyByCondition(String city_id, String area_id,
                                             String name, String code, String contact);
}
