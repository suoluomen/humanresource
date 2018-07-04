package com.shandong.human.resource.service.home;

import com.shandong.human.resource.domain.Company;

import java.util.List;
import java.util.ArrayList;

/**
 * 公司service
 * <p/>
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/20 10:29
 */
public interface CompanyService {

    /**
     * 保存输入信息
     *
     * @param company
     */
    int setCompanyInfo(Company company);

    /**
     * 通过城市获得公司数量
     *
     * @return
     */
    List<Company> getCompanyNumberByCity();

    /**
     * 判断是否为空
     *
     * @param id
     * @return
     */
    int isNull(int id);

    /**
     * 通过id获得公司数据
     *
     * @param id
     * @return
     */
    Company getCompanyById(int id);

    /**
     * 更新公司信息
     *
     * @param company
     */
    void updateCompanyInfo(Company company);

    /**
     * <<<<<<< HEAD
     * 根据公司名模糊查询
     *
     * @return
     */
    List<Company> fuzzySearchByName(String name);


    /**
     * 根据公司所在区查询
     *
     * @param area_id
     * @return
     */
    List<Company> selectByAreaID(Integer area_id);


    /**
     * 根据公司地址模糊查询
     *
     * @return
     */
    List<Company> fuzzySearchByAddress(String address);


    /**
     * 根据公司业务模糊查询
     *
     * @return
     */
    List<Company> fuzzySearchByBusiness(String business);

    /**
     * 获取所有Company
     *
     * @return
     */
    ArrayList<Company> getAllCompany();
}
