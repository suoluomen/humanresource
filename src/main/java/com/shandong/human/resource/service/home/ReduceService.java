package com.shandong.human.resource.service.home;

import com.shandong.human.resource.domain.Reduce;

import java.util.ArrayList;

/**
 * 公司人数减少原因的service
 * <p>
 * Author: chenyongpeng <chen9121@foxmail.com>
 * Date: 2016/3/27 19:50
 */
public interface ReduceService {

    /**
     * 获取就业人数减少类型和理由
     *
     * @param type
     * @return
     */
    ArrayList<Reduce> getTypeOfReason(int type);
}
