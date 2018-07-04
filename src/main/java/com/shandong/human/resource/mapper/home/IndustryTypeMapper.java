package com.shandong.human.resource.mapper.home;

import com.shandong.human.resource.domain.IndustryType;

import java.util.ArrayList;

/**
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/31 21:24
 */

public interface IndustryTypeMapper {
    /**
     * 获取所有的企业类型或性质
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
