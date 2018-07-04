package com.shandong.human.resource.service.home.impl;

import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.mapper.sys.CompanyMapper;
import com.shandong.human.resource.service.home.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

/**
 * 公司service实现类
 * <p/>
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/20 10:30
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 保存输入信息
     *
     * @param company
     */
    public int setCompanyInfo(Company company) {
        return companyMapper.setCompanyInfo(company);
    }

    /**
     * 通过城市获得公司数量
     *
     * @return
     */
    public List<Company> getCompanyNumberByCity() {
        return companyMapper.getCompanyNumberByCity();
    }

    /**
     * 判断是否为空
     *
     * @param id
     * @return
     */
    public int isNull(int id) {
        return companyMapper.isNull(id);
    }

    /**
     * 通过id获得公司数据
     *
     * @param id
     * @return
     */
    public Company getCompanyById(int id) {
        return companyMapper.getCompanyById(id);
    }

    /**
     * 更新公司信息
     *
     * @param company
     */
    public void updateCompanyInfo(Company company) {
        companyMapper.updateCompanyInfo(company);
    }

    /**
     * 根据公司名模糊查询
     *
     * @param name
     * @return
     */
    public List<Company> fuzzySearchByName(String name) {
        return companyMapper.fuzzySearchByName(name);
    }

    /**
     * 根据公司所在区查询
     *
     * @param area_id
     * @return
     */
    public List<Company> selectByAreaID(Integer area_id) {
        return companyMapper.selectByAreaID(area_id);
    }

    /**
     * 根据公司地址模糊查询
     *
     * @return
     */
    public List<Company> fuzzySearchByAddress(String address) {
        return companyMapper.fuzzySearchByAddress(address);
    }


    /**
     * 根据公司业务模糊查询
     *
     * @return
     */
    public List<Company> fuzzySearchByBusiness(String business) {
        return companyMapper.fuzzySearchByBusiness(business);
    }

    /**
     * 获取所有Company
     *
     * @return
     */
    public ArrayList<Company> getAllCompany() {
        return companyMapper.getAllCompany();
    }
}
