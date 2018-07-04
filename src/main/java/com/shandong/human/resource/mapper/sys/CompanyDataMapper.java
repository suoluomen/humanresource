package com.shandong.human.resource.mapper.sys;

import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.domain.CompanyData;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司上报数据mapper
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/13 下午8:34
 */
public interface CompanyDataMapper {

    /**
     * 获得全部的公司上报数据
     *
     * @return
     */
    ArrayList<CompanyData> getAllCompanyData();

    /**
     * 通过公司id获得公司上报的数据
     *
     * @param id
     * @return
     */
    ArrayList<CompanyData> getCompanyDataByCompanyIdZTR(@Param("id") Integer id);

    /**
     * 通过公司id获得公司上报数据
     *
     * @param id
     * @return
     */
    ArrayList<CompanyData> getCompanyDataByCompanyId(@Param("id") Integer id);

    /**
     * 获得公司数据列表
     *
     * @return
     */
    List<CompanyData> companyDataList();

    /**
     * 通过id删除新闻
     *
     * @param id
     */
    void deleteNewsById(Integer id);

    /**
     * 通过公司id获得公司上报的数据
     *
     * @param id
     * @return
     */
    CompanyData getCompanyDataById(Integer id);

    /**
     * 新增公司上报数据
     *
     * @param companyData
     * @return
     */
    int companyDataAdd(CompanyData companyData);

    /**
     * 在每个调查期获得公司总的人数
     *
     * @return
     */
    List<CompanyData> getTotalPeopleFromCompanyDataOfEverySuvryTime();

    /**
     * 新增公司上报数据
     *
     * @param companyData
     */
    void companyDataAddFirst(CompanyData companyData);

    /**
     * 获得公司所有的上报数据
     *
     * @return
     */
    ArrayList<CompanyData> getAllCompanyDataFromSQL();

    /**
     * 通过id改变公司上报数据的状态
     *
     * @param id
     */
    void changeCompanyDataStatusById(@Param("id") Integer id);

    /**
     * 通过调查期id获得公司上报数据
     *
     * @param survey_time_id
     * @return
     */
    ArrayList<CompanyData> getCompanyDataBySurveyTimeId(int survey_time_id);

    /**
     * 通过公司id获得企业上报数据
     *
     * @param id
     * @return
     */
    ArrayList<CompanyData> getCompanyDataByCompanyIdS2(@Param("id") int id);

    /**
     * 通过城市获得企业上报数据总数
     *
     * @return
     */
    Integer getCompanyDataCountByCity();

    /**
     * 通过省份获得企业上报数据总数
     *
     * @return
     */
    Integer getCompanyDataCountByProvince();

    /**
     * 获得企业上报数据列表
     *
     * @param ids
     * @return
     */
    List<CompanyData> getCompanyDataList(@Param("ids") List<Long> ids);

    /**
     * 更新企业上报数据的状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateCompanyDataStatus(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 通过公司id获得企业上报数据
     *
     * @param company_id
     * @return
     */
    ArrayList<CompanyData> getCompanyDataByCompanyIdLastestTime(int company_id);

    /**
     * 通过公司状态获得企业上报数据
     *
     * @param status
     * @return
     */
    List<CompanyData> selectByStatus(Integer status);

    /**
     * 根据surveyTime查询
     *
     * @return
     */
    List<CompanyData> selectBySurveyTimeID(Integer id);

    /**
     * 获得待审核企业上报数据总数
     * status为0,1的数据
     *
     * @return
     */
    Integer getToCheckCompanyDataCount();

    /**
     * 更新上报数据
     *
     * @param companyData
     * @return
     */
    Integer updateCompanyData(CompanyData companyData);

    /**
     * 更新企业上报数据的状态
     *
     * @param id
     * @param status
     * @return
     */
    void provinceCheck(@Param("id")Integer id,@Param("status")Integer status,@Param("npr")String npr);

    /**
     * 根据公司和时间查询上报数据
     *
     * @param company_id
     * @param survey_time_id
     * @return
     */
    List<CompanyData> selectByCompanyandSurveyTime(@Param("company_id") Integer company_id,@Param("survey_time_id") Integer survey_time_id);
}
