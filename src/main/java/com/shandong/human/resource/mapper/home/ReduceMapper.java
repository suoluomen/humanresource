package com.shandong.human.resource.mapper.home;

import com.shandong.human.resource.domain.Reduce;

import java.util.ArrayList;

/**
 * 企业人数减少的原因Mapper
 * <p>
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/27 19:55
 */
public interface ReduceMapper {

    /**
     * 通过类型获得企业人数减少的原因
     *
     * @param type
     * @return
     */
    ArrayList<Reduce> getTypeOfReason(int type);
}
