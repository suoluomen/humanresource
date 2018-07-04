package com.shandong.human.resource.mapper.home;

import com.shandong.human.resource.domain.Area;

import java.util.ArrayList;

/**
 * 区域Mapper
 * 
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/15 10:53
 */
public interface AreaMapper {

    /**
     * 获取所有的省份
     * 
     * @return
     */
    ArrayList<Area> getAllProvince();

    /**
     * 获取所有的城市
     * 
     * @return
     */
    ArrayList<Area> getAllCity();

    /**
     * 获取所有的区域
     * 
     * @param id
     * @return
     */
    ArrayList<Area> getAllAreaById(int id);

    /**
     * 获取相应的id
     *
     * @param id
     * @return
     */
    Area getById(int id);
}
