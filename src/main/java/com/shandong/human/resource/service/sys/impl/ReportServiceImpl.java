package com.shandong.human.resource.service.sys.impl;

import com.shandong.human.resource.domain.CompanyData;
import com.shandong.human.resource.mapper.sys.CompanyDataMapper;
import com.shandong.human.resource.mapper.sys.CompanyMapper;
import com.shandong.human.resource.service.sys.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 企业数据上报Service实现类
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/11 上午10:40
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private CompanyDataMapper companyDataMapper;

    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 获取全部CompanyData
     * 返回类型为一个ArrayList<CompanyData>
     *
     * @return
     */
    public ArrayList<CompanyData> getAllCompanyData() {
        return companyDataMapper.getAllCompanyData();
    }

    /**
     * 根据company_id获取CompanyData
     * 返回类型为一个ArrayList<CompanyData>
     *
     * @param id
     * @return
     */
    public ArrayList<CompanyData> getCompanyDataByCompanyId(Integer id) {
        return companyDataMapper.getCompanyDataByCompanyIdZTR(id);
    }

    /**
     * 根据id获取CompanyData
     * 返回类型为一个ArrayList<CompanyData>
     *
     * @param id
     * @return
     */
    public CompanyData getCompanyDataById(Integer id) {
        return companyDataMapper.getCompanyDataById(id);
    }

    /**
     * 根据id获取更改CompanyData的status
     *
     * @param id
     */
    public void changeCompanyDataStatusById(Integer id) {
        companyDataMapper.changeCompanyDataStatusById(id);
    }

    /**
     * 通过公司名称获得公司id
     *
     * @param name
     * @return
     */
    public Integer getCompanyIdByName(String name) {
        return companyMapper.getCompanyIdByName(name).getId();
    }

    /**
     * 通过ids获得公司上报数据
     *
     * @param id
     * @return
     */
    public ArrayList<CompanyData> getCompanyDataByCompanyIdS2(Integer id) {
        return companyDataMapper.getCompanyDataByCompanyIdS2(id);
    }
}
