package com.shandong.human.resource.service.home;

import com.shandong.human.resource.domain.Area;

import java.util.ArrayList;

/**
 * 区域service
 * <p>
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/15 10:59
 * MODIFY: SYC 2016/3/30 16:04 note： add note of getAllCity();
 */
public interface AreaService {
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
