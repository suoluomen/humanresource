package com.shandong.human.resource.mapper.city;

import com.shandong.human.resource.domain.Company;
import com.shandong.human.resource.domain.CompanyData;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * 城市用户mapper
 *
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/30 上午10:50
 */
public interface CityMapper {

    /**
     * 通过城市id获得公司数据
     *
     * @param city_id
     * @return
     */
    ArrayList<Company> getCompanyIdByCityId(@Param("city_id") Integer city_id);

    /**
     * 通过城市id获得状态为0的公司数据
     *
     * @param company_id
     * @return
     */
    ArrayList<CompanyData> getCompanyDataByCompanyIdStatus0(@Param("company_id") Integer company_id);

    /**
     * 城市用户审核通过
     *
     * @param id
     */
    void cityCheckPass(@Param("id") Integer id);

    /**
     * 城市用户审核失败
     *
     * @param id
     * @param reason
     */
    void cityCheckFail(@Param("id") String id, @Param("reason") String reason);
}
