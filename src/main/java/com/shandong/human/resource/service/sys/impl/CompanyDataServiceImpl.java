package com.shandong.human.resource.service.sys.impl;

import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.domain.CompanyData;
import com.shandong.human.resource.mapper.sys.CompanyDataMapper;
import com.shandong.human.resource.service.sys.CompanyDataService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公司数据service实现类
 * <p/>
 * Author: helin <helin199210@icloud.com>
 * Time: 16/3/15 下午3:11
 */
@Service
@Transactional(isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class CompanyDataServiceImpl implements CompanyDataService {

    @Autowired
    private CompanyDataMapper companyDataMapper;

    /**
     * 获得公司数据列表
     *
     * @return
     */
    public List<CompanyData> companyDataList() {
        return companyDataMapper.companyDataList();
    }

    /**
     * 通过id删除新闻
     *
     * @param id
     */
    public void deleteNewsById(Integer id) {
        companyDataMapper.deleteNewsById(id);
    }

    /**
     * 通过id得到公司数据
     *
     * @param id
     * @return
     */
    public CompanyData getCompanyDataById(Integer id) {
        return companyDataMapper.getCompanyDataById(id);
    }

    /**
     * 新增公司上报数据
     *
     * @param companyData
     * @return
     */
    public int companyDataAdd(CompanyData companyData) {
        return companyDataMapper.companyDataAdd(companyData);
    }

    /**
     * 在每个调查期获得公司总的人数
     *
     * @return
     */
    public List<CompanyData> getTotalPeopleFromCompanyDataOfEverySuvryTime() {
        return companyDataMapper.getTotalPeopleFromCompanyDataOfEverySuvryTime();
    }

    /**
     * 新增公司上报数据
     *
     * @param companyData
     */
    public void companyDataAddFirst(CompanyData companyData) {
        companyDataMapper.companyDataAddFirst(companyData);
    }

    /**
     * 获得公司所有的上报数据
     *
     * @return
     */
    public ArrayList<CompanyData> getAllCompanyDataFromSQL() {
        return companyDataMapper.getAllCompanyDataFromSQL();
    }

    /**
     * 通过调查期id获得公司上报数据
     *
     * @param survey_time_id
     * @return
     */
    public List<CompanyData> getCompanyDataBySurveyTimeId(int survey_time_id) {
        return companyDataMapper.getCompanyDataBySurveyTimeId(survey_time_id);
    }

    /**
     * 获得市用户应该看到的数据总数
     *
     * @return
     */
    public Integer getCompanyDataCountByCity() {
        return companyDataMapper.getCompanyDataCountByCity();
    }

    /**
     * 获得省用户应该看到的数据总数
     *
     * @return
     */
    public Integer getCompanyDataCountByProvince() {
        return companyDataMapper.getCompanyDataCountByProvince();
    }

    /**
     * 根据status字段的值使用
     * in查询查询数据列表
     *
     * @param ids
     * @return
     */
    public List<CompanyData> getCompanyDataList(List<Long> ids) {
        return companyDataMapper.getCompanyDataList(ids);
    }

    /**
     * 更新数据的状态
     *
     * @param id
     * @param status
     * @return
     */
    public int updateCompanyDataStatus(Integer id, Integer status) {
        return companyDataMapper.updateCompanyDataStatus(id, status);
    }

    /**
     * 按照最新时间获取公司的ID
     *
     * @param company_id
     * @return
     */
    public ArrayList<CompanyData> getCompanyDataByCompanyIdLastestTime(int company_id) {
        return companyDataMapper.getCompanyDataByCompanyIdLastestTime(company_id);
    }

    /**
     * 通过公司状态获得企业上报数据
     *
     * @param status
     * @return
     */
    public List<CompanyData> selectByStatus(Integer status) {
        return companyDataMapper.selectByStatus(status);
    }


    /**
     * 根据surveyTime查询
     *
     * @return
     */
    public List<CompanyData> selectBySurveyTimeID(Integer id) {
        return companyDataMapper.selectBySurveyTimeID(id);
    }

    /**
     * 获得待审核企业上报数据总数
     * status为0, 1 的数据
     *
     * @return
     */
    public Integer getToCheckCompanyDataCount() {
        return companyDataMapper.getToCheckCompanyDataCount();
    }

    /**
     * 更新上报数据
     *
     * @param companyData
     * @return
     */
    public Integer updateCompanyData(CompanyData companyData) {
        return companyDataMapper.updateCompanyData(companyData);
    }

    /**
     * 更新数据的状态
     *
     * @param id
     * @param status
     * @param npr
     * @return
     */
    public void provinceCheck(Integer id, Integer status, String npr) {
        companyDataMapper.provinceCheck(id, status, npr);
    }

    /**
     * 根据公司和时间查询上报数据
     *
     * @param company_id
     * @param survey_time_id
     * @return
     */
    public List<CompanyData> selectByCompanyandSurveyTime(Integer company_id, Integer survey_time_id) {
        return companyDataMapper.selectByCompanyandSurveyTime(company_id, survey_time_id);
    }
}
