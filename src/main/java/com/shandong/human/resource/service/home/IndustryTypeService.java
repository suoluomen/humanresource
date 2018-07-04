package com.shandong.human.resource.service.home;

import com.shandong.human.resource.domain.IndustryType;

import java.util.ArrayList;

/**
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/31 21:25
 */

public interface IndustryTypeService {

    /**
     * 获取所有的类型或原因
     *
     * @return
     */
    ArrayList<IndustryType> getIndustryByType(int type);

    /**
     * 根据id获取企业性质
     * @return
     */
    IndustryType getIndustryById(int id);
}
