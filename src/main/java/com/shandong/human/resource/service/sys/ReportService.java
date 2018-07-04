package com.shandong.human.resource.service.sys;

import com.shandong.human.resource.domain.CompanyData;

import java.util.ArrayList;

/**
 * 企业数据上报Service
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/24 上午10:38
 */
public interface ReportService {

    /**
     * 获取全部CompanyData
     * 返回类型为一个ArrayList<CompanyData>
     *
     * @return
     */
    ArrayList<CompanyData> getAllCompanyData();

    /**
     * 根据company_id获取CompanyData
     * 返回类型为一个ArrayList<CompanyData>
     *
     * @param id
     * @return
     */
    ArrayList<CompanyData> getCompanyDataByCompanyId(Integer id);

    /**
     * 根据id获取CompanyData
     * 返回类型为一个ArrayList<CompanyData>
     *
     * @param id
     * @return
     */
    CompanyData getCompanyDataById(Integer id);

    /**
     * 根据id获取更改CompanyData的status
     *
     * @param id
     */
    void changeCompanyDataStatusById(Integer id);

    /**
     * 通过公司名称获得公司id
     *
     * @param name
     * @return
     */
    Integer getCompanyIdByName(String name);

    /**
     * 通过ids获得公司上报数据
     *
     * @param id
     * @return
     */
    ArrayList<CompanyData> getCompanyDataByCompanyIdS2(Integer id);
}
