package com.shandong.human.resource.service.sys;

import com.shandong.human.resource.domain.MonitorInformation;
import com.shandong.human.resource.domain.MonitorInformationAddtion;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统监控Service
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/14 下午2:25
 */
public interface MonitorService {

    /**
     * 获得系统监控信息
     *
     * @return
     */
    MonitorInformation getMonitorInformation();

    /**
     * 获得系统监控辅助信息
     *
     * @param request
     * @return
     */
    MonitorInformationAddtion getMonitorInformationAddtion(HttpServletRequest request);
}
