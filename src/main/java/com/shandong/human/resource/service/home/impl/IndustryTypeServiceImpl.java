package com.shandong.human.resource.service.home.impl;

import com.shandong.human.resource.domain.IndustryType;
import com.shandong.human.resource.mapper.home.IndustryTypeMapper;
import com.shandong.human.resource.service.home.IndustryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/31 21:26
 */
@Service
public class IndustryTypeServiceImpl implements IndustryTypeService {
    @Autowired
    private IndustryTypeMapper industryTypeMapper;
    /**
     * 获取所有的类型或原因
     *
     * @return
     */
    public ArrayList<IndustryType> getIndustryByType(int type)
    {
        return industryTypeMapper.getIndustryByType(type);
    }

    /**
     * 根据id获取企业性质
     * @return
     */
    public IndustryType getIndustryById(int id)
    {
        return industryTypeMapper.getIndustryById(id);
    }


}
