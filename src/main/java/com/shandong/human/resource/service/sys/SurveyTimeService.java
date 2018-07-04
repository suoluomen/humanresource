package com.shandong.human.resource.service.sys;

import com.shandong.human.resource.domain.SurveyTime;

import java.util.ArrayList;

/**
 * 调查期Service
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/20/16 下午1:21
 */
public interface SurveyTimeService {

    /**
     * 获取所有调查时间
     *
     * @return
     */
    ArrayList<SurveyTime> getAllSurveyTime();

    /**
     * 向表中插入一个时间
     *
     * @param time
     * @return
     */
    Integer insertSurveyTime(SurveyTime time);

    /**
     * 通过id获得所有调查期
     *
     * @param id
     * @return
     */
    SurveyTime getAllSurveyTimeById(int id);

    /**
     * 获得调查期的总数
     *
     * @return
     */
    Integer getSurveyTimeCount();
}
