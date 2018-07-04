package com.shandong.human.resource.service.city;

import com.shandong.human.resource.domain.CompanyData;

import java.util.ArrayList;

/**
 * 城市用户service
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/30 上午10:41
 */
public interface CityService {

    /**
     * 通过城市id获得公司数据
     *
     * @param city_id
     * @return
     */
    Integer[] getCompanyIdByCity(Integer city_id);

    /**
     * 通过城市id获得状态为0的公司数据
     *
     * @param company_id
     * @return
     */
    ArrayList<CompanyData> getCompanyDataByCompanyIdStatus0(Integer company_id);

    /**
     * 城市用户审核通过
     *
     * @param id
     */
    void cityCheckPass(Integer id);

    /**
     * 城市用户审核失败
     *
     * @param id
     * @param reason
     */
    void cityCheckFail(Integer id, String reason);
}
