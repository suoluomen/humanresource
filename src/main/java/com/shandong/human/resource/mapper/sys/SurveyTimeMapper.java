package com.shandong.human.resource.mapper.sys;

import com.shandong.human.resource.domain.SurveyTime;

import java.util.ArrayList;

/**
 * 调查期Mapper
 * <p>
 * Author: constantine <1194479264@qq.com>
 * Date: 16/3/11 上午10:42
 * <p>
 * Modified by SYC 3/20/16: add insert
 */
public interface SurveyTimeMapper {

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
     * 通过id获得调查期
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


